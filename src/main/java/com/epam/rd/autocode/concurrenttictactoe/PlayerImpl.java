package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImpl implements Player {
    private static final char WHITESPACE = ' ';
    private final TicTacToe board;
    private final PlayerStrategy strategy;
    private final char mark;

    public PlayerImpl(final TicTacToe board, final char mark, final PlayerStrategy strategy) {
        this.board = board;
        this.mark = mark;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        while (isNotFull() && gameIsNotEnded()) {
            synchronized (board) {
                if (canMove()) {
                    Move move = strategy.computeMove(mark, board);
                    board.setMark(move.row, move.column, mark);
                }
            }
        }
    }

    private boolean canMove() {
        return isNotFull()
                && gameIsNotEnded()
                && board.lastMark() != mark;
    }

    private boolean isNotFull() {
        for (int i = 0; i < board.table().length; i++) {
            for (int j = 0; j < board.table()[i].length; j++) {
                if (board.table()[i][j] == WHITESPACE) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean gameIsEnded() {
        if (mainDiagonalIsDone() || auxiliaryDiagonalIsDone()) {
            return true;
        }
        for (int i = 0; i < board.table().length; i++) {
            if (rowIsDone(i) || columnIsDone(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean gameIsNotEnded() {
        return !gameIsEnded();
    }

    private boolean mainDiagonalIsDone() {
        char cell = board.table()[0][0];
        for (int i = 0; i < board.table().length; i++) {
            if (board.table()[i][i] != cell) {
                return false;
            }
            cell = board.table()[i][i];
        }
        return cell != WHITESPACE;
    }

    private boolean auxiliaryDiagonalIsDone() {
        int lastRow = board.table().length - 1;
        char cell = board.table()[lastRow][0];
        for (int i = 0; i < board.table().length; i++) {
            if (board.table()[lastRow - i][i] != cell) {
                return false;
            }
            cell = board.table()[lastRow - i][i];
        }
        return cell != WHITESPACE;
    }

    private boolean rowIsDone(int row) {
        char cell = board.table()[row][0];
        for (int i = 0; i < board.table()[row].length; i++) {
            if (board.table()[row][i] != cell) {
                return false;
            }
            cell = board.table()[row][i];
        }
        return cell != WHITESPACE;
    }

    private boolean columnIsDone(int column) {
        char cell = board.table()[0][column];
        for (int i = 0; i < board.table().length; i++) {
            if (board.table()[i][column] != cell) {
                return false;
            }
            cell = board.table()[i][column];
        }
        return cell != WHITESPACE;
    }
}