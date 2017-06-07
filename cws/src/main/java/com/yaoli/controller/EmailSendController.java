package com.yaoli.controller;

import com.yaoli.aspect.annotation.validate.MustValidated;
import com.yaoli.aspect.annotation.validate.ValidateBody;
import com.yaoli.email.EmailEntity;
import com.yaoli.email.EmailSendTask;
import com.yaoli.vo.EmailSendVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by will on 2016/12/15.
 */
@Controller
@RequestMapping("/es")
public class EmailSendController {

    @RequestMapping("/gotoEmailSendTest.do")
    public String gotoEmailSendTest(){
        return "/emailsend/testemailsend";
    }

    @RequestMapping("/emailSendTest.do")
    @MustValidated
    public void emailSendTest(@RequestBody @ValidateBody EmailSendVO vo, BindingResult result, HttpServletRequest request, HttpServletResponse response){

        result.addError(new ObjectError("pwdError","密码错误"));

        Thread thread = new Thread(new EmailSendTask());
        thread.start();


        EmailEntity entity = new EmailEntity();
        entity.setFrom("826431787@qq.com");
        entity.setSubject("测试邮件");
        entity.setInternetAddress("826431787@qq.com");
        entity.setSendDate(new Date());
        entity.setSubType("related");

        MimeMultipart multipart = new MimeMultipart();

        MimeBodyPart mbp = new MimeBodyPart();


        entity.setMultipart(multipart);

        //EmailSendTask.sendEmail(entity);
    }
}
