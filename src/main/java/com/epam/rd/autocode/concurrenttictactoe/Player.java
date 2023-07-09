package com.epam.rd.autocode.concurrenttictactoe;

public interface Player extends Runnable {
    static Player createPlayer(final TicTacToe ticTacToe, final char mark, PlayerStrategy strategy) {
        return new PlayerImpl(ticTacToe,mark,strategy);
    }
}
