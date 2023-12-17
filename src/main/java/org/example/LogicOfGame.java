package org.example;
import java.awt.*;
import java.util.concurrent.CyclicBarrier;

public class LogicOfGame implements Runnable{

    private final int starColumn;
    private final int endColumn;
    private final CyclicBarrier barrier;
    private final GUIBoard game;
    private final Color color;
    public LogicOfGame(int starColumn, int endColumn, CyclicBarrier barrier, GUIBoard game, Color color) {
        this.starColumn = starColumn;
        this.endColumn = endColumn;
        this.barrier = barrier;
        this.game = game;
        this.color = color;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i<game.getIterations(); i++){
            try {
                int [][] newGameBoard = new int[game.getRows()][game.getColumns()];
                GameRule(newGameBoard);
                barrier.await();
                game.PanelChanging(newGameBoard, starColumn, endColumn, color);
                Thread.sleep(1700);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void GameRule(int[][] newGameBoard){
        for (int i = 0; i < game.getRows(); i++){
            for (int j = starColumn; j <= endColumn; j++){

                int cellsNumber = NumberOfNeighbours(i, j, game.board);

                if (game.board[i][j] == 1 && (cellsNumber == 2 || cellsNumber == 3)){
                    newGameBoard[i][j] = 1;
                } else if (game.board[i][j] == 0 && (cellsNumber == 3)){
                    newGameBoard[i][j] = 1;
                }else {
                    newGameBoard[i][j] = 0;
                }
            }
        }
    }

    public int NumberOfNeighbours(int rows, int columns, int [][] board){
        int numberOfCells = 0;

        if (rows == 0 && columns == 0){ // top left
            numberOfCells += board[0][1];
            numberOfCells += board[1][0];
            numberOfCells += board[1][1];
            numberOfCells += board[game.getRows() - 1][0];
            numberOfCells += board[game.getRows() - 1][1];
            numberOfCells += board[0][game.getColumns() - 1];
            numberOfCells += board[1][game.getColumns() - 1];
            numberOfCells += board[game.getRows() - 1][game.getColumns() - 1];

        } else if (rows == 0 && columns == (game.getColumns() - 1)) /* top right */{

            numberOfCells += board[0][game.getColumns() - 2];
            numberOfCells += board[1][game.getColumns() - 2];
            numberOfCells += board[1][game.getColumns() - 1];
            numberOfCells += board[0][0];
            numberOfCells += board[1][0];
            numberOfCells += board[game.getRows() - 1][0];
            numberOfCells += board[game.getRows() - 1][game.getColumns() - 2];
            numberOfCells += board[game.getRows() - 1][game.getColumns() - 1];

        } else if (rows == (game.getRows() - 1) && columns ==0) /* bottom left */{

            numberOfCells += board[0][0];
            numberOfCells += board[0][1];
            numberOfCells += board[game.getRows() - 2][0];
            numberOfCells += board[game.getRows() - 2][1];
            numberOfCells += board[game.getRows() - 1][1];
            numberOfCells += board[0][game.getColumns() - 1];
            numberOfCells += board[game.getRows() - 1][game.getColumns() - 1];
            numberOfCells += board[game.getRows() - 2][game.getColumns() - 1];

        } else if (rows == (game.getRows()) - 1 && (columns == game.getColumns() - 1)) /* bottom right */{

            numberOfCells += board[0][0];
            numberOfCells += board[0][game.getColumns() - 2];
            numberOfCells += board[0][game.getColumns() - 1];
            numberOfCells += board[game.getRows() - 1][0];
            numberOfCells += board[game.getRows() - 2][0];
            numberOfCells += board[game.getRows() - 2][game.getColumns() - 1];
            numberOfCells += board[game.getRows() - 2][game.getColumns() - 2];
            numberOfCells += board[game.getRows() - 1][game.getColumns() - 2];

        } else if (rows == 0) /* top */{ //columns != 0 && (columns == game.getColumns() - 1)

            numberOfCells += board[game.getRows() - 1][columns - 1];
            numberOfCells += board[game.getRows() - 1][columns];
            numberOfCells += board[game.getRows() - 1][columns + 1];
            numberOfCells += board[rows][columns - 1];
            numberOfCells += board[rows][columns + 1];
            numberOfCells += board[rows + 1][columns - 1];
            numberOfCells += board[rows + 1][columns];
            numberOfCells += board[rows + 1][columns + 1];

        } else if (rows == (game.getRows() - 1)) /* bottom */ {

            numberOfCells += board[rows - 1][columns - 1];
            numberOfCells += board[rows - 1][columns];
            numberOfCells += board[rows - 1][columns + 1];
            numberOfCells += board[rows][columns - 1];
            numberOfCells += board[rows][columns + 1];
            numberOfCells += board[0][columns - 1];
            numberOfCells += board[0][columns];
            numberOfCells += board[0][columns + 1];

        } else if (columns == 0) { /* left */

            numberOfCells += board[rows - 1][game.getColumns() - 1];
            numberOfCells += board[rows - 1][columns];
            numberOfCells += board[rows - 1][columns + 1];
            numberOfCells += board[rows][game.getColumns() - 1];
            numberOfCells += board[rows][columns + 1];
            numberOfCells += board[rows + 1][game.getColumns() - 1];
            numberOfCells += board[rows + 1][columns];
            numberOfCells += board[rows + 1][columns + 1];

        } else if (columns == (game.getColumns()) - 1) /* right */{

            numberOfCells += board[rows - 1][columns - 1];
            numberOfCells += board[rows - 1][columns];
            numberOfCells += board[rows - 1][0];
            numberOfCells += board[rows][columns - 1];
            numberOfCells += board[rows][0];
            numberOfCells += board[rows + 1][columns - 1];
            numberOfCells += board[rows + 1][columns];
            numberOfCells += board[rows + 1][0];

        } else {

            numberOfCells += board[rows - 1][columns - 1];
            numberOfCells += board[rows - 1][columns];
            numberOfCells += board[rows - 1][columns + 1];
            numberOfCells += board[rows][columns - 1];
            numberOfCells += board[rows][columns + 1];
            numberOfCells += board[rows + 1][columns - 1];
            numberOfCells += board[rows + 1][columns];
            numberOfCells += board[rows + 1][columns + 1];

        }

        return numberOfCells;
    }
}
