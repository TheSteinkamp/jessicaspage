package org.example.jessicaspage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class contactcontroller {

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

            return ResponseEntity.ok("Message recieved");
        }
        catch(Exception e){
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }

    }
}
