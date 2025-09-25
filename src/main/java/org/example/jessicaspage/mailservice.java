package org.example.jessicaspage;

import brevo.ApiClient;
import brevo.Configuration;
import brevoApi.TransactionalEmailsApi;
import brevoModel.CreateSmtpEmail;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailSender;
import brevoModel.SendSmtpEmailTo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collections;

@Service
public class mailservice {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    @Value("${BREVO_MAIL}")
    private String myemail;

    public ResponseEntity<String> contactForm(@RequestBody contact contact) {
        try{
            ApiClient defaultClient = Configuration.getDefaultApiClient();
            defaultClient.setApiKey(apiKey);

            TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();

            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail()
                    .sender(new SendSmtpEmailSender()
                            .email(myemail)
                            .name("Webbformulär"))
                    .to(Collections.singletonList(
                            new SendSmtpEmailTo().email(myemail)))
                    .subject("Nytt meddelande från formuläret")
                    .htmlContent("<p><strong>Namn:</strong> " + contact.getName() + "</p>"
                            + "<p><strong>Email:</strong> " + contact.getEmail() + "</p>"
                            + "<p><strong>Meddelande:</strong><br>" + contact.getMessage() + "</p>");

            CreateSmtpEmail response = apiInstance.sendTransacEmail(sendSmtpEmail);
            System.out.println("Mail skickat: " + response.toString());

            return ResponseEntity.ok("Message sent successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
