package com.mucfc.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil {
    
    public static void sendMail(String to, String code) throws Exception {
        //1.创建连接对象，连接到邮箱服务器
        Properties props = new Properties();
        // 指定协议  
        props.put("mail.transport.protocol", "smtp");  
        // 主机 smtp.qq.com  
        //props.put("mail.smtp.host", "smtp.exmail.qq.com");
        props.put("mail.smtp.host", "smtp.qq.com");  
        // 端口  
        props.put("mail.smtp.port", 465);  
        // 用户密码认证  
        props.put("mail.smtp.auth", "true");  
        // 调试模式  
        //props.put("mail.debug", "true");  //会把邮件内容输出到控制台
        //ssl加密
        props.put("mail.smtp.ssl.enable", "true"); 
        
        InternetAddress sendMan = new InternetAddress("2909109450@qq.com");
        
        //1.创建邮件会话  
        Session session = Session.getInstance(props);
        
        //2.创建邮件对象
        Message message = new MimeMessage(session);
        //2.1设值发件人
        message.setFrom(sendMan);
        //2.2设值收件人
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        //2.3设值邮件主题
        message.setSubject("激活邮件");
        //2.4设值邮件正文
        message.setContent("<h1>来自xxx的激活邮件</h1><h3><a href='http://www.baidu.com'>百度</a></h3>", "text/html;charset=utf-8");
        
        //3.发送一封激活邮件
        Transport trans = session.getTransport();
        trans.connect("2909109450@qq.com", "uofiqigdxeafddcc"); 
        trans.sendMessage(message, message.getAllRecipients());
        trans.close();
        
    }

}
