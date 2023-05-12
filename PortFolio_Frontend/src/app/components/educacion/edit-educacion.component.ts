import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Educacion } from 'src/app/models/educacion.model';
import { EducacionService } from 'src/app/services/educacion.service';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-edit-educacion',
  templateUrl: './edit-educacion.component.html',
  styleUrls: ['./edit-educacion.component.css']
})
export class EditEducacionComponent {
  educacion: Educacion = new Educacion("","","");

  constructor(private activatedRouter: ActivatedRoute, 
    private educacionService: EducacionService, 
    private router: Router,
    public imageService: ImageService) {}

    ngOnInit(): void {
      this.imageService.clearUrl();
      const id = this.activatedRouter.snapshot.params['id'];
      this.educacionService.detail(id).subscribe(data => {
        this.educacion = data;
      }, err => {
        alert("Error al modificar");
        this.router.navigate(['']);
      })
    }

    onUpdate(): void {
      const id = this.activatedRouter.snapshot.params['id'];
      if(this.imageService.url != "") {
        this.educacion.imagen = this.imageService.url;
      }
      this.educacionService.update(id , this.educacion).subscribe(data => {
        this.router.navigate(['']);
      }, err => {
        alert ("Error al modificar la educacion");
        this.router.navigate(['']);
      })
    }

    uploadImage($event:any) {
      const id = this.activatedRouter.snapshot.params['id'];
      const name = "fotoEducacion_" + id;
      this.imageService.uploadImageEducacion($event, name , id);
    }
}
