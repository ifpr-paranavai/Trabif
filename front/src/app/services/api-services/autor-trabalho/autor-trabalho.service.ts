import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseResult, BaseResults, BaseService } from '../base/base.service';
import { AutorTrabalho } from 'src/app/models/autor-trabalho';

@Injectable({
  providedIn: 'root'
})
export class AutorTrabalhoService implements BaseService {

  baseUrl = 'http://localhost:8080/api/autorTrabalho';
  constructor(private http: HttpClient) { }
  getAll(): Observable<BaseResults<AutorTrabalho>> {
    return this.http.get<BaseResults<AutorTrabalho>>(this.baseUrl);
  }
  getById(id: number): Observable<BaseResult<AutorTrabalho>> {
    return this.http.get<BaseResult<AutorTrabalho>>(this.baseUrl + '/' + id);
  }
  post(obj: any): Observable<BaseResult<any>> {
    return this.http.post<BaseResult<any>>(this.baseUrl, obj);
  }
  postWithFile(obj: any, file: File): Observable<any> {
    let formData = new FormData();
    formData.append('documento', file);
    formData.append('autorTrabalho', JSON.stringify(obj));
    return this.http.post<any>(this.baseUrl, formData);
  }
  put(id: number, obj: AutorTrabalho): Observable<BaseResult<AutorTrabalho>> {
    return this.http.put<BaseResult<AutorTrabalho>>(this.baseUrl + '/' + id, obj);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAutorTrabalhoByIdUsuarioAndIdEvento(idUsuario: number, idEvento: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/usuario/" + idUsuario + "/evento/" + idEvento);
  }
  getAutorTrabalhoByIdTrabalho(idTrabalho: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/trabalho/" + idTrabalho);
  }
}
