import {Component, OnInit} from '@angular/core';
import { Categoria} from "../categoria";
import {CategoriaService} from "../categoria.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  categorias: Categoria[] = [];
  selectedCategoryId: number;

  constructor(public categoriaService:CategoriaService) { }

  ngOnInit(): void {
    this.categoriaService.getAll().subscribe((data: Categoria[])=>{
      this.categorias= data;
      console.log(this.categorias);
    })
  }

  openDeleteModal(id: number) {
    this.selectedCategoryId = id; // Almacenar el id de la categoría seleccionada
  }

  deleteCategoria(id: any){
    this.categoriaService.delete(id).subscribe(res => {
      this.categorias = this.categorias.filter(cat => cat.id !== id);
      console.log('Categoria id =' + id + ' eliminada satisfactoriamente!');
    })

    const modal = document.getElementById('exampleModal');
    if (modal) {
      modal.classList.remove('show');
      modal.style.display = 'none';
      const backdrop = document.getElementsByClassName('modal-backdrop')[0];
      if (backdrop) {
        backdrop.remove();
      }
    }
  }


}

