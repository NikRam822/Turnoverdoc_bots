package turnoverdoc.telegram.Bot.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Keyboard {
    SendMessage menuButtons(Long chatId);
}
