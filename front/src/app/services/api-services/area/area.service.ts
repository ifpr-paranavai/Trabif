import { Injectable } from '@angular/core';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Area } from 'src/app/models/area';

@Injectable({
  providedIn: 'root'
})
export class AreaService implements BaseService {
  baseUrl = 'http://localhost:8080/api/area';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<Area>> {
    return this.http.get<BaseResults<Area>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<Area>> {
    return this.http.get<BaseResult<Area>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<Area>> {
    return this.http.post<BaseResult<Area>>(this.baseUrl, obj);
  }
  put(id: number, obj: any): Observable<BaseResult<Area>> {
    return this.http.put<BaseResult<Area>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
