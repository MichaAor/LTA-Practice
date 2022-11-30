package com.lta.contactbook.Service;

import com.lta.contactbook.Model.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactService {
    List<Contact> GetAll();
    Optional<Contact> Get(Long id);
    void Save(Contact contact);
    void Update(Contact contact);
    void Delete(Long id);
}
