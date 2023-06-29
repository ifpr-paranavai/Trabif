import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { Email } from 'src/app/models/email';

@Injectable({
  providedIn: 'root'
})
export class EmailService implements BaseService {

  baseUrl = 'http://localhost:8080/api/email';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<Email>> {
    return this.http.get<BaseResults<Email>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<Email>> {
    return this.http.get<BaseResult<Email>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<Email>> {
    return this.http.post<BaseResult<Email>>(this.baseUrl, obj);
  }
  put(id: number, obj: Email): Observable<BaseResult<Email>> {
    return this.http.put<BaseResult<Email>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
