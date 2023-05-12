import { Injectable } from '@angular/core';
import { Storage, getDownloadURL, list, ref, uploadBytes } from '@angular/fire/storage';


@Injectable({
  providedIn: 'root'
})
export class ImageService {
  url: string = ";"
  constructor(private storage: Storage) { }

  public uploadImage($event: any, name: string) {
    const file = $event.target.files[0];
    const imgRef = ref(this.storage, `imagen/`+ name);
    uploadBytes(imgRef,file)
    .then(response => {this.getImages()})
    .catch(error => console.log(error))
  }

  public uploadImageExperiencia($event: any, name: string , id: number) {
    const file = $event.target.files[0];
    const imgRef = ref(this.storage, `experiencia/`+ name);
    uploadBytes(imgRef,file)
    .then(response => {this.getImagesExperiencia(id)})
    .catch(error => console.log(error))
  }

  public uploadImageEducacion($event: any, name: string , id: number) {
    const file = $event.target.files[0];
    const imgRef = ref(this.storage, `educacion/`+ name);
    uploadBytes(imgRef,file)
    .then(response => {this.getImagesEducacion(id)})
    .catch(error => console.log(error))
  }

  public uploadImageProyecto($event: any, name: string , id: number) {
    const file = $event.target.files[0];
    const imgRef = ref(this.storage, `proyecto/`+ name);
    uploadBytes(imgRef,file)
    .then(response => {this.getImagesProyecto(id)})
    .catch(error => console.log(error))
  }

  getImages() {
    const imagesRef = ref(this.storage, "imagen");
    list(imagesRef)
    .then(async response => {
      for(let item of response.items) {
        this.url = await getDownloadURL(item);
        console.log("La url es: " + this.url);
      }
    })
    .catch(error => console.log(error))
  }

  getImagesExperiencia(id: number) {
    const imagesRef = ref(this.storage, "experiencia");
    list(imagesRef)
    .then(async response => {
      for(let item of response.items) {
        if(item.name == "fotoExperiencia_" + id) {
          this.url = await getDownloadURL(item);
          console.log("La url es: " + this.url);
        }
      }
    }
    )
    .catch(error => console.log(error))
  }

  getImagesEducacion(id: number) {
    const imagesRef = ref(this.storage, "educacion");
    list(imagesRef)
    .then(async response => {
      for(let item of response.items) {
        if(item.name == "fotoEducacion_" + id) {
          this.url = await getDownloadURL(item);
          console.log("La url es: " + this.url);
        }
      }
    }
    )
    .catch(error => console.log(error))
  }

  getImagesProyecto(id: number) {
    const imagesRef = ref(this.storage, "proyecto");
    list(imagesRef)
    .then(async response => {
      for(let item of response.items) {
        if(item.name == "fotoProyecto_" + id) {
          this.url = await getDownloadURL(item);
          console.log("La url es: " + this.url);
        }
      }
    }
    )
    .catch(error => console.log(error))
  }

  clearUrl() {
    this.url = "";
  }
}
