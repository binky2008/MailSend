/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsend;

/**
 *
 * @author mathide
 */
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MyMail {

    private String mail_head_name;
    private String mail_head_value;
    private String mail_to;
    private String mail_from;
    private String mail_subject;
    private String mail_body;
    private String personalName;
    private String mail_file;

    public MimeMessage getMail(Session session) throws Exception {
        MimeMessage message = new MimeMessage(session);//邮件发送对象  
        message.setSubject(MimeUtility.encodeText(getMail_subject(),"gb2312","B"));// 设置邮件主题  
        message.setHeader(MimeUtility.encodeText(getMail_head_name(),"gb2312","B"),MimeUtility.encodeText(getMail_head_value(),"gb2312","B"));// 设置邮件标题  
        message.setSentDate(new Date());// 设置邮件发送时期  
        Address address = new InternetAddress(getMail_from(), MimeUtility.encodeText(getPersonalName(), "gb2312", "B"));
        message.setFrom(address);// 设置邮件发送者的地址  
        Address toaddress = new InternetAddress(getMail_to());
        message.addRecipient(Message.RecipientType.TO, toaddress);// 设置邮件接收者的地址  

        MimeBodyPart content=createContent(getMail_body());
        MimeBodyPart attachment01 = createAttachment(mail_file);

        // 将邮件中各个部分组合到一个"mixed"型的 MimeMultipart 对象  
        MimeMultipart allPart = new MimeMultipart("mixed");
        allPart.addBodyPart(content);
        allPart.addBodyPart(attachment01);

        // 将上面混合型的 MimeMultipart 对象作为邮件内容并保存  
        message.setContent(allPart);
        message.saveChanges();

        return message;
    }

    /**
     * 根据传入的文件路径创建附件并返回
     */
    public MimeBodyPart createAttachment(String fileName) throws Exception {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(fileName);
        attachmentPart.setDataHandler(new DataHandler(fds));
        attachmentPart.setFileName(MimeUtility.encodeText(fds.getName(),"gb2312","B"));
        return attachmentPart;
    }

    /**
     * 根据传入的邮件正文body创建正文部分
     */
    public MimeBodyPart createContent(String body)
            throws Exception {
        // 用于保存最终正文部分  
        MimeBodyPart contentBody = new MimeBodyPart();
        // 用于组合文本和图片，"related"型的MimeMultipart对象  
        MimeMultipart contentMulti = new MimeMultipart("related");

        // 正文的文本部分  
        MimeBodyPart textBody = new MimeBodyPart();
        textBody.setContent(body, "text/html;charset=gbk");
        contentMulti.addBodyPart(textBody);

        // 将上面"related"型的 MimeMultipart 对象作为邮件的正文  
        contentBody.setContent(contentMulti);
        return contentBody;
    }

    public String getMail_to() {
        return mail_to;
    }

    public void setMail_to(String mail_to) {
        this.mail_to = mail_to;
    }

    public String getMail_body() {
        return mail_body;
    }

    public void setMail_body(String mail_body) {
        this.mail_body = mail_body;
    }

    public String getMail_head_name() {
        return mail_head_name;
    }

    public void setMail_head_name(String mail_head_name) {
        this.mail_head_name = mail_head_name;
    }

    public String getMail_head_value() {
        return mail_head_value;
    }

    public void setMail_head_value(String mail_head_value) {
        this.mail_head_value = mail_head_value;
    }

    public String getMail_from() {
        return mail_from;
    }

    public void setMail_from(String mail_from) {
        this.mail_from = mail_from;
    }

    public String getMail_subject() {
        return mail_subject;
    }

    public void setMail_subject(String mail_subject) {
        this.mail_subject = mail_subject;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getMail_file() {
        return mail_file;
    }

    public void setMail_file(String mail_file) {
        this.mail_file = mail_file;
    }
    
}
