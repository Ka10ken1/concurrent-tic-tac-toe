package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacImpl implements TicTacToe {
    private char lastMark = 'O';
    private final char[][] table = new char[3][3];

    public TicTacImpl() {
        for (int i = 0; i < table.length; i++) {
            Arrays.fill(table[i], ' ');
        }
    }

    @Override
    public void setMark(int x, int y, char mark) {
        if (table[x][y] == ' ') {
            table[x][y] = mark;
        } else {
            throw new IllegalArgumentException("Cannot set mark twice");
        }
        lastMark = mark;
    }

    @Override
    public char[][] table() {
        char[][] gameTable = new char[3][3];
        for (int i = 0; i < table.length; i++) {
            gameTable[i] = Arrays.copyOf(table[i], table.length);
        }
        return gameTable;
    }

    @Override
    public char lastMark() {
        return lastMark;
    }
}