package com.yaoli.email;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by will on 2016/12/15.
 */
public class EmailSendTask implements Runnable{

    public static BlockingQueue<EmailEntity> emailQueue = new LinkedBlockingQueue<EmailEntity>();

    //session
    private static Session session;

    //初始化
    static {

        class MailAuthenticator extends Authenticator{
            String user=null;
            String pass="";
            public MailAuthenticator(String user,String pass){
                this.user=user;
                this.pass=pass;
            }
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,pass);
            }
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.auth", "true");

        //以下两者最重要
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");

        Authenticator auth = new MailAuthenticator("826431787@qq.com", "mxsygjjcmlncbcbb");

        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
    }



    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {

                EmailEntity entity = emailQueue.take();

                //发送邮件
                EmailSendTask.sendEmail(entity);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendEmail(EmailEntity entity){

        Message msg = new MimeMessage(session);
        try
        {
            msg.setSubject(entity.getSubject());

            msg.setFrom(new InternetAddress(entity.getFrom()));
            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(entity.getRecipient()));
            msg.setSentDate(new Date());

            MimeMultipart mm = entity.getMultipart();
            mm.setSubType(entity.getSubType());

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

    //            MimeBodyPart text = new MimeBodyPart();
//            text.setContent("<img src='cid:xx.jpg'>", "text/html");
//
//            MimeBodyPart image = new MimeBodyPart();
//
//            image.setDataHandler(new DataHandler(new FileDataSource("imgs/combined.png")));
//            image.setContentID("xx.jpg");

//            MimeMultipart mm = new MimeMultipart();
//            mm.addBodyPart(text);
//            mm.addBodyPart(image);
}
