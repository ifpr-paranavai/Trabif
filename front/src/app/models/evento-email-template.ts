import { EmailTemplate } from './email-template';
import { Auditoria } from "./auditoria";
import { Evento } from "./evento";

export class EventoEmailTemplate extends Auditoria {
  id?: number;
  evento?: Evento;
  emailTemplate?: EmailTemplate;
}
