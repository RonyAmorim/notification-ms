package com.rony.notification_ms.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private static final String EMAIL_SENDING_FAILED = "Failed to send email";

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Sends an email with the specified parameters.
     *
     * @param to      the recipient email address
     * @param subject the subject of the email
     * @param text    the body of the email
     */
    public void sendEmail(String to, String subject, String text) {
        logger.info("Preparing to send email to {}", to);
        try {
            MimeMessage message = createMimeMessage(to, subject, text);
            javaMailSender.send(message);
            logger.info("Email sent successfully to {}", to);
        } catch (MessagingException e) {
            logger.error(EMAIL_SENDING_FAILED, e);
            throw new RuntimeException(EMAIL_SENDING_FAILED, e);
        }
    }

    /**
     * Creates a MimeMessage with the specified parameters.
     *
     * @param to      the recipient email address
     * @param subject the subject of the email
     * @param text    the body of the email
     * @return the MimeMessage
     * @throws MessagingException if there is an error creating the message
     */
    private MimeMessage createMimeMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true); // The second parameter indicates the text is HTML content
        return message;
    }
}
