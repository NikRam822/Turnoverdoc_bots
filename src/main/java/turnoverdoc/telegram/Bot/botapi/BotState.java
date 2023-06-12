package turnoverdoc.telegram.Bot.botapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import turnoverdoc.telegram.Bot.BotConstants;

import static turnoverdoc.telegram.Bot.BotConstants.*;

@Slf4j
@Component
public enum BotState {

    Start(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, START_MESSAGE);
        }

        @Override
        public BotState nextState(BotContext context) {
            return Menu;
        }

        @Override
        protected void handleInput(BotContext context) {

        }
    },
    Menu {
        @Override
        public void enter(BotContext context) {
            BotKeyboard botKeyboard = new BotKeyboard();
            setKeyboardInput(botKeyboard.menuButtons(context.getUserTelegram().getChatId()));
            sendMessage(context, "");
        }

        @Override
        public BotState nextState(BotContext context) {
            switch (context.getUserTelegram().getMenuFunction()) {
                case CREATE_ORDER_BUTTON:
                    log.info("Create order enter");
                    if (context.getUserService().isTelegramLinkedToProfile(context.getCallBack().getFrom().getUserName())) {
                        log.info("telegram is linked to profile");
                        return CreateOrder;
                    }
                    log.info("telegram is not linked");
                    sendMessage(context, TELEGRAM_NOT_LINKED);
                    log.info("send message to user");
                    return Menu;
                case BOT_FEATURES_BUTTON:
                    return BotFeatures;
                case ABOUT_COMPANY_BUTTON:
                    return AboutCompany;
                case USERS_ORDERS_BUTTON:
                    return UsersOrders;
                default:
                    sendMessage(context, INVALID_INPUT);
                    return Menu;
            }
        }

        @Override
        protected void handleInput(BotContext context) {
            if (context.getCallBack() != null) {
                context.getUserTelegram().setMenuFunction(context.getCallBack().getData());
            }
        }
    },
    CreateOrder(false) {
        @Override
        public void enter(BotContext context) {
            context.getOrderService().createOrder(context.getCallBack().getFrom().getUserName());
            sendMessage(context, ORDER_CONTACT_RECEIVED);
            log.info("order has been created");
        }

        @Override
        public BotState nextState(BotContext context) {
            return Menu;
        }

        @Override
        protected void handleInput(BotContext context) {

        }
    },
    BotFeatures(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, BOT_FEATURES_TEXT);
        }

        @Override
        public BotState nextState(BotContext context) {
            return Menu;
        }

        @Override
        protected void handleInput(BotContext context) {

        }
    },
    AboutCompany(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, ABOUT_COMPANY_TEXT);
        }

        @Override
        public BotState nextState(BotContext context) {
            return Menu;
        }

        @Override
        protected void handleInput(BotContext context) {

        }
    },
    UsersOrders(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, BotConstants.getAllUsersOrdersMessage(context.getOrderService()
                    .findByUserId(context.getUserTelegram().getUsername())));
        }

        @Override
        public BotState nextState(BotContext context) {
            return Menu;
        }

        @Override
        protected void handleInput(BotContext context) {

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
        if (!text.equals("")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(context.getUserTelegram().getChatId());
            sendMessage.setText(text);

            try {
                log.info("send message: " + text);
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
    public abstract BotState nextState(BotContext context);

    protected abstract void handleInput(BotContext context);
}
