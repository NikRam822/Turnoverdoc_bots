package turnoverdoc.telegram.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import turnoverdoc.telegram.dto.TurnoverNotifyDto;
import turnoverdoc.telegram.services.Notifier;

@RestController
public class TurnoverController {

    private Notifier orderNotifier;

    @Autowired
    public void setOrderNotifier(Notifier orderNotifier) {
        this.orderNotifier = orderNotifier;
    }

    @PostMapping("/main")
    public ResponseEntity<TurnoverNotifyDto> turnoverNotify(@RequestBody TurnoverNotifyDto turnoverNotifyDto) {
        orderNotifier.sendStatusNotify(turnoverNotifyDto);
        return new ResponseEntity<>(turnoverNotifyDto, HttpStatus.OK);
    }
}
