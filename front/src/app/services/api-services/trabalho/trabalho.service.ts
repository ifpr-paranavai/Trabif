import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { Trabalho } from 'src/app/models/trabalho';

@Injectable({
  providedIn: 'root'
})
export class TrabalhoService implements BaseService {

  baseUrl = 'http://localhost:8080/api/trabalho';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<Trabalho>> {
    return this.http.get<BaseResults<Trabalho>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<Trabalho>> {
    return this.http.get<BaseResult<Trabalho>>(this.baseUrl + '/' + id);
  }
  post(obj: Trabalho): Observable<BaseResult<Trabalho>> {
    return this.http.post<BaseResult<Trabalho>>(this.baseUrl, obj);
  }
  put(id: number, obj: Trabalho): Observable<BaseResult<Trabalho>> {
    return this.http.put<BaseResult<Trabalho>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
