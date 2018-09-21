package com.tian.spring.common.email;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.apache.commons.mail.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author tianbeiping
 * @Title: CommonEmailUtil
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午4:11
 */
public class CommonEmailUtil {

    public void simpleDemo() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("username", "password"));
        email.setSSLOnConnect(true);
        email.setFrom("user@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("foo@bar.com");
        email.send();
    }


    public void sendHtmlMsg() throws EmailException, MalformedURLException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName("mail.myserver.com");
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@apache.org", "Me");
        email.setSubject("Test email with inline image");

        // set the html message
        email.setHtmlMsg("<html>The apache logo - <img src=\"http://img4.duitang.com/uploads/item/201312/05/20131205172415_jfJcZ.thumb.700_0.jpeg\"></html>");

        // send the email
        email.send();
    }


    public void sendFileMsg() throws EmailException, UnsupportedEncodingException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("mypictures/john.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        //attachment.setName("John");
        attachment.setName(MimeUtility.encodeText("John"));

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("mail.myserver.com");
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@apache.org", "Me");
        email.setSubject("The picture");
        email.setMsg("Here is the picture you wanted");

        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();


    }
}
