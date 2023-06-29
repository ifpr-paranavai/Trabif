import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { EventoOrganizador } from 'src/app/models/evento-organizador';

@Injectable({
  providedIn: 'root'
})
export class EventoOrganizadorService implements BaseService {

  baseUrl = 'http://localhost:8080/api/eventoOrganizador';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<EventoOrganizador>> {
    return this.http.get<BaseResults<EventoOrganizador>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<EventoOrganizador>> {
    return this.http.get<BaseResult<EventoOrganizador>>(this.baseUrl + '/' + id);
  }
  post(obj: EventoOrganizador): Observable<BaseResult<EventoOrganizador>> {
    return this.http.post<BaseResult<EventoOrganizador>>(this.baseUrl, obj);
  }
  put(id: number, obj: EventoOrganizador): Observable<BaseResult<EventoOrganizador>> {
    return this.http.put<BaseResult<EventoOrganizador>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
