import { Auditoria } from "./auditoria";

export class Evento extends Auditoria {
  id?: number;
  nome?: string;
  dataInicio?: Date;
  dataFim?: Date;
}
