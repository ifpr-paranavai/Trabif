import { Auditoria } from "./auditoria";
import { Trabalho } from "./trabalho";
import { Usuario } from "./usuario";

export class AutorTabalho extends Auditoria {
  id?: number;
  usuarioDTO?: Usuario;
  tabalhoDTO?: Trabalho;
}
