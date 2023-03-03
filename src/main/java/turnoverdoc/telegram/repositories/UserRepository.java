package turnoverdoc.telegram.repositories;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turnoverdoc.telegram.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTelegramUsername(String username);
}
