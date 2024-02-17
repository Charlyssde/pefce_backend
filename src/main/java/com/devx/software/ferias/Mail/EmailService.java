package com.devx.software.ferias.Mail;


import com.devx.software.ferias.Mail.Adapter.SendgridAdapter;
import com.devx.software.ferias.Mail.Gateway.MailGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    MailGateway mailAdapter;

    @Autowired
    public EmailService(SendgridAdapter mailAdapter) {
        this.mailAdapter = mailAdapter;
    }

    public void sendEmail(String to, String subject, String body) {
        mailAdapter.sendEmail(to, subject, body);
    }

}
