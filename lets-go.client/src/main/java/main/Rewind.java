package main;

public interface Rewind {

    void signalPass();
    void signalWinner(/* todo : add color */);
    void clearMoveNote();
}
