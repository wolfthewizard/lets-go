package main;


import contract.Change;

import java.util.List;


public interface Game {

    void clearServerResponse();
    void setOpponentsCaptives(int nOfCaptives);
    void setPlayersCaptives(int nOfCaptives);
    void signalPlayersMove();
    void signalOpponentsMove();
    void signalInvalidMove();
    void signalOpponentsLeave();
    void signalWin();
    void signalLose();
    void signalTie();
    void openNewGameCreation();
    void closeGame();
    void exitApp();
    void signalFailedToCreateGame();
    void signalServerFailed();
    void enforceChanges(List<Change> changes);
}
