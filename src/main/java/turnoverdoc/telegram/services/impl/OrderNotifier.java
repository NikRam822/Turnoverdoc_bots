package turnoverdoc.telegram.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import turnoverdoc.telegram.Bot.BotConstants;
import turnoverdoc.telegram.Bot.botapi.ChatBot;
import turnoverdoc.telegram.dto.TurnoverNotifyDto;
import turnoverdoc.telegram.model.OrderStatus;
import turnoverdoc.telegram.model.UserTelegram;
import turnoverdoc.telegram.services.Notifier;
import turnoverdoc.telegram.services.UserService;

@Service
public class OrderNotifier implements Notifier {
    private ChatBot chatBot;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setChatBot(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    @Override
    public void sendStatusNotify(TurnoverNotifyDto turnoverNotifyDto) {
        UserTelegram userTelegram = userService.findByUsername(turnoverNotifyDto.getUsername());

        if (userTelegram != null) {
            SendMessage notifyMessage = new SendMessage();
            notifyMessage.setChatId(userTelegram.getChatId());
            notifyMessage.setText(BotConstants.getBotNotifyStatusMessage(OrderStatus.valueOf(turnoverNotifyDto.getOrderStatus()), turnoverNotifyDto.getApplicationId()));

            try {
                chatBot.execute(notifyMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
