import { EmailTemplate } from './email-template';
import { Auditoria } from "./auditoria";
import { Evento } from "./evento";

export class EventoEmailTemplate extends Auditoria {
  id?: number;
  eventoDTO?: Evento;
  emailTemplateDTO?: EmailTemplate;
}
