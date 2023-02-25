package turnoverdoc.telegram.Bot.botapi;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import turnoverdoc.telegram.model.User;

@Getter
public class BotContext {
    private final ChatBot bot;  //телеграм бот
    private final User user;    //пользователь, с которым работает бот
    private final String input; //последний ввод пользователя

    public static BotContext of(ChatBot bot, String input, User user) {
        return new BotContext(input, user, bot);
    }

    public BotContext(String input, User user, ChatBot bot) {
        this.input = input;
        this.user = user;
        this.bot = bot;
    }
}
