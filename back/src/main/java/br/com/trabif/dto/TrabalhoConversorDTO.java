package br.com.trabif.dto;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.trabif.domain.AutorTrabalho;
import br.com.trabif.domain.Trabalho;
import br.com.trabif.domain.Usuario;
import lombok.Data;

@Data
public class TrabalhoConversorDTO {
    private MultipartFile documento;
    private String trabalho;

    public Trabalho convertObj() {
        Trabalho trabalho = new Trabalho();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Gson gson = new Gson();
            trabalho = gson.fromJson(this.trabalho, Trabalho.class);
            
            trabalho.setPdf(this.documento.getBytes());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return trabalho;
    }
}