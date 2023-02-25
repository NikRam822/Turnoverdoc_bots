package turnoverdoc.telegram.Bot.botapi;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import turnoverdoc.telegram.model.User;
import turnoverdoc.telegram.services.UserService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Component
public class ChatBot extends TelegramLongPollingBot {
    private String botName;

    private UserService userService;

    public ChatBot(@Value("${bot.token}") String botToken, @Value("${bot.username}") String botName, TelegramBotsApi telegramBotsApi, UserService userService) throws TelegramApiException {
        super(botToken);
        this.botName = botName;
        this.userService = userService;
        telegramBotsApi.registerBot(this);
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


        User user = userService.findByChatId(chatId);

        BotContext context;
        BotState state;

        if (user == null) {
            state = BotState.getInitialState();

            user = new User(update.getMessage().getChatId(), state.ordinal());
            userService.save(user);

            context = BotContext.of(this, text, user);

            state.enter(context);
        } else {
            context = BotContext.of(this, text, user);
            state = BotState.byId(user.getStateId());
        }

        do {
            state = state.nextState();

            state.enter(context);
        } while (!state.isInputNeeded());

    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
