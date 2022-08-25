package com.barclays.homeloan.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * To send an email
 * @author bharat
 *
 */
@Service
public class EmailSenderService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		
		javaMailSender.send(message);
		System.out.println("Mail Sent!!!");
	}
}