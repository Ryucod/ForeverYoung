package forever.young.email;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;

public class EmailSender {
	@Autowired
	private JavaMailSender mailSender;
	
	public void SendMail(Email email) throws Exception {
		System.out.println(email);
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			msg.setSubject(email.getSubject());
			msg.setText(email.getContent());
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
			mailSender.send(msg);
			
		}catch(MessagingException e) {
			System.out.println("�޼��� �ͼ���");
			e.printStackTrace();
			
		}
		
	}

}
