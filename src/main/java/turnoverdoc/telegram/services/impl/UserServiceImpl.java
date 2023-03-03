package turnoverdoc.telegram.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turnoverdoc.telegram.model.User;
import turnoverdoc.telegram.model.UserTelegram;
import turnoverdoc.telegram.repositories.UserRepository;
import turnoverdoc.telegram.repositories.UserTelegramRepository;
import turnoverdoc.telegram.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserTelegramRepository userTelegramRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setUserRepository(UserTelegramRepository userTelegramRepository) {
        this.userTelegramRepository = userTelegramRepository;
    }

    @Override
    public UserTelegram findByChatId(Long chatId) {
        return userTelegramRepository.findByChatId(chatId);
    }

    @Override
    public UserTelegram save(UserTelegram userTelegram) {
        return userTelegramRepository.save(userTelegram);
    }

    @Override
    public boolean isTelegramLinkedToProfile(String username) {
        User user = findByTelegramUsername(username);

        return user != null;
    }

    @Override
    public User findByTelegramUsername(String username) {
        return userRepository.findByTelegramUsername(username);
    }
}
