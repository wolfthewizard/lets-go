package main;

import core.ICommandDirector;
import core.model.Change;
import core.model.Move;
import core.model.MoveIdentity;
import javafx.util.Pair;
import main.contract.enums.BoardSize;
import main.helpers.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        IJsonParser jsonParser = new JsonParser();
        IClientsManager clientsManager = new ClientsManager();
        ServerListener serverCommunicator = new ServerListener(new ActionProcesser(jsonParser,
                new PlayerValidator(), new ICommandDirector() {
            @Override
            public Pair<Integer, ArrayList<Change>> CreateNewBotGame(boolean letBotStart, BoardSize boardSize) {
                return new Pair<>(11, new ArrayList<>());
            }

            @Override
            public int CreateNewMultiplayerGame() {
                return 5;
            }

            @Override
            public ArrayList<Change> TryToMove(Move move) {
                return null;
            }

            @Override
            public void CancelGame(MoveIdentity leftIdentity) {

            }
        }, clientsManager), new IdGenerator(), clientsManager);
    }
}
