package com.intecsec.java.basic.net.mail.messages;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;

public class HtmlMessage {
	public static MimeMessage generate() throws Exception 
	{
		String from = "lychen@sei.ecnu.edu.cn "; // 发件人地址
		String to = "chenliangyu1980@126.com"; // 收件人地址
		
		String subject = "HTML邮件";
		String body = "<a href=http://www.ecnu.edu.cn>" 
		  + "<h4>欢迎大家访问我们的网站</h4></a></br>" 
		  + "<img src=\"https://news.ecnu.edu.cn/_upload/article/images/2e/e2/6b554d034c9192101208c732195e/16a6ec66-6729-4469-a5f4-0435e0f2e66a.jpg\">";

		// 创建Session实例对象
		Session session = Session.getDefaultInstance(new Properties());
		// 创建MimeMessage实例对象
		MimeMessage message = new MimeMessage(session);
		// 设置发件人
		message.setFrom(new InternetAddress(from));
		// 设置收件人
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		// 设置发送日期
		message.setSentDate(new Date());
		// 设置邮件主题
		message.setSubject(subject);
		// 设置HTML格式的邮件正文
		message.setContent(body, "text/html;charset=gb2312");
		// 保存并生成最终的邮件内容
		message.saveChanges();

		// 把MimeMessage对象中的内容写入到文件中
		//msg.writeTo(new FileOutputStream("e:/HtmlMessage.eml"));
		return message;
	}
}
