package com.lta.contactbook.Service;

import com.lta.contactbook.Model.Contact;
import com.lta.contactbook.Repository.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService{
    @Autowired
    private IContactRepository iCr;

    @Override
    public List<Contact> GetAll() {
        return iCr.findAll();
    }

    @Override
    public Optional<Contact> Get(Long id) {
        return iCr.findById(id);
    }

    @Override
    public void Save(Contact contact) {
        iCr.save(contact);
    }

    @Override
    public void Update(Contact contact) {
        iCr.save(contact);
    }

    @Override
    public void Delete(Long id) {
        iCr.deleteById(id);
    }
}
