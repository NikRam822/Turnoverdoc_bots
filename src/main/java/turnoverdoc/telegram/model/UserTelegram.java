package turnoverdoc.telegram.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_telegram")
public class UserTelegram {
    @Id
    private Long chatId;

    private Integer stateId;
    private String menuFunction;

    public UserTelegram(Long chatId, Integer state) {
        this.chatId = chatId;
        this.stateId = state;
    }

    public UserTelegram() {
    }
}
