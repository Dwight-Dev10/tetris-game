package GameBoard;

import main.PlayManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    /** GameBoard.GamePanel:
     *  a primary drawing surface where all game elements
     * (characters, backgrounds, objects) are
     * rendered and updated within a game loop **/

    // Screen Size
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    final int FPS = 60;
    Thread gameThread;
    final PlayManager pm;

    public GamePanel(PlayManager pm){


        // Panel Settings
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setLayout(null);

        // KeyListeners
        addKeyListener(new KeyHandler());
        setFocusable(true);
        this.pm = pm;
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
        if (!KeyHandler.pausePressed) {
            pm.update();
        }
    }
    // Draw the screen with the update information.
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        pm.draw(g2);
    }


}
