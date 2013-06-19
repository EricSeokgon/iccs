package kjf.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendMailUtil {

	private String smtpHost;
	private String auth_id;
	private String auth_pass;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String from;
	private String content;
	private String fileName;
	private String filePath;

	/*
	 * ���Ϻ����� ����� �ϴ� Ŭ����
	 * �����ڷ� �⺻ ������ ������ �ʼ� �׸� �ʵ常�� �ް�
	 * ������ �ΰ� ����� �ϴ� �ʵ�(÷�� ����, ����, ���� ����)��
	 * ���� setter �޼���� ������ �Ѵ�.
	 */
	public SendMailUtil(String smtpHost, String auth_id, String auth_pass, String to, String from, String subject, String content){
		this.smtpHost = smtpHost;
		this.auth_id = auth_id;
		this.auth_pass = auth_pass;
		this.to = to.replaceAll(";",",");
		this.from = from;
		this.subject = subject;
		this.content = content;
	}

	public SendMailUtil(String smtpHost, String auth_id, String auth_pass, String from, String subject, String content){
		this.smtpHost = smtpHost;
		this.auth_id = auth_id;
		this.auth_pass = auth_pass;
		this.from = from;
		this.subject = subject;
		this.content = content;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setCc(String cc) {
		this.cc = cc.replaceAll(";",",");
	}
	public void setBcc(String bcc) {
		this.bcc = bcc.replaceAll(";",",");
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}	

	public boolean sendMail()throws Exception{
		
		boolean success = false;
					
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
		
		MyAuthenticator auth = null;
		auth = new MyAuthenticator(auth_id, auth_pass);
		
		try{

			Session session= Session.getInstance(props, auth);

			MimeMessage message = new MimeMessage(session);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(content, "text/html;charset=EUC-KR");
			multipart.addBodyPart(messageBodyPart);		
			
			//÷�� ������ �����Ҷ�..
			if(filePath!=null){
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(filePath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(new String(fileName.getBytes("KSC5601"),"8859_1"));				
				multipart.addBodyPart(messageBodyPart);				
			}
            
			InternetAddress[] tos = InternetAddress.parse(to);	
			message.setRecipients(Message.RecipientType.TO, tos);
			
			//������ �����Ҷ�..
			if(cc!=null){
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			}
			
			//�������� �����Ҷ�..
			if(bcc!=null) {				
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
			}


			message.setFrom(new InternetAddress(from));			
			message.setSubject(subject,"EUC-KR");
			message.setContent(multipart);
			
			Transport.send(message);

			success = true;
		}catch(Exception e){
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}// end sendMail
	
    
	public static void main(String[] arg) throws Exception{
		/*
		SendMailUtil smu = new SendMailUtil("mail1.pktcorp.com", "ysyun", "ys0500", "ysvip@nate.com", "ysyun@pktcorp.com", "ok", "ok");
		System.out.println(smu.sendMail());
		*/
	}
	
}
