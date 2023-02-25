package turnoverdoc.telegram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turnoverdoc.telegram.model.User;
import turnoverdoc.telegram.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
