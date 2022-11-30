package com.lta.contactbook.Repository;

import com.lta.contactbook.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact,Long> {
}
