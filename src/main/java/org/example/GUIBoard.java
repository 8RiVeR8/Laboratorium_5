package org.example;

import javax.swing.*;
import java.awt.*;

public class GUIBoard extends JFrame {
    private int rows;
    private int columns;
    private int iterations;
    private int numberOfCoordinate;
    int[][] board;
    JPanel[][] panels;
    public GUIBoard(int rows, int columns, int iterations, int numberOfCoordinate,int [][] coordinates) {
        this.rows = rows;
        this.columns = columns;
        this.iterations = iterations;
        this.numberOfCoordinate = numberOfCoordinate;
        this.board = new int[rows][columns];

        setTitle("Game of Life");
        setSize(1000, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(rows, columns));
        panels = new JPanel[rows][columns];
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                panels[i][j] = panel;
                add(panel);
                board[i][j] = 0;
            }
        }
        setVisible(true);


        for (int i=0; i<numberOfCoordinate; i++){
            panels[coordinates[i][0]][coordinates[i][1]].setBackground(Color.BLACK);
            board[coordinates[i][0]][coordinates[i][1]] = 1;
        }
    }

    public synchronized void PanelChanging(int [][] newGameBoard, int starColumn, int endColumn){
        for (int i = 0; i < rows; i++){
            for (int j = starColumn; j <= endColumn; j++){
                board[i][j] = newGameBoard[i][j];
                panels[i][j].setBackground((board[i][j] == 1) ? Color.BLACK : Color.WHITE);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    public int getIterations() {
        return iterations;
    }

}
