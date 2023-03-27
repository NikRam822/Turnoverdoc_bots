package turnoverdoc.telegram.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import turnoverdoc.telegram.dto.TurnoverNotifyDto;

@RestController
public class TurnoverController {

    @PostMapping("/main")
    public ResponseEntity<TurnoverNotifyDto> turnoverNotify(@RequestBody TurnoverNotifyDto turnoverNotifyDto) {

        return new ResponseEntity<>(turnoverNotifyDto, HttpStatus.OK);
    }
}
