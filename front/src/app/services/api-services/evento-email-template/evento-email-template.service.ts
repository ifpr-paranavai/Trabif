import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';

@Injectable({
  providedIn: 'root'
})
export class EventoEmailTemplateService implements BaseService {

  baseUrl = 'http://localhost:8080/api/eventoEmailTemplate';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<any>> {
    return this.http.get<BaseResults<any>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<any>> {
    return this.http.get<BaseResult<any>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<any>> {
    return this.http.post<BaseResult<any>>(this.baseUrl, obj);
  }
  put(id: number, obj: any): Observable<BaseResult<any>> {
    return this.http.put<BaseResult<any>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
