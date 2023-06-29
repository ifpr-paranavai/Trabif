import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { Permissao } from 'src/app/models/permissao';

@Injectable({
  providedIn: 'root'
})
export class PermissaoService implements BaseService {

  baseUrl = 'http://localhost:8080/api/permissao';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<Permissao>> {
    return this.http.get<BaseResults<Permissao>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<Permissao>> {
    return this.http.get<BaseResult<Permissao>>(this.baseUrl + '/' + id);
  }
  post(obj: Permissao): Observable<BaseResult<Permissao>> {
    return this.http.post<BaseResult<Permissao>>(this.baseUrl, obj);
  }
  put(id: number, obj: Permissao): Observable<BaseResult<Permissao>> {
    return this.http.put<BaseResult<Permissao>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
