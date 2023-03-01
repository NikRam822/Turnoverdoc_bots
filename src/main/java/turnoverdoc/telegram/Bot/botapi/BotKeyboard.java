package turnoverdoc.telegram.Bot.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static turnoverdoc.telegram.Bot.BotConstants.*;

public class BotKeyboard implements Keyboard {
    @Override
    public SendMessage menuButtons(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> buttons;

        buttons = initializeButton(4);

        buttons.get(0).setText(CREATE_ORDER_BUTTON);
        buttons.get(0).setCallbackData(CREATE_ORDER_BUTTON);

        buttons.get(1).setText(BOT_FEATURES_BUTTON);
        buttons.get(1).setCallbackData(BOT_FEATURES_BUTTON);

        buttons.get(2).setText(ABOUT_COMPANY_BUTTON);
        buttons.get(2).setCallbackData(ABOUT_COMPANY_BUTTON);

        buttons.get(3).setText(USERS_ORDERS_BUTTON);
        buttons.get(3).setCallbackData(USERS_ORDERS_BUTTON);

        inlineKeyboardMarkup.setKeyboard(createGrid(buttons));

        return createSendMessageWithBotKeyboard(chatId, MENU_MESSAGE, inlineKeyboardMarkup);
    }

    private SendMessage createSendMessageWithBotKeyboard(Long chatId, String messageText, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageText);
        sendMessage.setReplyMarkup(replyKeyboard);
        return sendMessage;
    }

    /**
     * Инициализация кнопок
     * @param count количество кнопок
     * @return Возвращает кнопки
     */
    private List<InlineKeyboardButton> initializeButton(int count) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            buttons.add(new InlineKeyboardButton());
        }

        return buttons;
    }

    /**
     * Создает разметку кнопок
     * @param buttons кнопки
     * @return готовая сетка
     */
    private List<List<InlineKeyboardButton>> createGrid(List<InlineKeyboardButton> buttons) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (int i = 0; i < buttons.size(); i++) {
            List<InlineKeyboardButton> inlineButton = new ArrayList<>();
            inlineButton.add(buttons.get(i));
            rowList.add(inlineButton);
        }

        return rowList;
    }
}
