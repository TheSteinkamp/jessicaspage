package org.example.jessicaspage;

import brevo.ApiClient;
import brevo.Configuration;
import brevoApi.TransactionalEmailsApi;
import brevoModel.CreateSmtpEmail;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailSender;
import brevoModel.SendSmtpEmailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class contactcontroller {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.mail}")
    private String myemail;

    private final contactrepository contactrepository;
    @Autowired
    public contactcontroller(contactrepository contactrepository) {
        this.contactrepository = contactrepository;
    }

    @PostMapping("/contact")
    public ResponseEntity<String> contactForm(@RequestBody contact contact) {
        try{
            System.out.println("Name: " + contact.getName());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Message: " + contact.getMessage());

            contactrepository.save(contact);

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
