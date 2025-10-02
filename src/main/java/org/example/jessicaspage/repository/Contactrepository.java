package org.example.jessicaspage.repository;

import org.example.jessicaspage.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Contactrepository extends JpaRepository<Contact, Long> {

}
