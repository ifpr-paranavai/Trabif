import { Area } from "./area";
import { Auditoria } from "./auditoria";
import { Avaliador } from "./avaliador";

export class AreaAvalidor extends Auditoria {
  id?: number;
  area?: Area;
  avaliador?: Avaliador;
}
