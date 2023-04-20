import { Auditoria } from "./auditoria";
import { Categoria } from "./categoria";

export class Trabalho extends Auditoria {
  id?: number;
  titulo?: string;
  categoria?: Categoria;
}
