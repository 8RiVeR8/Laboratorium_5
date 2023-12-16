package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class LogicOfGame implements Runnable{

    private int starColumn;
    private int endColumn;
    private CyclicBarrier barrier;
    private GUIBoard game;
    public LogicOfGame(int starColumn, int endColumn, CyclicBarrier barrier, GUIBoard game) {
        this.starColumn = starColumn;
        this.endColumn = endColumn;
        this.barrier = barrier;
        this.game = game;
    }
    @Override
    public void run() {
        for (int i = 0; i<game.getIterations(); i++){
            try {
                int [][] newGameBoard = new int[game.getRows()][game.getColumns()];
                GameRule(newGameBoard);
                barrier.await();
                game.PanelChanging(newGameBoard, starColumn, endColumn);
                Thread.sleep(1700);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void GameRule(int[][] board){
        for (int i = 0; i < game.getRows(); i++){
            for (int j = starColumn; j <= endColumn; j++){

                int cellsNumber = NumberOfNeighbours();

                if (board[i][j] == 1 && (cellsNumber == 2 || cellsNumber == 3)){
                    board[i][j] = 1;
                } else if (board[i][j] == 0 && (cellsNumber == 3)){
                    board[i][j] = 1;
                }else {
                    board[i][j] = 0;
                }
            }
        }
    }

    public int NumberOfNeighbours(){

        return 0;
    }
}
