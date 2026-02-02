package GameBoard;

import arks.Arks_main;
import arks.Block;
import main.PlayManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;

import static main.PlayManager.*;

public class Board {
    // Board owns static blocks
    private final ArrayList<Block> blocks = new ArrayList<>();

    // effect
    public boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();
    //PlayManager pm = new PlayManager();
    // Score



    // Deletes line
    public void checkDelete () {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;

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
                    effectCounterOn = true;
                    effectY.add(y);

                    for( int i = staticBlocks.size() -1; i > -1; i--) {
                        // remove akk the blocks in the current y line
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }
                    lineCount++;
                    PlayManager.lines++;
                    //Drop speed
                    // if the line score hits a certain number, increase the drop speed
                    // 1 is the fastest
                    if (PlayManager.lines % 10 == 0 && dropInterval >1 ) {
                        PlayManager.level++;
                        if(dropInterval > 10) {
                            dropInterval -= 10;
                        }else {
                            dropInterval -=1 ;
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
        // Add Score
        if(lineCount > 0){
            GamePanel.se.play(1,false); // Sound

            int singleLinesScore = 10 * PlayManager.level;
            PlayManager.score += singleLinesScore * lineCount;
        }
    }
    public void drawBoard(Graphics2D g2){
        // When line is complete
        if (effectCounterOn){
            effectCounter++;

            g2.setColor(Color.RED);
            for (int i = 0; i < effectY.size(); i++ ){
                g2.fillRect(left_x, effectY.get(i), 360, Block.SIZE );
            }

            if (effectCounter == 10) {
                effectCounterOn = false;
                effectCounter = 0;
                effectY.clear();
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

