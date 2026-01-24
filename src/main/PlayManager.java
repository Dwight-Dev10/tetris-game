package main;

import GameBoard.Board;
import GameBoard.GamePanel;
import GameBoard.KeyHandler;
import arks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayManager {
    // Handles gameplay actions - deleting lines, adding scores

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
    //private final Board board = new Board();
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    // Arks dropdown
    public static int dropIntrerval = 60; // arks drops every 60 frames

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
        //currentArks = new Arks_L2();
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

            currentArks.deactivating = false;

            //board.addArks(currentArks);

            // replace the current Arks with the next ARK
            currentArks = nextArk;
            currentArks.setXY(ARKS_START_X, ARKS_START_Y);
            nextArk = pickArk();
            nextArk.setXY(NEXTARK_X, NEXTARK_Y);

            // when a ark beomes inactive, check if lines can ve deleted
            checkDelete();
        }
        currentArks.update();
    }
    private void checkDelete () {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;

        while (x < right_x &&  y < bottom_y){

            for ( int i = 0; i < staticBlocks.size(); i++) {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
                    // increase the count if there is a static block
                    blockCount++;
                }
            }
            x += Block.SIZE;

            if ( x == right_x) {

                // if the block count hits 12, the current y line is all filled with blocks
                // so we can delete them
                if (blockCount == 12) {

                    for( int i = staticBlocks.size() -1; i > -1; i--) {
                        // remove akk the blocks in the current y line
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }

                    // line has been deleted so need to slide down blocks that are above is
                    for (int i = 0; i < staticBlocks.size(); i++){
                        // if a block is above the current y, move it down
                        if (staticBlocks.get(i).y < y){
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }
                }
                blockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }
        }
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

        // Draw the currentArk
        if(currentArks != null){
            currentArks.draw(g2);
        }

        // Draw the next ark
        nextArk.draw(g2);
        //board.draw(g2);
        for ( int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(g2);
        }

        // Draw Pause
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
        if (KeyHandler.pausePressed){
            x = left_x + 20;
            y = top_y + 320;
            g2.drawString("Game Paused", x ,y);
        }

    }

}

