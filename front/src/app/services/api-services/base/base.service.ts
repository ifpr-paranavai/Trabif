import { Observable } from 'rxjs';

export class BaseResults<K> {
  content: K[] | undefined;
}

export class BaseResult<K> {
  result: K | undefined;
}
export interface BaseService {
  getAll(): Observable<BaseResults<any>>;

  getById(id: number): Observable<BaseResult<any>>;

  post(obj: any): Observable<BaseResult<any>>;

  put(id: number, obj: any): Observable<BaseResult<any>>;

  delete(id: number): Observable<any>;
}
