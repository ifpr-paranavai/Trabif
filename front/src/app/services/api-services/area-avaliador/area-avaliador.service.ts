import { Injectable } from '@angular/core';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AreaAvaliador } from 'src/app/models/area-avaliador';

@Injectable({
  providedIn: 'root'
})
export class AreaAvaliadorService implements BaseService {
  baseUrl = 'http://localhost:8080/api/area';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<AreaAvaliador>> {
    return this.http.get<BaseResults<AreaAvaliador>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<AreaAvaliador>> {
    return this.http.get<BaseResult<AreaAvaliador>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<AreaAvaliador>> {
    return this.http.post<BaseResult<AreaAvaliador>>(this.baseUrl, obj);
  }
  put(id: number, obj: any): Observable<BaseResult<AreaAvaliador>> {
    return this.http.put<BaseResult<AreaAvaliador>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
