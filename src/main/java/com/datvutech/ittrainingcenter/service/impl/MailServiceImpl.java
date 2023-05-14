package com.datvutech.ittrainingcenter.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.datvutech.ittrainingcenter.service.MailService;
import com.datvutech.ittrainingcenter.service.model.MailDetail;

@Service
public class MailServiceImpl implements MailService {
    private JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendHtmlContentMail(MailDetail mailDetail) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        try {
            messageHelper.setFrom(mailDetail.getFromAddress(), mailDetail.getSenderName());
            messageHelper.setTo(mailDetail.getToAddress());
            messageHelper.setSubject(mailDetail.getSubject());
            messageHelper.setText(mailDetail.getContent(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

}
