package turnoverdoc.telegram.services;

import turnoverdoc.telegram.model.User;
import turnoverdoc.telegram.model.UserTelegram;

public interface UserService {
    UserTelegram findByChatId(Long chatId);
    UserTelegram save(UserTelegram userTelegram);
    boolean isTelegramLinkedToProfile(String username);
    User findByTelegramUsername(String username);
}
