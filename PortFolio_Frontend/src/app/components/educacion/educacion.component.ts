import { Component, OnInit } from '@angular/core';
import { Educacion } from 'src/app/models/educacion.model';
import { EducacionService } from 'src/app/services/educacion.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-educacion',
  templateUrl: './educacion.component.html',
  styleUrls: ['./educacion.component.css']
})
export class EducacionComponent implements OnInit{
  educacion: Educacion[] = [];
  isAdmin = false;

  constructor(private tokenService: TokenService , public educacionService: EducacionService){}
  
  ngOnInit(): void {
    this.cargarEducacion();
    if(this.tokenService.getToken() && this.tokenService.getAuthorities().length == 2) {
      this.isAdmin = true;
    }
  }

  cargarEducacion() {
    this.educacionService.lista().subscribe(data => {
      this.educacion = data;
      console.log(this.educacion);
    })
  }

  delete(id?: number) {
    if (id != undefined) {
      this.educacionService.delete(id).subscribe(
        data => {
          this.cargarEducacion();
        }, err => {
          alert("No se pudo borrar la experiencia");
        }
      )
    }
  }
}
