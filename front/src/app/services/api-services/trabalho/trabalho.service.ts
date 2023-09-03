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
  getAllByCategoryId(id: number): Observable<BaseResults<Trabalho>> {
    return this.http.get<BaseResults<Trabalho>>(this.baseUrl + '/categoria/' + id);
  }
  getAllByEventId(id: number): Observable<BaseResults<Trabalho>> {
    let page = {
      'page': 0,
      'size': 1000
    };
    return this.http.get<BaseResults<Trabalho>>(this.baseUrl + '/evento/' + id, {params: page});
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

  postWithFile(obj: any, file: File): Observable<any> {
    let formData = new FormData();
    formData.append('documento', file);
    formData.append('trabalho', JSON.stringify(obj));
    return this.http.post<any>(this.baseUrl, formData);
  }
}
