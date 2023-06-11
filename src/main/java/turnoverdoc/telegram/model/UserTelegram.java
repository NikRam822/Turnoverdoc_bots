package turnoverdoc.telegram.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
