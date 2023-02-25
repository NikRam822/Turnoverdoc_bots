package turnoverdoc.telegram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private Long chatId;

    private Integer stateId;

    public User(Long chatId, Integer state) {
        this.chatId = chatId;
        this.stateId = state;
    }
}
