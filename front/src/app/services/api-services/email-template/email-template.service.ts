import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { EmailTemplate } from 'src/app/models/email-template';

@Injectable({
  providedIn: 'root'
})
export class EmailTemplateService implements BaseService {

  baseUrl = 'http://localhost:8080/api/emailTemplate';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<EmailTemplate>> {
    return this.http.get<BaseResults<EmailTemplate>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<EmailTemplate>> {
    return this.http.get<BaseResult<EmailTemplate>>(this.baseUrl + '/' + id);
  }
  post(obj: EmailTemplate): Observable<BaseResult<EmailTemplate>> {
    return this.http.post<BaseResult<EmailTemplate>>(this.baseUrl, obj);
  }
  put(id: number, obj: EmailTemplate): Observable<BaseResult<EmailTemplate>> {
    return this.http.put<BaseResult<EmailTemplate>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
