package com.rahul.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {
	@SuppressWarnings("unused")
	public void sendMail(String to, String body, String subject) {
		String from = "rahulcprogrammer@gmail.com";
		
		try {
            // acquire a secure SMTPs session
            Properties pros = new Properties();
            pros.put("mail.transport.protocol", "smtps");
            pros.put("mail.smtps.host", "smtp.gmail.com");
            pros.put("mail.smtps.port", 465);
            pros.put("mail.smtps.auth", "true");
            pros.put("mail.smtps.quitwait", "false");
            Session session
                = Session.getDefaultInstance(pros);
            session.setDebug(true);
            // Wrap a message in session
            Message message = new MimeMessage(session);
            message.setSubject(subject);
 
           
			if (true) {
                message.setContent(body, "text/html");
            }
            else {
                message.setText(body);
            }
            // specify E-mail address of Sender and Receiver
            Address sender = new InternetAddress(from, "Rahul Chandel");
            Address receiver = new InternetAddress(to);
            message.setFrom(sender);
            message.setRecipient(Message.RecipientType.TO,
                                 receiver);
            // sending an E-mail
            try (Transport tt = session.getTransport()) {
                // acqruiring a connection to remote server
                tt.connect(from, "xzbnwigpgnhslfnw");
                tt.sendMessage(message,
                               message.getAllRecipients());
                System.out.println("E-Mail Sent Successfully");
            }
        }
        catch (MessagingException e) {
            System.out.println(e.toString());       
        }
        catch (UnsupportedEncodingException e) {
        	System.out.println(e.toString()); 
        }
	}
}
