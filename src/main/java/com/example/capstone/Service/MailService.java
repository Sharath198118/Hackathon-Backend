package com.example.capstone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.capstone.Exceptions.FailedToSendEmailException;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	
	@Async 
	public void sendEmail(String receiver, String body, String Subject) throws FailedToSendEmailException {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(receiver);
			mailMessage.setText(body);
			mailMessage.setSubject(Subject);
			javaMailSender.send(mailMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedToSendEmailException("Failed to Send Email, Retry");
		}
	}
}
