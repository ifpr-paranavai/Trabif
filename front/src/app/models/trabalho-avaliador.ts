import { Trabalho } from './trabalho';
import { Auditoria } from "./auditoria";
import { Usuario } from './usuario';

export class TrabalhoAvaliador extends Auditoria {
  id?: number;
  trabalho?: Trabalho;
  usuario?: Usuario;
}
