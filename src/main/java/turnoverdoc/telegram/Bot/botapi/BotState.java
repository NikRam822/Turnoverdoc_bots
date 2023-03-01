package turnoverdoc.telegram.Bot.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static turnoverdoc.telegram.Bot.BotConstants.START_MESSAGE;

public enum BotState {

    Start(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, START_MESSAGE);
        }

        @Override
        public BotState nextState() {
            return Menu;
        }

        @Override
        protected void handleInput() {

        }
    },
    Menu {
        @Override
        public void enter(BotContext context) {
            BotKeyboard botKeyboard = new BotKeyboard();
            setKeyboardInput(botKeyboard.menuButtons(context.getUser().getChatId()));
            sendMessage(context, "");
        }

        @Override
        public BotState nextState() {
            return null;
        }

        @Override
        protected void handleInput() {

        }
    };

    private static BotState[] states;
    private final boolean isInputNeeded;
    private SendMessage replyKeyboardMarkup;
    private SendMessage keyboardInput;

    BotState() {
        this.isInputNeeded = true;
    }

    BotState(boolean inputNeeded) {
        this.isInputNeeded = inputNeeded;
    }

    public void setReplyKeyboardMarkup(SendMessage replyKeyboardMarkup) {
        this.replyKeyboardMarkup = replyKeyboardMarkup;
    }

    public void setKeyboardInput(SendMessage keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    /**
     * Возвращает начальное состояние бота
     * @return Самое первое состояние
     */
    public static BotState getInitialState() {
        return byId(0);
    }

    public static BotState byId(int id) {
        if (states == null) {
            states = BotState.values();
        }

        return states[id];
    }

    /**
     * Отправляет сообщение пользователю
     * @param context контекст приложения
     * @param text текст сообщения
     */
    protected void sendMessage(BotContext context, String text) {
        if (replyKeyboardMarkup == null && keyboardInput == null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(context.getUser().getChatId());
            sendMessage.setText(text);

            try {
                context.getBot().execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (replyKeyboardMarkup != null) {
            try {
                context.getBot().execute(replyKeyboardMarkup);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            try {
                context.getBot().execute(keyboardInput);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isInputNeeded() {
        return isInputNeeded;
    }

    public abstract void enter(BotContext context);

    /**
     * Переходит в следующее состояние
     * @return Следующее состояние
     */
    public abstract BotState nextState();

    protected abstract void handleInput();
}
