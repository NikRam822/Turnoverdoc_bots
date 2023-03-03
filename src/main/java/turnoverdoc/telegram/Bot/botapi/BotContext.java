package turnoverdoc.telegram.Bot.botapi;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import turnoverdoc.telegram.model.UserTelegram;
import turnoverdoc.telegram.services.OrderService;
import turnoverdoc.telegram.services.UserService;

@Getter
public class BotContext {
    private final ChatBot bot;  //телеграм бот
    private final UserTelegram userTelegram;    //пользователь, с которым работает бот
    private final String input; //последний ввод пользователя
    private CallbackQuery callBack;
    private UserService userService;
    private OrderService orderService;

    public static BotContext of(ChatBot bot, String input, UserTelegram userTelegram, CallbackQuery callBack, UserService userService, OrderService orderService) {
        return new BotContext(input, userTelegram, bot, callBack, userService, orderService);
    }

    public BotContext(String input, UserTelegram userTelegram, ChatBot bot, CallbackQuery callBack, UserService userService, OrderService orderService) {
        this.input = input;
        this.userTelegram = userTelegram;
        this.bot = bot;
        this.callBack = callBack;
        this.userService = userService;
        this.orderService = orderService;
    }
}
