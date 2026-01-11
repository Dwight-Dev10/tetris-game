import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //System.out.printf("Hello and welcome!");

        JFrame window = new JFrame("Simple Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        window.setResizable(false); // prevent frame from being resized by users

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // window.setSize(420,420);


    }
}