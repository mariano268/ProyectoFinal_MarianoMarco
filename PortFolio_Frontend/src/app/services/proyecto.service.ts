import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Proyecto } from '../models/proyecto.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProyectoService {
  URL = "http://localhost:8080/proyecto/";

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Proyecto[]>{
    return this.httpClient.get<Proyecto[]>(this.URL + `lista`);
  }

  public detail(id: number): Observable<Proyecto>{
    return this.httpClient.get<Proyecto>(this.URL + `detail/${id}`);
  }

  public update(id: number , proyecto: Proyecto): Observable<any>{
    return this.httpClient.put<any>(this.URL + `editar/${id}` , proyecto);
  }

  public delete(id: number): Observable<any>{
    return this.httpClient.delete<any>(this.URL + `eliminar/${id}`);
  }
}