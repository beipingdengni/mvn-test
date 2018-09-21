package com.tian.spring.common.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: SpringEmailUtil
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午4:34
 */
public class SpringEmailUtil {


    private JavaMailSender mailSender;
    private Properties properties;

    public SpringEmailUtil() {

        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.163.com");
        properties.put("mail.smtp.port", "25");

        properties.put("mail.smtp.user", "test@163.com");
        properties.put("mail.smtp.password", "test");

        properties.put("mail.smtp.from", "test@163.com");


        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(properties.getProperty("mail.smtp.host"));//指定用来发送Email的邮件服务器主机名
        mailSenderImpl.setPort(Integer.parseInt(properties.getProperty("mail.smtp.port")));//默认端口，标准的SMTP端口
        mailSenderImpl.setUsername(properties.getProperty("mail.smtp.user"));//用户名
        mailSenderImpl.setPassword(properties.getProperty("mail.smtp.password"));//密码
        mailSender = mailSenderImpl;
    }

//    @Bean
//    public MailSender mailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.163.com");//指定用来发送Email的邮件服务器主机名
//        mailSender.setPort(25);//默认端口，标准的SMTP端口
//        mailSender.setUsername("test@163.com");//用户名
//        mailSender.setPassword("test");//密码
//        return mailSender;
//    }

    public void sendSimpleEmail(String to, String subject, String context) {
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom(properties.getProperty("mail.smtp.from"));//发件人
        message.setTo(to);//收件人
        message.setSubject(subject);//主题
        message.setText(context);//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }


    public void sendSimpleEmailHtml(String to, String subject, String context) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        messageHelper.setFrom(properties.getProperty("mail.smtp.from"));//发件人
        messageHelper.setTo(to);//收件人
        messageHelper.setSubject(subject);//主题
        // true 表示启动HTML格式的邮件
        messageHelper.setText(context, true);//正文

        mailSender.send(mimeMessage);

        System.out.println("邮件发送完毕");
    }


    public void sendAttachmentMail(String to, String subject, String context, String fileName) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        messageHelper.setFrom(properties.getProperty("mail.smtp.from"));//发件人
        messageHelper.setTo(to);//收件人
        messageHelper.setSubject(subject);//主题
        // true 表示启动HTML格式的邮件
        messageHelper.setText(context, true);//正文
        File file = new File(fileName);
        messageHelper.addAttachment(file.getName(), file);
        mailSender.send(mimeMessage);

        System.out.println("邮件发送完毕");
    }


}
