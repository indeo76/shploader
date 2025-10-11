import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ShpReport} from '../model/ShpReport';

@Injectable({
  providedIn: 'root'
})
export class ShpAnalizerService {
  private path: string = 'http://localhost:8095/shploader/shpanalizer/analizuj';

  constructor(private http: HttpClient) { }

  analizuj(mode: string): Observable<ShpReport> {
    return this.http.get<ShpReport>(this.path + '?mode=' + mode);
  }

}
