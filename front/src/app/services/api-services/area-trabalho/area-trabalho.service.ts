import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { AreaTabalho } from 'src/app/models/area-tabalho';

@Injectable({
  providedIn: 'root'
})
export class AreaTrabalhoService implements BaseService {

  baseUrl = 'http://localhost:8080/api/areaTrabalho';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<AreaTabalho>> {
    return this.http.get<BaseResults<AreaTabalho>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<AreaTabalho>> {
    return this.http.get<BaseResult<AreaTabalho>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<AreaTabalho>> {
    return this.http.post<BaseResult<AreaTabalho>>(this.baseUrl, obj);
  }
  put(id: number, obj: any): Observable<BaseResult<AreaTabalho>> {
    return this.http.put<BaseResult<AreaTabalho>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
