import { Auditoria } from "./auditoria";
import { Organizador } from "./organizador";

export class Evento extends Auditoria {
  id?: number;
  nome?: string;
  organizador?: Organizador;
  dataInicio?: Date;
  dataFim?: Date;
}
