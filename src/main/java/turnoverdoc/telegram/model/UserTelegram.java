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

    private String username;

    public UserTelegram(Long chatId, Integer state, String username) {
        this.chatId = chatId;
        this.stateId = state;
        this.username = username;
    }

    public UserTelegram() {
    }
}
