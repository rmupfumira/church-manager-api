package za.org.rfm.service;

import com.sendgrid.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;



@RunWith(SpringJUnit4ClassRunner.class)
public class EmailTest {

    @Test
    public void shouldSendEmailViaSendGridApi() throws IOException {
        Email from = new Email("test@example.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("russel@rfm.org.za");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);
        //System.out.println("my key:..."+System.getenv("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"));

       
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
            throw ex;
        }
    }
}
