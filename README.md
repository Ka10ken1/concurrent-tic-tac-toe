# Concurrent Tic-Tac-Toe

## Description

Concurrent Tic-Tac-Toe is an exercise where two concurrent threads represent players and try to edit the same 3x3 table by setting `X` and `O`.

Note that game table itself (interface [`com.epam.rd.autocode.concurrenttictactoe.TicTacToe`](src/main/java/com/epam/rd/autocode/concurrenttictactoe/TicTacToe.java)) is not very aware of the game rules.
The only thing it must restrict is setting a value in a cell that already has a value.
The game table must not restrict setting in a row several values that are the same.
The game table must not restrict setting a value after one of the players has won.
Refer to the methods Javadoc for details.

Implement the `buildGame` method of [com.epam.rd.autocode.concurrenttictactoe.TicTacToe](src/main/java/com/epam/rd/autocode/concurrenttictactoe/TicTacToe.java) to present your implementation.

[com.epam.rd.autocode.concurrenttictactoe.Player](src/main/java/com/epam/rd/autocode/concurrenttictactoe/Player.java) is an interface representing player routine.
It extends `Runnable` interface to make it possible to submit it to a thread for concurrent execution.
Implement the method `createPlayer` of [com.epam.rd.autocode.concurrenttictactoe.Player](src/main/java/com/epam/rd/autocode/concurrenttictactoe/Player.java) to supply your implementation of a `Player`.
Each player gets a game table to play, a mark (note that `X` player always goes first), and a strategy to compute next moves.
Players are aware of the game rules and will stop execution when one of them has won.
Players respect each other and take their turns one after another.

The game routine is as follows:
- An empty board is created.
- Two players gets the marks(`X` and `O`), the created board and strategies to compute their moves.
- Both players start execution in separate threads.
- Players stop execution when the game is over.
- The expected table state is compared to the actual one.

Note that each game may not last for more than 2 seconds.  
