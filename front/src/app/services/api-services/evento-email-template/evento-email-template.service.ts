import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { EventoEmailTemplate } from 'src/app/models/evento-email-template';

@Injectable({
  providedIn: 'root'
})
export class EventoEmailTemplateService implements BaseService {

  baseUrl = 'http://localhost:8080/api/eventoEmailTemplate';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<EventoEmailTemplate>> {
    return this.http.get<BaseResults<EventoEmailTemplate>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<EventoEmailTemplate>> {
    return this.http.get<BaseResult<EventoEmailTemplate>>(this.baseUrl + '/' + id);
  }
  post(obj: EventoEmailTemplate): Observable<BaseResult<EventoEmailTemplate>> {
    return this.http.post<BaseResult<EventoEmailTemplate>>(this.baseUrl, obj);
  }
  put(id: number, obj: EventoEmailTemplate): Observable<BaseResult<EventoEmailTemplate>> {
    return this.http.put<BaseResult<EventoEmailTemplate>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getByIdEvento(idEvento: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/evento/' + idEvento);
  }
  postNewTemplate(obj: any): Observable<any> {
    return this.http.post<any>(this.baseUrl + '/template/', obj);
  }
}
