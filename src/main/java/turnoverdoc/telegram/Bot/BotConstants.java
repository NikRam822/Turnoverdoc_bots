package turnoverdoc.telegram.Bot;

import turnoverdoc.telegram.model.Order;
import turnoverdoc.telegram.model.OrderStatus;

import java.util.List;

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
        return "С помощью бота вы можете: \n" +
                "1) Узнать о компании\n" +
                "2) Подписаться на уведомления о смене статуса ваших заявок\n" +
                "3) Узнать подробнее о статусе любой из ваших созданных заявок";
    }

    public static String getBotNotifyStatusMessage(OrderStatus orderStatus, String applicationId) {
        return "Обновлен статус по вашей заявке " + applicationId + "\n" +
                "Текущий статус: " + orderStatus.getStatusName() + "\n" +
                orderStatus.getStatusDescription();
    }

    // TODO: replace this method to another class
    public static String getAllUsersOrdersMessage(List<Order> orders) {
        StringBuilder stringBuilder = new StringBuilder();

        if (orders == null) {
            // TODO: change text
            stringBuilder.append("Мы не нашли ваши заявки");
            return stringBuilder.toString();
        }
        for (int i = 0; i < orders.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(orders.get(i).getId()).append("\n");
            stringBuilder.append("Текущий статус: ").append(orders.get(i).getStatus().getStatusName()).append("\n");
            stringBuilder.append(orders.get(i).getStatus().getStatusDescription());
            if (i != orders.size() - 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
