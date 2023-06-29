import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { AutorTabalho } from 'src/app/models/autor-tabalho';

@Injectable({
  providedIn: 'root'
})
export class AutorTrabalhoService implements BaseService {

  baseUrl = 'http://localhost:8080/api/autorTrabalho';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<AutorTabalho>> {
    return this.http.get<BaseResults<AutorTabalho>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<AutorTabalho>> {
    return this.http.get<BaseResult<AutorTabalho>>(this.baseUrl + '/' + id);
  }
  post(obj: AutorTabalho): Observable<BaseResult<AutorTabalho>> {
    return this.http.post<BaseResult<AutorTabalho>>(this.baseUrl, obj);
  }
  put(id: number, obj: AutorTabalho): Observable<BaseResult<AutorTabalho>> {
    return this.http.put<BaseResult<AutorTabalho>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
