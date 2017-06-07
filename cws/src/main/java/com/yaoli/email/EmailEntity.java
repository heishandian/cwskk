package com.yaoli.email;

import javax.mail.internet.MimeMultipart;
import java.util.Date;

/**
 * Created by will on 2016/12/15.
 */
public class EmailEntity{
    private String subject;

    private String from;

    private String internetAddress;

    private Date sendDate;

    private String subType = "related";

    private MimeMultipart multipart;

    private String recipient;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getInternetAddress() {
        return internetAddress;
    }

    public void setInternetAddress(String internetAddress) {
        this.internetAddress = internetAddress;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public MimeMultipart getMultipart() {
        return multipart;
    }

    public void setMultipart(MimeMultipart multipart) {
        this.multipart = multipart;
    }
}
