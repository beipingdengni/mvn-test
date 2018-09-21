package com.tian.spring.common.email;


import com.sun.mail.util.MailSSLSocketFactory;
import com.tian.spring.common.StringUtil;
import com.tian.spring.common.excaption.EmailException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: EmailUtil
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午2:45
 */
public class EmailUtil {


    public void SendMsg(String subject, String context, String receiveAddr) throws GeneralSecurityException, MessagingException, EmailException {

        if (StringUtil.isBlank(receiveAddr)) {
            throw new EmailException(20001, " receiveAddr 不能为空 ");
        }

        Session session = getSession();

        session.setDebug(true);

        String fromAddress = "tianbeiping@163.com";

        MimeMessage mimeMessage = getMimeMessage(session, subject, context, fromAddress, receiveAddr);

        Transport.send(mimeMessage);


    }

    public void SendMsgFile(String subject, String context, String receiveAddr, String filename) throws GeneralSecurityException, MessagingException, EmailException, IOException {

        if (StringUtil.isBlank(receiveAddr) || StringUtil.isBlank(filename)) {
            throw new EmailException(20001, " receiveAddr 或 filename 不能为空 ");
        }

        Session session = getSession();

        session.setDebug(true);

        String fromAddress = "tianbeiping@163.com";

        MimeMessage mimeMessage = getMimeMessageFile(session, filename, subject, context, fromAddress, receiveAddr);

        Transport.send(mimeMessage);

    }


    private MimeMessage getMimeMessageFile(Session session, String filename, String subject, String htmlContext, String fromAddress, String receiveAddr) throws MessagingException, IOException {
        MimeMessage mimeMessage = new MimeMessage(session);
        // 设置发送者
        mimeMessage.setFrom(new InternetAddress(fromAddress));

        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        String[] split = receiveAddr.split(";");
        InternetAddress[] addresses = new InternetAddress[split.length];
        for (int i = 0; i < split.length; i++) {
            addresses[i] = new InternetAddress(split[i]);
        }
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, addresses);
        // 主题
        mimeMessage.setSubject(subject);
        mimeMessage.setSentDate(new Date());
        // 创建消息部分
        BodyPart bodyPart = new MimeBodyPart();
        // 消息内容
        bodyPart.setContent(htmlContext, "text/html;charset=UTF-8");

        // 创建传递多重值
        Multipart multipart = new MimeMultipart();

        // 设置文本消息部分
        multipart.addBodyPart(bodyPart);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        // 添加文件
        messageBodyPart.attachFile(new File(filename));
        // 自定义文件
        //DataSource source=new ByteArrayDataSource(new ByteArrayInputStream(null),"application/png");
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(source.getName());


        mimeMessage.setContent(multipart);

        return mimeMessage;
    }

    private MimeMessage getMimeMessage(Session session, String subject, String htmlContext, String fromAddress, String receiveAddr) throws MessagingException {

        MimeMessage mimeMessage = new MimeMessage(session);

        // 设置发送者
        mimeMessage.setFrom(new InternetAddress(fromAddress));

        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        String[] split = receiveAddr.split(";");
        InternetAddress[] addresses = new InternetAddress[split.length];
        for (int i = 0; i < split.length; i++) {
            addresses[i] = new InternetAddress(split[i]);
        }
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, addresses);
        /**
         *  To: 增加收件人（可选）
         *  message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
         *  Cc: 抄送（可选）
         *  message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
         *  Bcc: 密送（可选）
         *  message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));
         */

        mimeMessage.setSubject(subject);
        mimeMessage.setContent(htmlContext, "text/html;charset=UTF-8");
        mimeMessage.setSentDate(new Date());

        /**
         *  本地 测试
         * // 7. 保存前面的设置
         *   message.saveChanges();
         *   // 8. 将该邮件保存到本地
         *   OutputStream out = new FileOutputStream("D://MyEmail.eml");
         *   message.writeTo(out);
         *   out.flush();
         *   out.close();
         */

        return mimeMessage;
    }


    /**
     * 获取session
     *
     * @return
     * @throws GeneralSecurityException
     */
    private Session getSession() throws GeneralSecurityException {
        Properties mailProp = getMailProp();
        Authenticator auth = getAuth();
        return Session.getDefaultInstance(mailProp, auth);
    }

    /**
     * 属性配置
     *
     * @return
     * @throws GeneralSecurityException
     */
    private Properties getMailProp() throws GeneralSecurityException {
        Properties props = new Properties();
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置发件人的SMTP服务器地址 、设置邮件服务器
        props.put("mail.smtp.host", "localhost");
        // 发送端口
        props.put("mail.smtp.port", 465);
        //设置用户的认证方式
        props.put("mail.smtp.auth", "true");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        return props;
    }

    /**
     * 获取授权
     *
     * @return
     */
    private Authenticator getAuth() {
        Authenticator auth = null;
        //是否需要身份验证
        if (Objects.equals(Boolean.TRUE, true)) {
            auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("tianbeiping@163.com", "121212");
                }
            };
        }
        return auth;
    }

}
