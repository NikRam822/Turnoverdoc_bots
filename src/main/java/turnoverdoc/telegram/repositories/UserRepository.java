package turnoverdoc.telegram.repositories;

import jakarta.transaction.Transactional;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import turnoverdoc.telegram.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE contact_id=(SELECT contacts.id FROM contacts WHERE contacts.messenger=:username AND contacts.is_personal=true)", nativeQuery = true)
    @Transactional
    User findByTelegramUsername(String username);

    //Caused by: org.hibernate.exception.DataException: JDBC exception executing SQL [SELECT * FROM users WHERE contact_id=(SELECT contacts.id FROM contacts WHERE contacts.messenger=? AND contacts.is_personal=true)]
    //Caused by: org.postgresql.util.PSQLException: ОШИБКА: подзапрос в выражении вернул больше одной строки
}
