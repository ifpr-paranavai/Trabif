import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { BaseService } from '../base/base.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrganizadorService implements BaseService {

  baseUrl = 'http://localhost:3000/organizador';
  constructor(private http: HttpClient) { }
  getAll(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
  getById(id: number): Observable<any> {
    return this.http.get(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<any> {
    return this.http.post(this.baseUrl, obj);
  }
  put(id: number, obj: any): Observable<any> {
    return this.http.put(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
