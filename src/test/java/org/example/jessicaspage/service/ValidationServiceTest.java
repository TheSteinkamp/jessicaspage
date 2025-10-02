package org.example.jessicaspage.service;

import org.example.jessicaspage.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    private ValidationService testservice;
    private Map<String, String> errors;
    @BeforeEach
    void setup() {
        testservice = new ValidationService();
        errors = new HashMap<>();
    }

    @Test
    void validateContactTest() {
        Contact testcontact = new Contact(1L, null, "testemail.com", null);
        Contact validtestcontact = new Contact(1L, "Test", "test@email.com", "testmessage");

        errors = testservice.validateContact(testcontact);
        assertEquals(3, errors.size(), "fel på name, email, message");
        assertEquals("Namn får inte vara tomt", errors.get("name"));
        assertEquals("Ogiltigt e-postformat", errors.get("email"));
        assertEquals("Meddelande får inte vara tomt", errors.get("message"));

        errors = testservice.validateContact(validtestcontact);
        assertTrue(errors.isEmpty());
    }

    @Test
    void validateNameTest() {
        String validName = "Testname";
        String notValidName = null;

        testservice.validateName(validName, errors);
        assertTrue(errors.isEmpty());

        testservice.validateName(notValidName, errors);
        assertFalse(errors.isEmpty(), "Namn får inte vara tomt");
        assertTrue(errors.containsKey("name"));
    }

    @Test
    void validateEmailTest() {
        String validEmail = "test@test.com";
        String notValidEmail = "testemail.com";
        String nullEmail = null;

        testservice.validateEmail(validEmail, errors);
        assertTrue(errors.isEmpty());

        testservice.validateEmail(notValidEmail, errors);
        assertEquals("Ogiltigt e-postformat", errors.get("email"));

        testservice.validateEmail(nullEmail, errors);
        assertEquals("Epost får inte vara tomt", errors.get("email"));
    }

    @Test
    void validateMessageTest() {
        String validMessage = "Testmessage";
        String notValidMessage = null;

        testservice.validateMessage(validMessage, errors);
        assertTrue(errors.isEmpty());
        testservice.validateMessage(notValidMessage, errors);
        assertEquals("Meddelande får inte vara tomt", errors.get("message"));
    }
}