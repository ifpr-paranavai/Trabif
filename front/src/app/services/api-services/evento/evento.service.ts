import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { Evento } from 'src/app/models/evento';

@Injectable({
  providedIn: 'root'
})
export class EventoService implements BaseService {

  baseUrl = 'http://localhost:3000/evento';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<Evento>> {
    return this.http.get<BaseResults<Evento>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<Evento>> {
    return this.http.get<BaseResult<Evento>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<Evento>> {
    return this.http.post<BaseResult<Evento>>(this.baseUrl, obj);
  }
  put(id: number, obj: Evento): Observable<BaseResult<Evento>> {
    return this.http.put<BaseResult<Evento>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
