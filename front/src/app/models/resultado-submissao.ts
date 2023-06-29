import { Auditoria } from "./auditoria";

export class ResultadoSubmissao extends Auditoria {
  id?: number;
  resultado?: number;

	confianca?: number;

	comentarioAutor?: string;

	comentarioOrganizador?: string;
}
