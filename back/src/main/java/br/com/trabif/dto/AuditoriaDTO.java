package br.com.trabif.dto;

import java.util.Date;
import lombok.Data;

@Data
public class AuditoriaDTO {
	private String usuarioCadastro;
    private Date dataCadastro;
    private String usuarioUltimaAlteracao;
    private Date dataUltimaAlteracao;
	private char status;
}
