import { Evento } from './evento';
import { Usuario } from './usuario';
import { Permissao } from './permissao';
import { Auditoria } from "./auditoria";

export class PermissaoUsuario extends Auditoria {
  id?: number;
  permissaoDTO?: Permissao;
  usuarioDTO?: Usuario;
  eventoDTO?: Evento;
}
