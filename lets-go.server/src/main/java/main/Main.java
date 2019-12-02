package main;

import contract.Prisoners;
import core.ICommandDirector;
import core.model.*;
import contract.enums.BoardSize;
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
            public CreateNewBotGameResult CreateNewBotGame(boolean letBotStart, BoardSize boardSize) {
                return new CreateNewBotGameResult(11, new ArrayList<>(), new Prisoners(0,0));
            }

            @Override
            public int CreateNewMultiplayerGame() {
                return 5;
            }

            @Override
            public MoveExecution TryToMove(Move move) {
                return new MoveExecution(new ArrayList<>(), new Prisoners(0,0));
            }

            @Override
            public void CancelGame(MoveIdentity leftIdentity) {

            }
        }, clientsManager), new IdGenerator(), clientsManager);
    }
}
