import { Auditoria } from "./auditoria";

export class Usuario extends Auditoria {
  id?: number;
  nome?: string;
  cpf?: string;
  email?: string;
  senha?: string;
  codigoRecuperacaoSenha?: string;
  dataEnvioCodigo?: Date;
  constructor() {
    super();
  }
}
