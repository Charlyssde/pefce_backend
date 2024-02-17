package com.devx.software.ferias.Mail.Utils;

public class Utils {

    public static String prepareEmail(String htmlContent) {
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
