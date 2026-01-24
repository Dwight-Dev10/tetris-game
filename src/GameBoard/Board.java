package GameBoard;

import arks.Arks_main;
import arks.Block;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    // Board owns static blocks
    private final ArrayList<Block> blocks = new ArrayList<>();

    public void addArks(Arks_main arks) {
        for (Block b : arks.b) {
            blocks.add(b);
        }
    }

    public int getBlockLength(){
        return blocks.size();
    }

    public void draw(Graphics2D g2) {
        for (Block b : blocks) {
            b.draw(g2);
        }
    }

}

