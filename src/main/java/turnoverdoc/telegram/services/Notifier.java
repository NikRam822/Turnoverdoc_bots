package turnoverdoc.telegram.services;

import turnoverdoc.telegram.dto.TurnoverNotifyDto;

public interface Notifier {
    void sendStatusNotify(TurnoverNotifyDto turnoverNotifyDto);
}
