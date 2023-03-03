package turnoverdoc.telegram.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turnoverdoc.telegram.model.Contact;
import turnoverdoc.telegram.repositories.ContactRepository;
import turnoverdoc.telegram.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}
