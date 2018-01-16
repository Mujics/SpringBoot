package web.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import web.model.Email;

@Component
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;

	

}
