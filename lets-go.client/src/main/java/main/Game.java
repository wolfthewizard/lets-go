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
    void signalWin(int gameId);
    void signalLose(int gameId);
    void signalTie(int gameId);
    void openNewGameCreation();
    void closeGame();
    void exitApp();
    void signalFailedToCreateGame();
    void signalServerFailed();
    void enforceChanges(List<Change> changes);
}
