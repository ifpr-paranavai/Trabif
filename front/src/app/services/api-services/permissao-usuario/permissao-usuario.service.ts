import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { PermissaoUsuario } from 'src/app/models/permissao-usuario';

@Injectable({
  providedIn: 'root'
})
export class PermissaoUsuarioService implements BaseService {

  baseUrl = 'http://localhost:8080/api/permissaoUsuario';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<PermissaoUsuario>> {
    return this.http.get<BaseResults<PermissaoUsuario>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<PermissaoUsuario>> {
    return this.http.get<BaseResult<PermissaoUsuario>>(this.baseUrl + '/' + id);
  }
  post(obj: PermissaoUsuario): Observable<BaseResult<PermissaoUsuario>> {
    return this.http.post<BaseResult<PermissaoUsuario>>(this.baseUrl, obj);
  }
  put(id: number, obj: PermissaoUsuario): Observable<BaseResult<PermissaoUsuario>> {
    return this.http.put<BaseResult<PermissaoUsuario>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
