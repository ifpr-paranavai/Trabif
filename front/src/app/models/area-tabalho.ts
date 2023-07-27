import { Area } from "./area";
import { Auditoria } from "./auditoria";
import { Trabalho } from "./trabalho";

export class AreaTabalho extends Auditoria {
  id?: number;
  areaDTO?: Area;
  tabalhoDTO?: Trabalho;
}
