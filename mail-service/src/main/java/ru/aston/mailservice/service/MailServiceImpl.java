package ru.aston.mailservice.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class MailServiceImpl{

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private String from;
    private  JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender,
        @Value("${spring.mail.username}") String from) {
            this.mailSender = mailSender;
            this.from = from;
    }

    public void sendEmailAboutDelete(String toEmail) {
        logger.info("sendEmailAboutDelete() mail = {}", toEmail);
        String subject = "Удаление";
        String body = "Здравствуйте! Ваш аккаунт был удалён.";
        sendEmail(toEmail, subject, body);
    }

    public void sendEmailAboutAdd(String toEmail) {
        logger.info("sendEmailAboutAdd() mail = {}", toEmail);
        String subject = "Создание";
        String body = "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.";
        sendEmail(toEmail, subject, body);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);

        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}
