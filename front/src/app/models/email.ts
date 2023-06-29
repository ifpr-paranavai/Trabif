import { Auditoria } from "./auditoria";

export class Email extends Auditoria {
  id?: number;
  destinatario?: string;
  titulo?: string;
  mensagem?: string;
}
