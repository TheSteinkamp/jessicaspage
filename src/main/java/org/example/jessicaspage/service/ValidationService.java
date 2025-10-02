package org.example.jessicaspage.service;

import org.example.jessicaspage.model.Contact;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    public Map<String, String> validateContact(Contact contact) {
        Map<String, String> errors = new HashMap<>();

        validateName(contact.getName(), errors);
        validateEmail(contact.getEmail(), errors);
        validateMessage(contact.getMessage(), errors);

        return errors;
    }

    public void validateName(String name, Map<String, String> errors) {
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Namn får inte vara tomt");
        }
    }

    public void validateEmail(String email, Map<String, String> errors) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Epost får inte vara tomt");
        } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
            errors.put("email", "Ogiltigt e-postformat");
        }
    }

    public void validateMessage(String message, Map<String, String> errors) {
        if (message == null || message.trim().isEmpty()) {
            errors.put("message", "Meddelande får inte vara tomt");
        }
    }
}
