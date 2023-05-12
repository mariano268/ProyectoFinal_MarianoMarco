import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Experiencia } from 'src/app/models/experiencia.model';
import { ExperienciaService } from 'src/app/services/experiencia.service';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-edit-experiencia',
  templateUrl: './edit-experiencia.component.html',
  styleUrls: ['./edit-experiencia.component.css']
})
export class EditExperienciaComponent {
  experiencia: Experiencia = new Experiencia("","","");


  constructor(private activatedRouter: ActivatedRoute, 
    private experienciaService: ExperienciaService, 
    private router: Router,
    public imageService: ImageService) {}

  ngOnInit(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.experienciaService.detail(id).subscribe(data => {
      this.experiencia = data;
    }, err => {
      alert("Error al modificar");
      this.router.navigate(['']);
    })
  }

  onUpdate(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    if(this.imageService.url != "") {
      this.experiencia.imagen = this.imageService.url;
    }
    this.experienciaService.update(id , this.experiencia).subscribe(data => {
      this.router.navigate(['']);
    }, err => {
      alert ("Error al modificar la experiencia");
      this.router.navigate(['']);
    })
  }

  uploadImage($event:any) {
    const id = this.activatedRouter.snapshot.params['id'];
    const name = "fotoExperiencia_" + id;
    this.imageService.uploadImageExperiencia($event, name , id);
  }
}
