package org.example;

public class Main {
    public static void main(String[] args) {
        GUIBoard game = TXTFileWorker.TxtReader(args[0]);
        ValidationAndThreads.MakeThreads(Integer.parseInt(args[1]), game.getColumns(), game.getRows(), game);

    }
}