import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Habilidad } from '../models/habilidad.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HabilidadService {
  URL = "http://localhost:8080/habilidad/";

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Habilidad[]>{
    return this.httpClient.get<Habilidad[]>(this.URL + `lista`);
  }

  public detail(id: number): Observable<Habilidad>{
    return this.httpClient.get<Habilidad>(this.URL + `detail/${id}`);
  }

  public update(id: number , habilidad: Habilidad): Observable<any>{
    return this.httpClient.put<any>(this.URL + `editar/${id}` , habilidad);
  }

  public delete(id: number): Observable<any>{
    return this.httpClient.delete<any>(this.URL + `eliminar/${id}`);
  }
}