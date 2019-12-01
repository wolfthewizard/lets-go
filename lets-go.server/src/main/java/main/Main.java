package main;

import core.ICommandDirector;
import core.model.Move;
import core.model.MoveExecution;
import core.model.MoveIdentity;
import javafx.util.Pair;
import main.contract.BoardSize;
import main.helpers.*;

public class Main {

    public static void main(String[] args) {
        IJsonParser jsonParser = new JsonParser();
        ServerListener serverCommunicator = new ServerListener(jsonParser, new ActionProcesser(jsonParser,
                new PlayerValidator(), new ICommandDirector() {
            @Override
            public Pair<Integer, MoveExecution> CreateNewBotGame(boolean letBotStart, BoardSize boardSize) {
                return new Pair<>(11, new MoveExecution());
            }

            @Override
            public int CreateNewMultiplayerGame() {
                return 5;
            }

            @Override
            public MoveExecution TryToMove(Move move) {
                return null;
            }

            @Override
            public void CancelGame(MoveIdentity leftIdentity) {

            }
        }, new IdGenerator()));
    }
}
