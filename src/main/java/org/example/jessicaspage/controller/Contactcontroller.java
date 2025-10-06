package org.example.jessicaspage.controller;
import org.example.jessicaspage.model.Contact;
import org.example.jessicaspage.repository.Contactrepository;
import org.example.jessicaspage.service.Mailservice;
import org.example.jessicaspage.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class Contactcontroller {

    private final Contactrepository contactrepository;
    private final Mailservice mailservice;
    private final ValidationService validationService;

    @Autowired
    public Contactcontroller(Contactrepository contactrepository, Mailservice mailservice) {
        this.contactrepository = contactrepository;
        this.mailservice = mailservice;
        this.validationService = new ValidationService();
    }

    @PostMapping("/contact")
    public ResponseEntity<String> contactForm(@RequestBody Contact contact) {

        Map<String, String> errors = validationService.validateContact(contact);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        try{
            System.out.println("Name: " + contact.getName());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Message: " + contact.getMessage());

            contactrepository.save(contact);

            return mailservice.contactForm(contact);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
