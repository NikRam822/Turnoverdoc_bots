package turnoverdoc.telegram.Bot.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public enum BotState {

    Start {
        @Override
        public void enter(BotContext context) {
            sendMessage("Start message", context);
        }

        @Override
        public BotState nextState() {
            return null;
        }
    };

    private static BotState[] states;
    private final boolean isInputNeeded;

    BotState() {
        this.isInputNeeded = true;
    }

    BotState(boolean inputNeeded) {
        this.isInputNeeded = inputNeeded;
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

    protected void sendMessage(String text, BotContext context) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(context.getUser().getChatId());
        sendMessage.setText(text);

        try {
            context.getBot().execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
}
