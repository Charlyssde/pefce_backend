package com.devx.software.ferias.Misc;


import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;

public class Mailgun {
    private final String MAILGUN_DOMAIN = "mg.soluto.mx";
    private final String MAILGUN_URL = "https://api.mailgun.net/v3";
    private final String PRIVATE_API_KEY = "d6c422499c177afc88a55691f22d3ca5-8ed21946-14b4e739";
    private final String MAILGUN_NAME = "Test";
    private final String MAILGUN_FROM = "devxsoftwarelabs@gmail.com";

    public MailgunMessagesApi mailgunMessagesApi() {
        return MailgunClient.config(PRIVATE_API_KEY)
                .createApi(MailgunMessagesApi.class);
    }

    public void sendBasicEmail(String subject, String to, String body) throws Exception {
        Message message = Message.builder()
                .from(MAILGUN_FROM)
                .to(to)
                .subject(subject)
                .html(this.prepareEmail(body))
                .build();
        this.mailgunMessagesApi().sendMessage(MAILGUN_DOMAIN, message);
    }

    private String prepareEmail(String htmlContent) {
        String header = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Secretaría de Desarrollo Económico y Portuario del Estado de Veracruz</title>\n" +
                "    <style>\n" +
                "        html,\n" +
                "        body {\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "            padding: 0px;\n" +
                "            margin: 0px;\n" +
                "            font-size: 1.3em !important;\n" +
                "        }\n" +
                "        \n" +
                "        .email_container {\n" +
                "            border: 1px solid rgb(91, 90, 93);\n" +
                "            border-radius: 0px;\n" +
                "            margin: 30px 15%;\n" +
                "        }\n" +
                "        \n" +
                "        .header {\n" +
                "            padding: 10px;\n" +
                "            border-bottom: 1px solid rgb(91, 90, 93);\n" +
                "            background-color: #575756;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        \n" +
                "        .logo {\n" +
                "            width: 15%;\n" +
                "            height: auto;\n" +
                "        }\n" +
                "        \n" +
                "        .footer {\n" +
                "            background-color: #575756;\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            padding: 20px 10px;\n" +
                "        }\n" +
                "        \n" +
                "        .body {\n" +
                "            padding: 40px 10px;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"email_container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1>Secretaría de Desarrollo Económico y Portuario del Estado de Veracruz</h1>\n" +
                "        </div>\n" +
                "        <div class=\"body\">";

        String body = htmlContent;

        String footer = "</div>\n" +
                "        <div class=\"footer\">\n" +
                "            <small>Tu estado industrial</small>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        return header + body + footer;
    }

}
