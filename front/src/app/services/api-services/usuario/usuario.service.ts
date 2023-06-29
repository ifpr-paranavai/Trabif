import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { Usuario } from 'src/app/models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService implements BaseService {

  baseUrl = 'http://localhost:8080/api/usuario';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<Usuario>> {
    return this.http.get<BaseResults<Usuario>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<Usuario>> {
    return this.http.get<BaseResult<Usuario>>(this.baseUrl + '/' + id);
  }
  post(obj: Usuario): Observable<BaseResult<Usuario>> {
    return this.http.post<BaseResult<Usuario>>(this.baseUrl, obj);
  }
  put(id: number, obj: Usuario): Observable<BaseResult<Usuario>> {
    return this.http.put<BaseResult<Usuario>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
