package main;

import GameBoard.Board;
import GameBoard.GamePanel;
import GameBoard.KeyHandler;
import arks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayManager {

    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;


    // Arks
    Arks_main currentArks;
    final int ARKS_START_X;
    final int ARKS_START_Y;
    Arks_main nextArk;
    final int NEXTARK_X;
    final int NEXTARK_Y;
    private final Board board = new Board();
    public static ArrayList<Block> staticBlocks = new ArrayList<>();
    public boolean gameOver;
    public static int dropInterval = 60; // arks drops every 60 frames

//    public boolean effectCounterOn;
//    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();

    public static int level = 1;
    public static int lines;
    public static int score;

    public PlayManager() {
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2); // 1280/ 2 -360 /2 = 460
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        ARKS_START_X = left_x + (WIDTH/2) - Block.SIZE;
        ARKS_START_Y = top_y + Block.SIZE;

        NEXTARK_X = right_x + 175;
        NEXTARK_Y = top_y + 500;


        // Starting Ark
        currentArks = pickArk();
        currentArks.setXY(ARKS_START_X,ARKS_START_Y);

        // Next coming ark
        nextArk = pickArk();
        nextArk.setXY(NEXTARK_X, NEXTARK_Y);

    }
    private Arks_main pickArk(){

        // Pick random ark
        Arks_main arks = null;
        int i = new Random().nextInt(7); // 0 - 6
        switch (i){
            case 0: arks = new Arks_Bar();break;
            case 1: arks = new Arks_L1();break;
            case 2: arks = new Arks_L2();break;
            case 3: arks = new Arks_Square();break;
            case 4: arks = new Arks_T();break;
            case 5: arks = new Arks_Z1();break;
            case 6: arks = new Arks_Z2();break;
        }
        return arks;
    }

    public void update(){
        if (!currentArks.active){
            // if the ark is not active, put it in the static blocks
            staticBlocks.add(currentArks.b[0]);
            staticBlocks.add(currentArks.b[1]);
            staticBlocks.add(currentArks.b[2]);
            staticBlocks.add(currentArks.b[3]);

            // chek if the game is over
            if(currentArks.b[0].x == ARKS_START_X && currentArks.b[0].y == ARKS_START_Y){
                // this means the current ark immediately collided a block and couldnt move at all
                // so its xy are the same with the nextArk
                gameOver = true;
                //Sound
                GamePanel.music.stop();
                GamePanel.se.play(2,false);
            }

            currentArks.deactivating = false;

            // replace the current Arks with the next ARK
            currentArks = nextArk;
            currentArks.setXY(ARKS_START_X, ARKS_START_Y);
            nextArk = pickArk();
            nextArk.setXY(NEXTARK_X, NEXTARK_Y);

            // when an ark becomes inactive, check if lines can be deleted
            board.checkDelete();
            //checkDelete();
        }
        currentArks.update();
    }

    public void draw(Graphics2D g2) {

        // Draw play area Frame
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

        // Draw Next ARK Frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 60, y + 60);

        g2.drawRect(x, top_y, 250, 300);
        x += 40;
        y = top_y +90;
        g2.drawString("LEVEL: " + level, x, y); y += 70;
        g2.drawString("LINES: " + lines, x, y); y += 70;
        g2.drawString("SCORE: " + score, x, y);

        // Draw the currentArk
        if(currentArks != null){
            currentArks.draw(g2);
        }

        // Draw the next ark
        nextArk.draw(g2);

        for ( int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(g2);
        }

        //Draw effects
        board.drawBoard(g2);

        // Draw Pause or Game Over
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
        if(gameOver){
            x = left_x + 25;
            y = top_y + 320;
            g2.drawString("Game Over Loser!!!", x, y);
        } else if (KeyHandler.pausePressed){
            x = left_x + 20;
            y = top_y + 320;
            g2.drawString("Game Paused", x ,y);
        }

        // Draw the Game Title
        x = 35;
        y = top_y + 320;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Times New Roman", Font.ITALIC, 60));
        g2.drawString("ArkTris", x + 2, y);


    }

}

