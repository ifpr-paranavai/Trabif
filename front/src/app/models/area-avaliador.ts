import { Area } from "./area";
import { Auditoria } from "./auditoria";
import { Usuario } from "./usuario";

export class AreaAvaliador extends Auditoria {
  id?: number;
  areaDTO?: Area;
  usuarioDTO?: Usuario;
}
