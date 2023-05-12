import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Habilidad } from 'src/app/models/habilidad.model';
import { HabilidadService } from 'src/app/services/habilidad.service';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-edit-skills',
  templateUrl: './edit-skills.component.html',
  styleUrls: ['./edit-skills.component.css']
})
export class EditSkillsComponent {
  habilidad: Habilidad = new Habilidad("",0);

  constructor(private activatedRouter: ActivatedRoute, 
    private habilidadService: HabilidadService, 
    private router: Router) {}

    ngOnInit(): void {
      const id = this.activatedRouter.snapshot.params['id'];
      this.habilidadService.detail(id).subscribe(data => {
        this.habilidad = data;
      }, err => {
        alert("Error al modificar");
        this.router.navigate(['']);
      })
    }

    onUpdate(): void {
      const id = this.activatedRouter.snapshot.params['id'];
      this.habilidadService.update(id , this.habilidad).subscribe(data => {
        this.router.navigate(['']);
      }, err => {
        alert ("Error al modificar la experiencia");
        this.router.navigate(['']);
      })
    }
}
