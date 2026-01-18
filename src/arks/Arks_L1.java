package arks;


import java.awt.*;

public class Arks_L1 extends Arks_main {

    public Arks_L1() {
        create(Color.orange);
    }

    public void setXY(int x, int y){
        // o
        // o This position does not change
        // o o o
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y - Block.SIZE;
        b[2].x = b[0].x;
        b[2].y = b[0].y + Block.SIZE;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;
    }
}
