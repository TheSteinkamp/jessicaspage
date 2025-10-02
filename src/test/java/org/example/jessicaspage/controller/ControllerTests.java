package org.example.jessicaspage.controller;

import org.example.jessicaspage.model.Contact;
import org.example.jessicaspage.repository.Contactrepository;
import org.example.jessicaspage.service.Mailservice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ControllerTests {

    @Mock
    private Contactrepository testrepository;
    @Mock
    private Mailservice testmailservice;
    @InjectMocks
    private Contactcontroller testcontroller;

    Contact mockContact = new Contact(1L, "testUser", "test@test.se", "testmessage");

    @Test
    public void savecontactTest() throws Exception {
        when(testrepository.save(any(Contact.class))).thenReturn(mockContact);
        when(testmailservice.contactForm(any(Contact.class))).thenReturn(new ResponseEntity<>("Message recieved", HttpStatus.OK));
        ResponseEntity<String> response = testcontroller.contactForm(mockContact);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Message recieved", response.getBody());
        verify(testrepository, times(1)).save(mockContact);
        verify(testmailservice, times(1)).contactForm(mockContact);
    }

    @Test
    void contactForm_ShouldReturn500_WhenSaveThrowsException() {
        when(testrepository.save(mockContact)).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<String> response = testcontroller.contactForm(mockContact);

        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Error: DB error"));
        verify(testrepository, times(1)).save(mockContact);
    }

}
