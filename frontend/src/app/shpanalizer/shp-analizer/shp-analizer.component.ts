import { Component } from '@angular/core';
import {ShpAnalizerService} from '../service/shp-analizer.service';
import {ShpReport, ShpReportRow} from '../model/ShpReport';
import { CommonModule } from '@angular/common';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import {TableModule} from 'primeng/table';
import {AbstractShpComponent} from '../../abstract/AbstractShpComponent';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-shp-analizer',
  standalone: true,
  imports: [CommonModule, ProgressSpinnerModule, TableModule, FormsModule],
  templateUrl: './shp-analizer.component.html',
  styleUrl: './shp-analizer.component.css'
})
export class ShpAnalizerComponent extends AbstractShpComponent<ShpReportRow>{
  private shpReport!: ShpReport;
  rowsFiltered: ShpReportRow[] = [];
  isAnalizeRunning = false;
  tableList: string[] = [];
  selectedTable!: string;
  kodNowy!: string;

  constructor(private service: ShpAnalizerService) {
    super();
  }

  analizuj(){
    this.isAnalizeRunning = true;
    this.service.analizuj()
      .subscribe(result => {
        this.shpReport = result;
        this.rowsFiltered = this.shpReport.rows;
        this.extractTables();
        this.isAnalizeRunning = false;
    });
  }

  private extractTables(): void {
    this.tableList = [
      ...new Set(
        this.shpReport.rows
          .map(row => row.kodGeoinfo?.tabela)
          .filter((tabela): tabela is string => !!tabela)
      )
    ];
    this.tableList.sort();
  }

  filterByTable(table: string) {
    this.selectedTable = table;
    this.rowsFiltered = this.shpReport.rows.filter(row => {
      return row.kodGeoinfo?.tabela === table;
    });
  }

  showAll() {
    this.rowsFiltered = this.shpReport.rows;
  }

  showNoOldCode(): void {
    this.rowsFiltered = this.shpReport.rows.filter(row => {
      return !row.dkp_N;
    });
  }

  findByKodNowy(): void {
    this.rowsFiltered = this.shpReport.rows.filter(row => {
      return row.kodNowy === this.kodNowy;
    });
  }

}
