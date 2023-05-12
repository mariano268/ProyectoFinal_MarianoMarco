import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Proyecto } from 'src/app/models/proyecto.model';
import { ImageService } from 'src/app/services/image.service';
import { ProyectoService } from 'src/app/services/proyecto.service';

@Component({
  selector: 'app-edit-proyectos',
  templateUrl: './edit-proyectos.component.html',
  styleUrls: ['./edit-proyectos.component.css']
})
export class EditProyectosComponent {
  proyecto: Proyecto = new Proyecto("","","");
  
  constructor(private activatedRouter: ActivatedRoute, 
    private proyectoService: ProyectoService, 
    private router: Router,
    public imageService: ImageService) {}

    ngOnInit(): void {
      this.imageService.clearUrl()
      const id = this.activatedRouter.snapshot.params['id'];
      this.proyectoService.detail(id).subscribe(data => {
        this.proyecto = data;
      }, err => {
        alert("Error al modificar");
        this.router.navigate(['']);
      })
    }

    onUpdate(): void {
      const id = this.activatedRouter.snapshot.params['id'];
      if(this.imageService.url != "") {
        this.proyecto.imagen = this.imageService.url;
      }
      this.proyectoService.update(id , this.proyecto).subscribe(data => {
        this.router.navigate(['']);
      }, err => {
        alert ("Error al modificar la experiencia");
        this.router.navigate(['']);
      })
    }

    uploadImage($event:any) {
      const id = this.activatedRouter.snapshot.params['id'];
      const name = "fotoProyecto_" + id;
      this.imageService.uploadImageProyecto($event, name , id);
    }
}
