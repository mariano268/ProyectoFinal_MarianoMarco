import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/models/persona.model';
import { PersonaService } from 'src/app/services/persona.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-acerca-de',
  templateUrl: './acerca-de.component.html',
  styleUrls: ['./acerca-de.component.css']
})
export class AcercaDeComponent implements OnInit{
  persona: Persona = new Persona(1,"","");
  isAdmin = false;

  constructor(private tokenService: TokenService , public personaService: PersonaService){}
  
  ngOnInit(): void {
    this.personaService.getPersona().subscribe(data => {this.persona = data});
    console.log(this.persona);

    if(this.tokenService.getToken() && this.tokenService.getAuthorities().length == 2) {
      this.isAdmin = true;
    }
  }

}
