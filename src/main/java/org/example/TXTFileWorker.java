package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TXTFileWorker {
    public static GUIBoard TxtReader(String fileName){
        int rows;
        int columns;
        int iterations;
        int numberOfCoordinates;
        int [][] coordinates;
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            rows = scanner.nextInt();
            columns = scanner.nextInt();
            iterations = scanner.nextInt();
            numberOfCoordinates = scanner.nextInt();
            coordinates = new int[numberOfCoordinates][2];
            for(int i=0; i<numberOfCoordinates; i++){
                coordinates[i][0] = scanner.nextInt();
                coordinates[i][1] = scanner.nextInt();
            }

            if (!ValidationAndThreads.SizeValidation(rows, columns, numberOfCoordinates, coordinates))
                throw new IllegalArgumentException("Incorrect coordinates!");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new GUIBoard(rows, columns, iterations, numberOfCoordinates,coordinates);
    }
}
