import { Auditoria } from "./auditoria";

export class EmailTemplate extends Auditoria {
  id?: number;
  titulo?: string;
  mensagem?: string;
}
