import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

    final int BALLD= 20, PaddleW = 20, PaddleH = 80, BORDER =10;

    int ballX = 0, ballY = 0, paddlePX = 0, paddlePY = 0, paddleCX = 0, paddleCY = 0;
    int ballDX = 3, ballDY = 4, paddleCDY =3, paddlePDY=10;
    int playerScore = 0, computerScore = 0;

    Timer timer;

    boolean MENU = true, PLAY = false, GAMEOVER = false;


    public Board(){
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLUE);
        timer  = new Timer(1000 / 60, this);

    }

    //INITIALIZATION
    public void init(){
        ballX = getWidth()/2-BALLD/2;
        ballY = getHeight()/2-BALLD/2;

        paddlePX = 0 + BORDER;
        paddlePY = getHeight()/2 - PaddleH/2;

        paddleCX = getWidth() - BORDER - PaddleW;
        paddleCY = getHeight()/2 - PaddleH/2;

    }

    public void checkCollisions(){

        Rectangle paddle1 = new Rectangle(paddlePX, paddlePY, PaddleW, PaddleH);
        Rectangle paddle2 = new Rectangle(paddleCX, paddleCY, PaddleW, PaddleH);
        Rectangle ball = new Rectangle(ballX, ballY, BALLD, BALLD);

        if(ball.intersects(paddle1) || ball.intersects(paddle2)){
            ballDX *=-1;
        }

        if(playerScore >= 10 || computerScore >=10){
            timer.stop();
            GAMEOVER = true;
            PLAY = false;
        }


    }

    public void move(){
        //Ball Movement

        if(ballX + BALLD >= getWidth()){
            playerScore +=1;
            init();
            ballDX*=-1;
            timer.stop();
        }

        if(ballX <= 0){
            computerScore+=1;
            init();
            ballDX*=-1;
            timer.stop();
        }


        if(ballY + BALLD >= getHeight() || ballY <=0){
            ballDY*=-1;
        }

        ballX += ballDX;
        ballY += ballDY;

        if(ballX + BALLD/2 > getWidth()/2){
            if(ballY > paddleCY+PaddleH/2+ 30){
                paddleCY+=paddleCDY;
            }
            if(ballY <= paddleCY+PaddleH/2 + 30){
                paddleCY-=paddleCDY;
            }
        }



    }

    public void reset(){
        playerScore=0;
        computerScore=0;
        init();
        MENU = false;
        GAMEOVER = false;
        PLAY = true;
        repaint();
    }

    public void playerUp(){
        paddlePY -= paddlePDY;
    }

    public void playerDown(){
        paddlePY += paddlePDY;
    }

    public void startPause(){
        if(timer.isRunning()){
            timer.stop();
        }
        else{
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        checkCollisions();
        move();
        repaint();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(Color.white);

        if(MENU){
            g.setFont(new Font("Serif,", Font.BOLD, 52));
            printSimpleString("Menu", getWidth(), 0, getHeight()/3, g);
            printSimpleString("PRESS P TO PLAY", getWidth(), 0, getHeight()/3 * 2, g);
        }
        else if(PLAY){
            g.fillOval(ballX, ballY, BALLD, BALLD);
            g.fillRect(paddlePX, paddlePY, PaddleW, PaddleH);
            g.fillRect(paddleCX, paddleCY, PaddleW, PaddleH);

            g.setFont(new Font("Serif", Font.BOLD, 36));
            printSimpleString(Integer.toString(playerScore), getWidth()/2, 0,BORDER*3, g);
            printSimpleString(Integer.toString(computerScore), getWidth()/2 , getWidth()/2, BORDER*3, g);
            g.fillRect(getWidth()/2- BORDER/2, 0, BORDER, getHeight());

        }

        else if(GAMEOVER){
            g.setFont(new Font("Serif,", Font.BOLD, 52));
            printSimpleString("GAME OVER", getWidth(), 0, getHeight()/3, g);
            printSimpleString("PRESS P TO PLAY AGAIN", getWidth(), 0, getHeight()/3 * 2, g);

        }



    }
    
    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos);
    }

}




//Paint It
//Move It
//Collide It
//Gamify
//Prettify