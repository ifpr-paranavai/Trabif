import { Usuario } from './usuario';
import { Evento } from './evento';
import { Auditoria } from "./auditoria";

export class EventoOrganizador extends Auditoria {
  id?: number;
  eventoDTO?: Evento;
  usuarioDTO?: Usuario;
}
