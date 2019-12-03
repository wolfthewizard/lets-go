package frontend;


import contract.Change;

import java.util.ArrayList;


public interface Game {

    void clearServerResponse();
    void setOpponentsCaptives(int nOfCaptives);
    void setPlayersCaptives(int nOfCaptives);
    void signalPlayersMove();
    void signalOpponentsMove();
    void signalInvalidMove();
    void failedToCreateGame();
    void serverFailed();
    void enforceChanges(ArrayList<Change> changes);
}
