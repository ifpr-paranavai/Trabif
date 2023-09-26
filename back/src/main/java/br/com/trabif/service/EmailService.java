package br.com.trabif.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.trabif.domain.EventoEmailTemplate;
import br.com.trabif.dto.EmailTemplateDTO;
import br.com.trabif.dto.EventoEmailTemplateDTO;
import freemarker.template.Configuration;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configuration fmConfiguration;

	@Value("${spring.mail.username}")
	private String remetente;

	public String enviarEmailTexto(String destinatario, String titulo, String messagem) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(remetente);
			simpleMailMessage.setTo(destinatario);
			simpleMailMessage.setSubject(titulo);
			simpleMailMessage.setText(messagem);
			javaMailSender.send(simpleMailMessage);
			return "Email enviado";
		} catch (Exception e) {
			return "Erro ao enviar e-mail";
		}
	}

	public void enviarEmailTemplate(String destinatario, Map<String, String> propriedades) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			String titulo = "titulo";
			if(propriedades.get(titulo) != null) {
				mimeMessageHelper.setSubject(propriedades.get(titulo).toString());
				propriedades.remove(titulo);
			}
			mimeMessageHelper.setFrom(remetente);
			mimeMessageHelper.setTo(destinatario);

			mimeMessageHelper.setText(getConteudoTemplate(propriedades), true);

			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String getConteudoTemplate(Map<String, String> model) {
		StringBuffer content = new StringBuffer();
		String emailString = new String();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(fmConfiguration.getTemplate("email-template-base.flth"), model));
			emailString = content.toString();
			for(String key : model.keySet()) {
				String target = "[" + key + "]";
				emailString = emailString.replace(target, model.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailString;
	}

	public void tratarEmail(String email, Map<String, Object> map) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
		}

		Map<String, String> propriedades = new HashMap<>();

		String mensagemString = "mensagem";
		if(map.get(mensagemString) != null) {
			propriedades.put(mensagemString, (String) map.get(mensagemString));
			map.remove(mensagemString);
		}

		propriedades.put("emailUsuario", email);
		
		String nomeEventoString = "nomeEvento";
		if(map.get(nomeEventoString) != null) {
			propriedades.put(nomeEventoString, (String) map.get(nomeEventoString));
			map.remove(nomeEventoString);
		}

		propriedades.put("titulo", "Resultado Final do Trabalho do evento " + propriedades.get(nomeEventoString));

		String nomeUsuarioString = "nomeUsuario";
		if(map.get(nomeUsuarioString) != null) {
			propriedades.put(nomeUsuarioString, (String) map.get(nomeUsuarioString));
			map.remove(nomeUsuarioString);
		}
		
		String tituloTrabalhoString = "tituloTrabalho";
		if(map.get(tituloTrabalhoString) != null) {
			propriedades.put(tituloTrabalhoString, (String) map.get(tituloTrabalhoString));
			map.remove(tituloTrabalhoString);
		}

		String resultadoFinalTrabalhoString = "resultadoFinalTrabalho";
		if(map.get(resultadoFinalTrabalhoString) != null) {
			propriedades.put(resultadoFinalTrabalhoString, (String) map.get(resultadoFinalTrabalhoString));
			map.remove(resultadoFinalTrabalhoString);
		}

		this.enviarEmailTemplate(email, propriedades);
		return;
	}
}
