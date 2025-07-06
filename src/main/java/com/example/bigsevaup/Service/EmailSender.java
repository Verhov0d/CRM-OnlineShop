package com.example.bigsevaup.Service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private static final String SMTP_HOST = "smtp.mail.ru";
    private static final int SMTP_PORT = 587;
    private static final String SMTP_USERNAME = "petrash123321@mail.ru";
    private static final String SMTP_PASSWORD = "gC4Ru05WVwgQvmTqtBfc";

    public static void sendEmail(String to, String subject, String body) throws MessagingException {
        // Создание свойств для подключения к SMTP-серверу
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Создание объекта Session для подключения к SMTP-серверу
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        // Создание объекта Message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SMTP_USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Отправка сообщения
        Transport.send(message);
    }
}