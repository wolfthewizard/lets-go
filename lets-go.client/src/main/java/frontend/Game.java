package frontend;

import core.contract.Change;

import java.util.List;

public interface Game {

    void clearServerResponse();
    void setOpponentsCaptives(int nOfCaptives);
    void setPlayersCaptives(int nOfCaptives);
    void signalPlayersMove();
    void signalOpponentsMove();
    void signalInvalidMove();
    void failedToCreateGame();
    void serverFailed();
    void enforceChanges(List<Change> changes);
}
