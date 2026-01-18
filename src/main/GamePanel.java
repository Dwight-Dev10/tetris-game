package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    /** main.GamePanel:
     *  a primary drawing surface where all game elements
     * (characters, backgrounds, objects) are
     * rendered and updated within a game loop **/

    // Screen Size
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    final int FPS = 60;
    Thread gameThread;
    PlayManager pm;

    public GamePanel(){

        // Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);

        // KeyListeners
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);


        pm = new PlayManager();
    }
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start(); // Automatically calls run
    }

    @Override
    public void run() {
        // Game Loop
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update(){
        // Update information such as character position
        pm.update();
    }

    // Draw the screen with the update information.
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        pm.draw(g2);
    }


}
