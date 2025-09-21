package org.example.jessicaspage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jessica")
public class contactcontroller {

    @PostMapping("/contact")
    public ResponseEntity<String> contactForm(@RequestBody contact contact) {
        System.out.println("Name: " + contact.getName());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Message: " + contact.getMessage());

        return ResponseEntity.ok("Message recieved");
    }
}
