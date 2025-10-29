import {Component, OnDestroy} from '@angular/core';
import {ShpImportService} from '../service/shp-import.service';
import {StatusResponse} from '../model/StatusResponse';
import {BehaviorSubject, expand, interval, Subscription, switchMap, tap, timer} from 'rxjs';
import {CommonModule} from '@angular/common';
import {ImportResult} from '../model/ImportResult';
import {PrimeTemplate} from 'primeng/api';
import {TableModule} from 'primeng/table';
import {AbstractShpComponent} from '../../abstract/AbstractShpComponent';
import {InplaceModule} from 'primeng/inplace';
import {ProgressSpinnerModule} from 'primeng/progressspinner';

@Component({
  selector: 'app-shp-importer',
  standalone: true,
  imports: [CommonModule, PrimeTemplate, TableModule, InplaceModule, ProgressSpinnerModule],
  templateUrl: './shp-importer.component.html',
  styleUrl: './shp-importer.component.css'
})
export class ShpImporterComponent extends AbstractShpComponent<ImportResult> implements OnDestroy{
  statusResponse!: StatusResponse;
  importResultList: ImportResult[] | undefined;

  private readonly defaultIntervalMs = 2000;
  private readonly fastIntervalMs = 500;
  private readonly slowIntervalMs = 5000;

  private pollingIntervalMs = this.defaultIntervalMs;
  private statusSubscription!: Subscription;

  constructor(private service: ShpImportService) {
    super();
    this.getStatusData();
  }

  private getStatusData() {
    this.statusSubscription = timer(0).pipe(
      switchMap(() => this.service.checkStatus()),
      tap(status => {
        this.statusResponse = status;
        if (status.status === 'FREE') {
          this.pollingIntervalMs = this.slowIntervalMs;
        }
      }),
      expand(() =>
        timer(this.pollingIntervalMs).pipe(
          switchMap(() => this.service.checkStatus()),
          tap(status => {
            this.statusResponse = status;
            if (status.status === 'FREE') {
              this.pollingIntervalMs = this.slowIntervalMs;
            } else {
              this.pollingIntervalMs = this.fastIntervalMs;
            }
          })
        )
      )
    ).subscribe();
  }

  private onImportClick() {
    this.pollingIntervalMs = this.fastIntervalMs;
  }

  importGesut() {
    this.onImportClick();
    this.importResultList = undefined;
    this.service.importGesut().subscribe(result => {
      this.importResultList = result;
    });
  }

  importSytuacja(){
    this.onImportClick();
    this.importResultList = undefined;
    this.service.importSytuacja().subscribe(result => {
      this.importResultList = result;
    });
  }

  importSwde(){
    this.onImportClick();
    this.importResultList = undefined;
    this.service.importSwde().subscribe(result => {
      this.importResultList = result;
    });
  }

  isFree(): boolean {
    if(this.statusResponse && this.statusResponse.status === 'FREE'){
      return true;
    } else {
      return false;
    }
  }

  ngOnDestroy(): void {
    this.statusSubscription?.unsubscribe();
  }

}
