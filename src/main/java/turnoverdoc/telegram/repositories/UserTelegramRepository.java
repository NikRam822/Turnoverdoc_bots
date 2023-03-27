package turnoverdoc.telegram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turnoverdoc.telegram.model.User;
import turnoverdoc.telegram.model.UserTelegram;

@Repository
public interface UserTelegramRepository extends JpaRepository<UserTelegram, Long> {
    UserTelegram findByChatId(Long chatId);
    UserTelegram findByUsername(String username);
}
