package com.intecsec.java.basic.net.mail.messages;

import java.io.FileOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class AttachmentMessage 
{
	public static MimeMessage generate() throws Exception
	{
		String from = "lychen@sei.ecnu.edu.cn "; // 发件人地址
		String to = "chenliangyu1980@126.com"; // 收件人地址
		
        String subject = "多附件邮件";        //邮件主题
        String body = "<a href=http://www.ecnu.edu.cn>" +
        			  "欢迎大家访问我们的网站</a></br>"; 
      
        // 创建Session实例对象
        Session session = Session.getDefaultInstance(new Properties());
     	// 创建MimeMessage实例对象
     	MimeMessage message = new MimeMessage(session);            
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
        		InternetAddress.parse(to));
        message.setSubject(subject);
        
        //创建代表邮件正文和附件的各个MimeBodyPart对象
        MimeBodyPart contentPart = createContent(body);
        MimeBodyPart attachPart1 = createAttachment("c:/temp/ecnu4.jpg");
        MimeBodyPart attachPart2 = createAttachment("c:/temp/ecnu5.jpg");
        
        //创建用于组合邮件正文和附件的MimeMultipart对象
        MimeMultipart allMultipart = new MimeMultipart("mixed");
        allMultipart.addBodyPart(contentPart);
        allMultipart.addBodyPart(attachPart1);
        allMultipart.addBodyPart(attachPart2);
        
        //设置整个邮件内容为最终组合出的MimeMultipart对象
        message.setContent(allMultipart);
        message.saveChanges();
        
        //message.writeTo(new FileOutputStream("e:/ComplexMessage.eml"));
        return message;
	}
	
	public static MimeBodyPart createContent(String body) throws Exception
	{
        MimeBodyPart htmlBodyPart = new MimeBodyPart();          
        htmlBodyPart.setContent(body,"text/html;charset=gb2312");
        return htmlBodyPart;
	}
	
	public static MimeBodyPart createAttachment(String filename) throws Exception
	{
		//创建保存附件的MimeBodyPart对象，并加入附件内容和相应信息
		MimeBodyPart attachPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(filename);
        attachPart.setDataHandler(new DataHandler(fds));
        attachPart.setFileName(fds.getName());
		return attachPart;
	}
}
