package com.epam.rd.autocode.concurrenttictactoe;

public class TicTacPlayer implements Player {
    private TicTacToe ticTacToe;
    private char mark;
    private PlayerStrategy strategy;

    public TicTacPlayer(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        while (!isWinner()) {
            synchronized (ticTacToe) {
                if (mark != ticTacToe.lastMark() && !isWinner() && isFreePlace()) {
                    Move move = strategy.computeMove(mark, ticTacToe);
                    ticTacToe.setMark(move.row, move.column, mark);
                }
            }
        }
    }

    private boolean isFreePlace() {
        for (int i = 0; i < ticTacToe.table().length; i++) {
            for (int j = 0; j < ticTacToe.table()[0].length; j++) {
                if (ticTacToe.table()[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinner() {
        for (int i = 0; i < ticTacToe.table().length; i++) {
            char row = ticTacToe.table()[i][0];
            if (row != ' ' && ticTacToe.table()[i][1] == row && ticTacToe.table()[i][2] == row) {
                return true;
            }
        }
        for (int j = 0; j < ticTacToe.table()[0].length; j++) {
            char column = ticTacToe.table()[0][j];
            if (column != ' ' && ticTacToe.table()[1][j] == column && ticTacToe.table()[2][j] == column) {
                return true;
            }
        }
        char diagonal = ticTacToe.table()[1][1];
        if (diagonal == ' ') {
            return false;
        }
        if ((ticTacToe.table()[0][0] == diagonal && ticTacToe.table()[2][2] == diagonal)
                || (ticTacToe.table()[0][2] == diagonal && ticTacToe.table()[2][0] == diagonal)) {
            return true;
        }
        return false;
    }
}