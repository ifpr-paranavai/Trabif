import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { AreaTrabalho } from 'src/app/models/area-trabalho';

@Injectable({
  providedIn: 'root'
})
export class AreaTrabalhoService implements BaseService {

  baseUrl = 'http://localhost:8080/api/areaTrabalho';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<AreaTrabalho>> {
    return this.http.get<BaseResults<AreaTrabalho>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<AreaTrabalho>> {
    return this.http.get<BaseResult<AreaTrabalho>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<AreaTrabalho>> {
    return this.http.post<BaseResult<AreaTrabalho>>(this.baseUrl, obj);
  }
  put(id: number, obj: any): Observable<BaseResult<AreaTrabalho>> {
    return this.http.put<BaseResult<AreaTrabalho>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
