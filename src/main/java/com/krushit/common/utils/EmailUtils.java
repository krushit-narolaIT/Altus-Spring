package com.krushit.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

    private static final String FROM_EMAIL = "indian.red.cross.in@gmail.com";
    private static final String FROM_PASSWORD = "ohmqkqzffxfjsurk";

    public static void sendEmail(String recipientEmail, String subject, String body) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject(subject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html");

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Email successfully sent to: " + recipientEmail);
    }

    public static void sendWelcomeEmail(String recipientEmail, String firstName, String lastName) throws MessagingException {
        String subject = "Welcome to Altus Ride – Your Journey Starts Here!";
        String body = "<html><body>"
                + "<h2>Hello " + firstName + " " + lastName + ",</h2>"
                + "<p>Welcome to <strong>Altus</strong> – your trusted companion for fast, safe, and convenient travel.</p>"
                + "<p>We're excited to have you on board! Whether you're heading to work, the airport, or out with friends, Altus gets you there with ease.</p>"
                + "<p>Download the app, book your first ride, and enjoy the comfort of seamless travel at your fingertips.</p>"
                + "<br/>"
                + "<p>Need help? Our 24/7 support team is just a tap away.</p>"
                + "<br/>"
                + "<p><strong>Let’s get moving!</strong></p>"
                + "<p>– The Altus Team</p>"
                + "</body></html>";
        sendEmail(recipientEmail, subject, body);
    }

    public static void sendWelcomeEmailWithCSS(String recipientEmail, String firstName, String lastName) throws MessagingException {
        try {
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    EmailUtils.class.getClassLoader().getResourceAsStream("mail.txt")))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
            }
            String body = contentBuilder.toString()
                    .replace("{firstName}", firstName)
                    .replace("{lastName}", lastName);
            String subject = "Welcome to Altus – Let's Get You Moving!";
            sendEmail(recipientEmail, subject, body);
        } catch (IOException e) {
            throw new MessagingException("Failed to read email template", e);
        }
    }
}
