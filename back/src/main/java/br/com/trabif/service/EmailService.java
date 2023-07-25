package br.com.trabif.service;

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
}
