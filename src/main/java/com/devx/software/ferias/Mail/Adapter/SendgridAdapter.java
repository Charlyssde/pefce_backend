package com.devx.software.ferias.Mail.Adapter;

import com.devx.software.ferias.Mail.Gateway.MailGateway;
import com.devx.software.ferias.Mail.Utils.Utils;
import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendgridAdapter implements MailGateway {

    private static final Logger logger = LoggerFactory.getLogger(SendgridAdapter.class);

    @Value("${spring.sendgrid.proxy.host}")
    private String FROM;

    @Value("${sendgrid.apikey}")
    private String API;

    @Override
    public void sendEmail(String to, String subject, String body) {

        try {

            Email from_email = new Email(FROM);
            Email to_email = new Email(to);

            Content content_email = new Content("text/html", Utils.prepareEmail(body));

            Mail mail = new Mail(from_email, subject, to_email, content_email);

            SendGrid sendGrid = new SendGrid(API);

            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            logger.info(response.getBody());


        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("error al enviar email");

        }
    }
}
