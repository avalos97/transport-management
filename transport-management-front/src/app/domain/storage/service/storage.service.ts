import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Storage } from '../interface/storage';
import { StorageType } from '../interface/storage-type';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  apiUrl = environment.REST_API_URL + 'storage';

  constructor(private http: HttpClient) { }

  getAllStorages(): Observable<Storage[]> {
    return this.http.get<Storage[]>(this.apiUrl);
  }

  getStorageById(id: number): Observable<Storage> {
    return this.http.get<Storage>(`${this.apiUrl}/${id}`);
  }

  addStorage(storage: Storage): Observable<Storage> {
    return this.http.post<Storage>(this.apiUrl, storage);
  }

  updateStorage(storage: Storage): Observable<Storage> {
    return this.http.put<Storage>(`${this.apiUrl}/${storage.id}`, storage);
  }

  getAllStoragesType(): Observable<StorageType[]> {
    return this.http.get<StorageType[]>(this.apiUrl + '/types');
  }

  delete(id: number): Observable<Storage> {
    return this.http.delete<Storage>(`${this.apiUrl}/${id}`);
  }
}
