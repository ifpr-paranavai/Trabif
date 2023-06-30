import { Auditoria } from "./auditoria";
import { Categoria } from "./categoria";
import { Evento } from "./evento";

export class Trabalho extends Auditoria {
  id?: number;
  titulo?: string;
  categoria?: Categoria;
  evento?: Evento;
  pdf?: Blob;
}
