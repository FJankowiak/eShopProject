package fr.adaming.service;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import fr.adaming.model.Client;

public class MailClient {
	
			public static void MailFacture(String mailRecup) {
			final String username = "phanuellesainteloi@gmail.com";
			final String password = "ARYA052230";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			
			// Get Session object.
					Session session = Session.getInstance(props, new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
			try {

				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);
				Multipart multipart = new MimeMultipart();

				// Set From: header field of the header.
				message.setFrom(new InternetAddress("phanuellesainteloi@gmail.com"));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRecup));

				// Set Subject: header field
				message.setSubject("Vaildation de votre commande");
				
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent("Bonjour,<br/> Nous vous remercions de votre achat. Vous pouvez venir r�cup�rer votre commande � l'adresse suivante : <br/> 55 Avenue des licornes <br/> 31000 TOULOUSE<br/> Vous trouverez ci-joint la facture de votre commande <br/>A bient�t pour de nouvelles commandes, <br/> Le service client", "text/html");
				 
				// creates body part for the attachment
				MimeBodyPart attachPart = new MimeBodyPart();
				String attachFile = "C:/Users/int0426/Desktop/FactureEshop.pdf";
				 
				DataSource source = new FileDataSource(attachFile);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(new File(attachFile).getName());
				 
				// adds parts to the multipart
				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(attachPart);
				 
				// sets the multipart as message's content
				message.setContent(multipart);

		         // Send message
		         Transport.send(message, message.getAllRecipients());
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		}

}
