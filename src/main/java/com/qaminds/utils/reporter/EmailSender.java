package com.qaminds.utils.reporter;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String filePath) {
        Properties properties = loadEmailProperties();
        if (properties == null) {
            System.out.println("No se pudo cargar las propiedades del correo electrónico");
            return;
        }

        // Obtén las propiedades del correo electrónico del archivo .properties
        String to = properties.getProperty("mail.to");
        String from = properties.getProperty("mail.from");
        String host = properties.getProperty("mail.smtp.host");
        String user = properties.getProperty("mail.user");
        String password = properties.getProperty("mail.password");

        // Configura la sesión de correo electrónico con authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(properties.getProperty("mail.subject", "Test Report"));  // Usa un valor predeterminado si el asunto no está definido

            // Crea un mensaje multipart y configura la parte del mensaje de texto
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Find the attached report.");
            multipart.addBodyPart(messageBodyPart);

            // Parte dos es el adjunto
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.attachFile(filePath);
            multipart.addBodyPart(messageBodyPart);

            // Envía las partes completas del mensaje
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar las propiedades del archivo .properties
    private static Properties loadEmailProperties() {
        Properties properties = new Properties();
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("email.properties")) {
            if (input == null) {
                System.out.println("No se pudo encontrar el archivo email.properties");
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return properties;
    }
}
