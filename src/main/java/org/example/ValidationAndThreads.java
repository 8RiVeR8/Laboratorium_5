package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class ValidationAndThreads{

    public static void MakeThreads(int numberOfThreads, int columns, int rows, GUIBoard game){

        if (ValidationThreads(numberOfThreads, columns))
            throw new IllegalArgumentException("Too many threads!");

        int overflow = columns % numberOfThreads;
        int howMuch = columns / numberOfThreads;
        int startIndex = 0;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads);
        ArrayList<Color> color = new ArrayList<>();
        color.add(Color.PINK);
        color.add(Color.ORANGE);
        color.add(Color.BLUE);
        color.add(Color.RED);
        color.add(Color.GREEN);

        System.out.println("# " + numberOfThreads + " threads, column - based partitioning");

        for (int i=0; i<numberOfThreads; i++) {
            int endIndex = startIndex + howMuch - 1;

            if (overflow != 0) {
                endIndex += 1;
                overflow -= 1;
            }

            Thread newThread = new Thread(new LogicOfGame(startIndex, endIndex, barrier, game, color.get(i)));
            newThread.start();

            System.out.println("tid " + i + ": rows: 0:" + (rows - 1) + " (" + rows + ")" + " cols: " + startIndex + ":" + endIndex + " " + "(" + (endIndex - startIndex + 1) + ")" );

            startIndex = endIndex + 1;
        }
    }

    public static boolean ValidationThreads(int numberOfThreads, int columns){
        return columns < numberOfThreads;
    }

    public static boolean SizeValidation(int rows, int columns, int numberOfCoordinates, int [][] coordinates){
        for (int i=0; i < numberOfCoordinates; i++){
            if (coordinates[i][0] >= rows)
                return false;
            if (coordinates[i][1] >= columns)
                return false;
        }
        return true;
    }
}
