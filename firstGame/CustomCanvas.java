package firstGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import java.util.Timer;


public class CustomCanvas extends Canvas{
    public int x;
    public int y;
    public int virtualX = 100;
    public int virtualY = 40;
    public int pos = 8;
    Timer timer;
    TimerTask task;

    Image imgCurent;
    boolean moving = false;
    int gameAction;
    int frame;
    int index = 0;

    String[] directions = {"U", "D", "L", "R"};
    Image[] img = new Image[16]; // Store character images
    ImageIcon icon;
    Rectangle rect;

    public CustomCanvas(){
        for (int d = 0; d < directions.length; d++) {
            for (int f = 1; f <= 4; f++) {
                icon = new ImageIcon("/character/" + directions[d] + f + ".png");
                img[index] = icon.getImage();
                index++;
            }//loop frame to create all images in array form
        }
        imgCurent = img[0]; //initial image of naruto upon running code

        timer = new Timer(true);
        task = new TimerTask() {
            public void run() {
                moveIt();

            }//end run
        };//end task 

        timer.schedule(task, 1000, 100);
        setBackground(Color.DARK_GRAY);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                gameAction = evt.getKeyCode();
                moving = true;
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                gameAction = 0;
                moving = false;
            }
        });

    }
    @Override
    public void paint(Graphics g){
        rect = new Rectangle( virtualX , virtualY,imgCurent.getWidth(this)+5, imgCurent.getHeight(this)+5);   
        g.setColor ( Color.RED );       
        g.fillRect ( rect.x,rect.y,rect.width,rect.height ); 
        g.drawImage(imgCurent, virtualX, virtualY, this); 
    }
    public void moveIt(){
        if (!moving){
            return;
        }

        switch (gameAction) {
            case KeyEvent.VK_DOWN:
                x = 1;
                  virtualY+=5;
                  frame = (frame+1) % 4;
                  imgCurent = img[frame + 4];
                  repaint();
                break;
            case KeyEvent.VK_UP:
                x = 2;
                  virtualY-=5;
                  frame = (frame+1) % 4;
                  imgCurent = img[frame + 0];
                  repaint();
                break;
            case KeyEvent.VK_LEFT:
                x = 3;
                //movementleft();
                  virtualX-=5;
                  frame = (frame+1) % 4;
                  imgCurent = img[frame + 8];
                  repaint();
                break;
            case KeyEvent.VK_RIGHT:
                x = 4;
              //  movementright();
                 virtualX+=5;
                 frame = (frame+1) % 4;
                 imgCurent = img[frame + 12];    
                 repaint();
                break;
        }
    }
}


