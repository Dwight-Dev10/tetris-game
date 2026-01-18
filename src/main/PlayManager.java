package main;

import arks.Arks_main;
import arks.Arks_L1;
import arks.Block;

import java.awt.*;

public class PlayManager {
    // Draws the play area
    // Manages tetrominoes
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

    // Arks dropdown
    public static int dropIntrerval = 60; // arks drops every 60 frames

    public PlayManager() {
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2); // 1280/ 2 -360 /2 = 460
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        ARKS_START_X = left_x + (WIDTH/2) - Block.SIZE;
        ARKS_START_Y = top_y + Block.SIZE;

        // Starting Ark
        currentArks = new Arks_L1();
        currentArks.setXY(ARKS_START_X,ARKS_START_Y);

    }

    public void update(){
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

        // Draw the currentArk
        if(currentArks != null){
            currentArks.draw(g2);
        }

    }






}

