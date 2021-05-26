import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Game implements KeyListener{

    JFrame frame;
    Board board;
    public Game(){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("PONG");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        board = new Board();
        frame.add(board);
        frame.addKeyListener(this);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            board.playerUp();
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            board.playerDown();
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            board.startPause();
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_P){
            board.reset();
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.board.init();
    }


}
