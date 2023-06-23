import { Area } from "./area";
import { Auditoria } from "./auditoria";
import { Usuario } from "./usuario";

export class AreaAvalidor extends Auditoria {
  id?: number;
  area?: Area;
  usuario?: Usuario;
}
