import { Observable } from 'rxjs';

export interface BaseService {
  getAll(): Observable<any>;

  getById(id: number): Observable<any>;

  post(obj: any): Observable<any>;

  put(id: number, obj: any): Observable<any>;

  delete(id: number): Observable<any>;
}
