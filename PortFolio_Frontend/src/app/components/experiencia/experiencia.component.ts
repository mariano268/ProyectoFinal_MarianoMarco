import { Component, OnInit } from '@angular/core';
import { Experiencia } from 'src/app/models/experiencia.model';
import { ExperienciaService } from 'src/app/services/experiencia.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent implements OnInit{
  experiencia: Experiencia[] = [];
  isAdmin = false;

  constructor(private tokenService: TokenService , public experienciaService: ExperienciaService){}
  
  ngOnInit(): void {
    this.cargarExperiencia();
    
    if(this.tokenService.getToken() && this.tokenService.getAuthorities().length == 2) {
      this.isAdmin = true;
    }

  }

  cargarExperiencia() {
    this.experienciaService.lista().subscribe(data => {
      this.experiencia = data;
      console.log(this.experiencia);
    })
  }
}
