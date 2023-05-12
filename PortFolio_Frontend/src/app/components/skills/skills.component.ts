import { Component, OnInit } from '@angular/core';
// Modulo para el tamaño de la pantalla.
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { distinctUntilChanged, tap } from 'rxjs/operators';
import { Habilidad } from 'src/app/models/habilidad.model';
import { TokenService } from 'src/app/services/token.service';
import { HabilidadService } from 'src/app/services/habilidad.service';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {
  habilidad: Habilidad[] = [];
  isAdmin = false;
  Breakpoints = Breakpoints;
  currentBreakpoint:string = '';

  readonly breakpoint$ = this.breakpointObserver
    .observe(['(min-width: 368px)' , '(min-width: 460px)' , '(min-width: 768px)' , '(min-width: 992px)' , '(min-width: 1200px)'])
    .pipe(
      tap(value => console.log(value)),
      distinctUntilChanged()
    );

  constructor(private breakpointObserver: BreakpointObserver , private tokenService: TokenService , public habilidadService: HabilidadService) { }

  ngOnInit(): void {
    this.cargarHabilidad();
    
    if(this.tokenService.getToken() && this.tokenService.getAuthorities().length == 2) {
      this.isAdmin = true;
    }

    this.breakpoint$.subscribe(() =>
      this.breakpointChanged()
    );
  }
  
  private breakpointChanged() {
    if(this.breakpointObserver.isMatched('(min-width: 1200px)')) {
      this.currentBreakpoint = "1200px";
    } else if(this.breakpointObserver.isMatched('(min-width: 992px)')) {
      this.currentBreakpoint = "992px";
    } else if(this.breakpointObserver.isMatched('(min-width: 768px)')) {
      this.currentBreakpoint = "768px";
    } else if(this.breakpointObserver.isMatched('(min-width: 460px)')) {
      this.currentBreakpoint = "460px";
    } else if(this.breakpointObserver.isMatched('(min-width: 1px)')) {
      this.currentBreakpoint = "1px";
    }
  }

  cargarHabilidad() {
    this.habilidadService.lista().subscribe(data => {
      this.habilidad = data;
      console.log(this.habilidad);
    })
  }

  delete(id?: number) {
    if (id != undefined) {
      this.habilidadService.delete(id).subscribe(
        data => {
          this.cargarHabilidad();
        }, err => {
          alert("No se pudo borrar la experiencia");
        }
      )
    }
  }


}
