import { Component, OnInit } from '@angular/core';
import { Proyecto } from 'src/app/models/proyecto.model';
import { ProyectoService } from 'src/app/services/proyecto.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-proyectos',
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.css']
})
export class ProyectosComponent implements OnInit{
  proyecto: Proyecto[] = [];
  isAdmin = false;

  constructor(private tokenService: TokenService , public proyectoService: ProyectoService){}
  
  ngOnInit(): void {
    this.cargarProyecto();
    
    if(this.tokenService.getToken() && this.tokenService.getAuthorities().length == 2) {
      this.isAdmin = true;
    }
  }

  cargarProyecto() {
    this.proyectoService.lista().subscribe(data => {
      this.proyecto = data;
      console.log(this.proyecto);
    })
  }

  delete(id?: number) {
    if (id != undefined) {
      this.proyectoService.delete(id).subscribe(
        data => {
          this.cargarProyecto();
        }, err => {
          alert("No se pudo borrar la experiencia");
        }
      )
    }
  }
}
