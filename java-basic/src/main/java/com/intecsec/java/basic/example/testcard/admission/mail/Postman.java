package com.intecsec.java.basic.example.testcard.admission.mail;


import com.intecsec.java.basic.example.testcard.admission.po.AdmissionTicket;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Postman {

    private Session session = null;
    private Transport transport = null;

    public void init() throws Exception {
    	//设置邮件账号
        Properties props = new Properties();
        //设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        //2、创建定义整个应用程序所需的环境信息的 Session 对象
        session = Session.getDefaultInstance(props);
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        //4、根据session对象获取邮件传输对象Transport
        transport = session.getTransport();
        //设置发件人的账户名和密码
        transport.connect("1102266271@qq.com", "iwtcyqtoyijlfghi");
    }

    public void close() throws Exception {
        transport.close();
    }

      
    public void sendAll(List<AdmissionTicket> tickets) throws Exception {
    	if(null == session)
    	{
    		init(); //初始化
    	}
    	
        for (AdmissionTicket ticket : tickets) {
        	System.out.println("get");
            Message msg = formatAdmissionTicket(ticket);
            //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(msg, msg.getAllRecipients());
        }
    }

    private MimeMessage formatAdmissionTicket(AdmissionTicket ticket) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("1102266271@qq.com"));
        message.setRecipients(Message.RecipientType.TO, ticket.getEmail());
        message.setSubject("请查收准考证");
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("<p>请查收准考证</p>", "text/html;charset=UTF-8");
        MimeBodyPart attachment = new MimeBodyPart();
        byte[] data = readPdf(ticket.getCandidateNumber() + ".pdf");
        DataHandler dataHandler = new DataHandler(new ByteArrayDataSource(data, "application/pdf"));
        attachment.setDataHandler(dataHandler);
        // 设置附件的文件名（需要编码）
        attachment.setFileName(MimeUtility.encodeText("准考证.pdf"));
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(attachment);
        mm.setSubType("mixed");
        message.setContent(mm);
        message.saveChanges();
        message.setHeader("Content-Transfer-Encoding", "base64");
        return message;
    }   
    
    private byte[] readPdf(String fileName) throws Exception
    {
    	InputStream in = new FileInputStream(new File(fileName));
    	byte[] bytes= null;
    	
    	bytes= new byte[in.available()];    //in.available()是得到文件的字节数
    	in.read(bytes);    //把文件的字节填充bytes数组中
    	in.close(); 
    	return bytes;
    }
}
