import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { Categoria } from 'src/app/models/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService implements BaseService {

  baseUrl = 'http://localhost:8080/api/categoria';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<Categoria>> {
    return this.http.get<BaseResults<Categoria>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<Categoria>> {
    return this.http.get<BaseResult<Categoria>>(this.baseUrl + '/' + id);
  }
  post(obj: Categoria): Observable<BaseResult<Categoria>> {
    return this.http.post<BaseResult<Categoria>>(this.baseUrl, obj);
  }
  put(id: number, obj: Categoria): Observable<BaseResult<Categoria>> {
    return this.http.put<BaseResult<Categoria>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
