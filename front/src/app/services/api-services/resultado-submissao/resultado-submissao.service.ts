import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { ResultadoSubmissao } from 'src/app/models/resultado-submissao';

@Injectable({
  providedIn: 'root'
})
export class ResultadoSubmissaoService implements BaseService {

  baseUrl = 'http://localhost:8080/api/resultadoSubmissao';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<ResultadoSubmissao>> {
    return this.http.get<BaseResults<ResultadoSubmissao>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<ResultadoSubmissao>> {
    return this.http.get<BaseResult<ResultadoSubmissao>>(this.baseUrl + '/' + id);
  }
  post(obj: ResultadoSubmissao): Observable<BaseResult<ResultadoSubmissao>> {
    return this.http.post<BaseResult<ResultadoSubmissao>>(this.baseUrl, obj);
  }
  put(id: number, obj: ResultadoSubmissao): Observable<BaseResult<ResultadoSubmissao>> {
    return this.http.put<BaseResult<ResultadoSubmissao>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
