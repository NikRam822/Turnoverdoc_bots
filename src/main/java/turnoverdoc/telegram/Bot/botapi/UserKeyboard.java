package turnoverdoc.telegram.Bot.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

import static turnoverdoc.telegram.Bot.BotConstants.*;

/***
 * Class for user's button
 * Buttons which under input line
 */
public class UserKeyboard {
    private BotContext context;

    public UserKeyboard(BotContext context) {
        this.context = context;
    }

    public SendMessage createMainMenu() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow createOrderRow = new KeyboardRow();
        KeyboardRow botFeaturesRow = new KeyboardRow();
        KeyboardRow aboutCompanyRow = new KeyboardRow();
        KeyboardRow usersOrdersRow = new KeyboardRow();

        replyKeyboardMarkup.setResizeKeyboard(true);

        createOrderRow.add(CREATE_ORDER_BUTTON);
        botFeaturesRow.add(BOT_FEATURES_BUTTON);

        aboutCompanyRow.add(ABOUT_COMPANY_BUTTON);
        usersOrdersRow.add(USERS_ORDERS_BUTTON);

        keyboardRows.add(createOrderRow);
        keyboardRows.add(botFeaturesRow);
        keyboardRows.add(aboutCompanyRow);
        keyboardRows.add(usersOrdersRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(context.getUserTelegram().getChatId());
        sendMessage.setText(MENU_MESSAGE);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
