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

  userPermissionEvent: PermissaoUsuario[] = [new PermissaoUsuario()];

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

  getPermissaoUsuarioByIdUsuarioAndIdEvento(idUsuario: number, idEvento: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/usuario/" + idUsuario + "/evento/" + idEvento);
  }
  getAutoresByIdEvento(idEvento: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/autores/' + idEvento);
  }
  getOrganizadoresByIdEvento(idEvento: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/organizadores/' + idEvento);
  }
  getAvaliadoresByIdEvento(idEvento: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/avaliadores/' + idEvento);
  }
}
