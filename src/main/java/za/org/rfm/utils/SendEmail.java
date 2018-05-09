package za.org.rfm.utils;


import com.sendgrid.*;
import java.io.IOException;

public class SendEmail {

    public static void send(String toAddress,String message,String subject){
        Email from = new Email("firemanager@rfm.org.za");
        Email to = new Email(toAddress);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(Constants.SENDGRID_MAIL_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    }

