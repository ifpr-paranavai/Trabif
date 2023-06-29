import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { TrabalhoAvaliador } from 'src/app/models/trabalho-avaliador';

@Injectable({
  providedIn: 'root'
})
export class TrabalhoAvaliadorService implements BaseService {

  baseUrl = 'http://localhost:8080/api/trabalhoAvaliador';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<TrabalhoAvaliador>> {
    return this.http.get<BaseResults<TrabalhoAvaliador>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<TrabalhoAvaliador>> {
    return this.http.get<BaseResult<TrabalhoAvaliador>>(this.baseUrl + '/' + id);
  }
  post(obj: TrabalhoAvaliador): Observable<BaseResult<TrabalhoAvaliador>> {
    return this.http.post<BaseResult<TrabalhoAvaliador>>(this.baseUrl, obj);
  }
  put(id: number, obj: TrabalhoAvaliador): Observable<BaseResult<TrabalhoAvaliador>> {
    return this.http.put<BaseResult<TrabalhoAvaliador>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
