package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean UpPressed, downPressed, leftPressed, rightPressed, pausePressed;

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // only method used
        int code = keyEvent.getKeyCode();

        if (code == keyEvent.VK_W || code == KeyEvent.VK_UP){
            UpPressed = true;

        }
        if (code == keyEvent.VK_A || code == keyEvent.VK_LEFT){
            leftPressed = true;

        }
        if (code == keyEvent.VK_S || code == keyEvent.VK_DOWN){
            downPressed = true;

        }
        if (code == keyEvent.VK_D || code == keyEvent.VK_RIGHT){
            rightPressed = true;

        }
        if (code == KeyEvent.VK_SPACE){
            if (pausePressed){
                pausePressed = false;
            }
            else{
                pausePressed = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }
}
