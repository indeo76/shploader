import { Component } from '@angular/core';
import {ShpImportService} from '../service/shp-import.service';
import {StatusResponse} from '../model/StatusResponse';
import {interval, Subscription, switchMap} from 'rxjs';
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
export class ShpImporterComponent extends AbstractShpComponent<ImportResult>{
  statusResponse!: StatusResponse;
  private statusSubscription!: Subscription;
  importResultList: ImportResult[] | undefined;

  constructor(private service: ShpImportService) {
    super();
    this.getStatusData();
  }

  private getStatusData() {
    this.service.checkStatus().subscribe(status => {
      this.statusResponse = status; // pierwsze wykonanie po załadowaniu
    });
    this.statusSubscription = interval(1000).pipe(
      switchMap(() => this.service.checkStatus())
    ).subscribe(status => {
      this.statusResponse = status;
    });
  }

  importGesut() {
    this.importResultList = undefined;
    this.service.importGesut().subscribe(result => {
      this.importResultList = result;
    });
  }

  importSytuacja(){
    this.importResultList = undefined;
    this.service.importSytuacja().subscribe(result => {
      this.importResultList = result;
    });
  }

  importSwde(){
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

  protected readonly JSON = JSON;
}
