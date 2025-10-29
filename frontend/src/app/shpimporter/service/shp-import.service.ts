import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {StatusResponse} from '../model/StatusResponse';
import {ImportResult} from '../model/ImportResult';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShpImportService {
  private path: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public checkStatus(): Observable<StatusResponse>{
    return this.http.get<StatusResponse>(this.path + 'getStatus');
  }

  public importGesut(): Observable<ImportResult[]>{
    return this.http.get<ImportResult[]>(this.path + 'importDeclaredTables?mode=GESUT&splitComplexGeom=true');
  }

  public importSytuacja(): Observable<ImportResult[]>{
    return this.http.get<ImportResult[]>(this.path + 'importDeclaredTables?mode=SYTUACJA&splitComplexGeom=false');
  }

  public importSwde(): Observable<ImportResult[]>{
    return this.http.get<ImportResult[]>(this.path + 'importDeclaredTables?mode=SWDE&splitComplexGeom=true');
  }

}
