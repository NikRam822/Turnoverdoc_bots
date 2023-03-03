package turnoverdoc.telegram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turnoverdoc.telegram.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
