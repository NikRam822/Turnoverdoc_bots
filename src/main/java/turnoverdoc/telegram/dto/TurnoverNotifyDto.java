package turnoverdoc.telegram.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TurnoverNotifyDto implements Serializable {
    private String orderStatus;
    private String username;
    private String applicationId;
}
