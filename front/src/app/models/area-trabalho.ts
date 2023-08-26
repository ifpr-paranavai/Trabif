import { Area } from "./area";
import { Auditoria } from "./auditoria";
import { Trabalho } from "./trabalho";

export class AreaTrabalho extends Auditoria {
  id?: number;
  areaDTO?: Area;
  trabalhoDTO?: Trabalho;
}
