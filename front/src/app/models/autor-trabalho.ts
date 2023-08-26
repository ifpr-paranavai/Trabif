import { Auditoria } from "./auditoria";
import { Trabalho } from "./trabalho";
import { Usuario } from "./usuario";

export class AutorTrabalho extends Auditoria {
  id?: number;
  usuarioDTO?: Usuario;
  trabalhoDTO?: Trabalho;
}
