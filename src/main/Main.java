package main;

import GameBoard.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("Simple Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        window.setResizable(false); // prevent frame from being resized by users

        PlayManager game = new PlayManager();


        GamePanel gp = new GamePanel(game); //expected args
        window.add(gp);
        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.launchGame();
        // window.setSize(420,420);


    }
}