package com.yaoli.email;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class JavaMailWithAttachment
{
//    public Session getConfigurationSession(){
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.qq.com");
//        props.put("mail.smtp.auth", "true");
//        //以下两者最重要
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.transport.protocol", "smtp");
//
//
//        Authenticator auth = new MailAuthenticator("826431787@qq.com", "mxsygjjcmlncbcbb");
//
//        Session session = Session.getInstance(props, auth);
//        session.setDebug(true);
//
//        return session;
//    }
//
//
//    class MailAuthenticator extends Authenticator{
//        String user=null;
//        String pass="";
//        public MailAuthenticator(String user,String pass){
//            this.user=user;
//            this.pass=pass;
//        }
//        @Override
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(user,pass);
//        }
//    }

    public void sendEmail(){
        //获取配置
        Session session = null;//getConfigurationSession();

        //
        Message msg = new MimeMessage(session);
        try
        {
            msg.setSubject("天气预报");

            msg.setFrom(new InternetAddress("826431787@qq.com"));
            msg.setRecipient(RecipientType.TO, new InternetAddress("826431787@qq.com"));
            msg.setSentDate(new Date());
;
            MimeBodyPart text = new MimeBodyPart();
            text.setContent("<img src='cid:xx.jpg'>", "text/html");

            MimeBodyPart image = new MimeBodyPart();

            image.setDataHandler(new DataHandler(new FileDataSource("imgs/combined.png")));
            image.setContentID("xx.jpg");

            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(text);
            mm.addBodyPart(image);
            mm.setSubType("related");

            msg.setContent(mm);

            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(msg, msg.getAllRecipients());

            transport.close();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}