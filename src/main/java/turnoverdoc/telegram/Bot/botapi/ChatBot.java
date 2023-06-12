package turnoverdoc.telegram.Bot.botapi;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import turnoverdoc.telegram.model.UserTelegram;
import turnoverdoc.telegram.services.OrderService;
import turnoverdoc.telegram.services.UserService;

@Slf4j
@Component
public class ChatBot extends TelegramLongPollingBot {
    private String botName;

    private UserService userService;
    private OrderService orderService;

    public ChatBot(@Value("${bot.token}") String botToken, @Value("${bot.username}") String botName, TelegramBotsApi telegramBotsApi) throws TelegramApiException {
        super(botToken);
        this.botName = botName;
        telegramBotsApi.registerBot(this);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Long chatId;
        String text;

        if (update.hasCallbackQuery()) {        // если была нажата кнопка и т.д
            chatId = update.getCallbackQuery().getMessage().getChatId();
            text = "";
        } else {
            text = update.getMessage().getText();
            chatId = update.getMessage().getChatId();
        }

        UserTelegram userTelegram = userService.findByChatId(chatId);

        BotContext context;
        BotState state;

        if (userTelegram == null) {
            state = BotState.getInitialState();

            userTelegram = new UserTelegram(update.getMessage().getChatId(), state.ordinal(), update.getMessage().getFrom().getUserName());
            userService.save(userTelegram);

            context = BotContext.of(this, text, userTelegram, update.getCallbackQuery(), userService, orderService);

            state.enter(context);
        } else {
            context = BotContext.of(this, text, userTelegram, update.getCallbackQuery(), userService, orderService);
            state = BotState.byId(userTelegram.getStateId());
        }

        state.handleInput(context);

        do {
            state = state.nextState(context);

            state.enter(context);
            log.info("enter to state: " + state.name());
        } while (!state.isInputNeeded());

        userTelegram.setStateId(state.ordinal());

        userService.save(userTelegram);

    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
