package arks;

import java.awt.*;

public class Arks_Square extends Arks_main {
    public Arks_Square(){
        create(Color.white);
    }
    public void setXY(int x, int y) {
        // o o
        // o o
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y + Block.SIZE;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;

    }
    public void getDirections1() {
    }
    public void getDirections2() {
    }
    public void getDirections3() {
    }
    public void getDirections4() {
    }
}
