package firstGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Timer;


public class CustomCanvas extends Canvas{
    public int x;
    public int y;
    public int virtualX = 150;
    public int virtualY = 150;
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
    Rectangle coin;
    Rectangle enemy;

    RNG rng = new RNG();
    int[] spawn = rng.generate_coin();

    //"C:\Users\asusx\source\IT173P\Java Canvas\firstGame\firstGame\character"
    public CustomCanvas(){
        for (int d = 0; d < directions.length; d++) {
            for (int f = 1; f <= 4; f++) {
                icon = new ImageIcon("\\character\\" + directions[d] + f + ".png");
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

        timer.schedule(task, 750, 100);
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
        super.paint(g);

        rect = new Rectangle( virtualX , virtualY,imgCurent.getWidth(this)+5, imgCurent.getHeight(this)+5);   
        g.setColor ( Color.RED );       
        g.fillRect ( rect.x,rect.y,rect.width,rect.height ); 
        g.drawImage(imgCurent, virtualX, virtualY, this); 

        coin = new Rectangle(spawn[0],spawn[1],25, 25);        
        g.setColor ( Color.YELLOW );        
        g.fillRect (coin.x,coin.y,coin.width,coin.height);
        g.drawRect(coin.x, coin.y, 25, 25);

        Collide();
    }

    public void spawn_something(){
        spawn = rng.generate_coin(); // change coordinates.
    }

    public void Collide(){      
        if (rect.intersects(coin)){                  
          System.out.println("wakokok nagbanggaan!!!");  
          /* virtualX= 0;
          virtualY= 0;  */
          /* icon = new ImageIcon("die.png");
          imgCurent = icon.getImage();     */
          spawn_something();
          System.out.println(Arrays.toString(spawn));
          repaint();
       } 
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


