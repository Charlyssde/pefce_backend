package com.devx.software.ferias.Mail.Gateway;

public interface MailGateway {

    void sendEmail(String to, String subject, String body);

}
