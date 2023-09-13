import { Auditoria } from "./auditoria";
import { Categoria } from "./categoria";
import { Evento } from "./evento";

export class Trabalho extends Auditoria {
  id?: number;
  titulo?: string;
  categoriaDTO?: Categoria;
  eventoDTO?: Evento;
  pdf?: Blob;
  resultado?: string;
}
