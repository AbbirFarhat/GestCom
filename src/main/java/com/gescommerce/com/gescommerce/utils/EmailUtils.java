package com.gescommerce.com.gescommerce.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailUtils {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fbarboura6@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        if (list != null && list.size() > 0) {
            message.setCc(getCcArray(list));
        }

        try {
            emailSender.send(message);
            logger.info("Email sent successfully to: {}", to);
        } catch (MailException e) {
            logger.error("Failed to send email to: {}", to, e);
            // Handle the exception (e.g., logging, notification)
        }
    }

    private String[] getCcArray(List<String> ccList) {
        String[] cc = new String[ccList.size()];

        for (int i = 0; i < ccList.size(); i++) {
            cc[i] = ccList.get(i);
        }
        return cc;
    }

    public void forgotMail(String to, String subject, String password) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("fbarboura6@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<p><b>Vos informations de connexion pour le système de gestion de café</b><br><b>E-mail: </b> " + to + " <br><b>Mot de passe: </b> " + password + "<br><a href=\"http://localhost:4200/\">Cliquez ici pour vous connecter</a></p>";
        message.setContent(htmlMsg, "text/html");

        try {
            emailSender.send(message);
            logger.info("Password reset email sent successfully to: {}", to);
        } catch (MailAuthenticationException e) {
            logger.error("Failed to send password reset email to: {}. Authentication failed.", to, e);
        } catch (MailException e) {
            logger.error("Failed to send password reset email to: {}. Other mail exception occurred.", to, e);
        }
    }

}
