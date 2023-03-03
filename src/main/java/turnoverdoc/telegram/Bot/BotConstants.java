package turnoverdoc.telegram.Bot;

public class BotConstants {
    public static final String START_MESSAGE = "Я - бот Turnoverdoc, который поможет вам в оформлении заявок. " +
            "С помощью меня вы можете узнать статус уже существующих заявок и подписаться на уведомления об их изменении, задать любой вопрос в техподдержку и многое другое.";
    public static final String MENU_MESSAGE = "Выберите одну из пунктов меню ниже";
    public static final String CREATE_ORDER_BUTTON = "Начать оформление заявки";
    public static final String BOT_FEATURES_BUTTON = "Возможности бота";
    public static final String ABOUT_COMPANY_BUTTON = "О нас";
    public static final String USERS_ORDERS_BUTTON = "Активные заявки";
    public static final String INVALID_INPUT = "Вы ввели некорректное значение. Попробуйте еще раз";
    public static final String ORDER_CONTACT_RECEIVED = "Заявка успешно оформлена. В ближайшее время с вами свяжется адмнистратор";
    public static final String BOT_FEATURES_TEXT = getBotFeaturesText();
    public static final String ABOUT_COMPANY_TEXT = "Текст о компании";
    public static final String TELEGRAM_NOT_LINKED = "Ваш аккаунт телеграма не привязан к профилю. Оформление через бота доступно только для привязанных аккаунтов";

    private static String getBotFeaturesText() {
        return """
                С помощью бота вы можете:
                1) Узнать о компании
                2) Подписаться на уведомления о смене статуса ваших заявок
                3) Узнать подробнее о статусе любой из ваших созданных заявок""";
    }

}
