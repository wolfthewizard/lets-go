package core.interfaces;

import core.model.Color;

public interface ITurnExecutor {

    void executeTurn(boolean lastTurnInvalid);

    void setMyColor(Color color);
}
