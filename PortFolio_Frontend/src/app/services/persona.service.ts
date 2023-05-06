import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Persona } from '../models/persona.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  URL = "http://localhost:8080/persona/";

  constructor(private httpClient: HttpClient) { }

  public detail(id: number): Observable<Persona>{
    return this.httpClient.get<Persona>(this.URL + `detail/${id}`);
  }

  public update(id: number , persona: Persona): Observable<any>{
    return this.httpClient.put<any>(this.URL + `editar/${id}` , persona);
  }
}
