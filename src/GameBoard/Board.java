package GameBoard;

import arks.Arks_main;
import arks.Block;

import java.awt.*;
import java.util.ArrayList;

import static main.PlayManager.*;

public class Board {
    // Board owns static blocks
    private final ArrayList<Block> blocks = new ArrayList<>();

    //effect

    // Deletes line
    public void checkDelete () {
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

