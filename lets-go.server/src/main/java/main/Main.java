package main;

import contract.Change;
import contract.Prisoners;
import contract.enums.Occupancy;
import core.interfaces.ICommandDirector;
import core.model.*;
import contract.enums.BoardSize;
import core.model.enums.MoveResponseType;
import main.helpers.*;
import core.model.Color;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        IJsonParser jsonParser = new JsonParser();
        IClientsManager clientsManager = new ClientsManager();
        ServerListener serverCommunicator = new ServerListener(new ActionProcesser(jsonParser,
                new PlayerValidator(), new ICommandDirector() {

            @Override
            public int CreateNewGame() {
                return 5;
            }

            @Override
            public MoveResponse TryToMove(Move move) {
                ArrayList<Change> changes = new ArrayList<>();
                if(move.getCoordinates() != null) {
                    if(move.getPlayerColor() == Color.BLACK) {
                        changes.add(new Change(Occupancy.BLACK, move.getCoordinates()));

                    } else {
                        changes.add(new Change(Occupancy.WHITE, move.getCoordinates()));
                    }
                }
                return new MoveResponse(MoveResponseType.GAME_GOES_ON, new MoveExecution(changes, new Prisoners(0,0)));
            }

            @Override
            public void CancelGame(MoveIdentity leftIdentity) {

            }
        }, clientsManager), clientsManager);
    }
}
