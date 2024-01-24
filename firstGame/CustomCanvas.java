package firstGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Timer;

public class CustomCanvas extends Canvas {
    public int x, y;
    public int virtualX = 150, virtualY = 150;
    public int pos = 8;
    Timer timer;
    TimerTask task;

    Image imgCurent;
    boolean moving = false;
    int gameAction, frame, index = 0;

    String[] directions = { "U", "D", "L", "R" };
    Image[] img = new Image[16]; // Store character images
    ImageIcon icon;
    Rectangle player, coin, enemy, speed_down;

    int enemy_width = 20, enemy_height = 20;
    int coin_width = 25, coin_height = 25;
    int level = 1;
    int player_speed = 8, enemy_speed = 2;

    RNG rng = new RNG();
    int[] spawn_coin = rng.generate_coord(); // generate random coordinate of coin.
    int[] spawn_down = rng.generate_coord();
    int[] spawn_enemy = rng.generate_coord();
    public int en_x = spawn_enemy[0], en_y = spawn_enemy[1];// for enemy

    public CustomCanvas() {
        for (int d = 0; d < directions.length; d++) {
            for (int f = 1; f <= 4; f++) {
                icon = new ImageIcon(directions[d] + f + ".png");
                img[index] = icon.getImage();
                index++;
            } // loop frame to create all images in array form
        }
        imgCurent = img[0]; // initial image of naruto upon running code

        timer = new Timer(true);
        task = new TimerTask() {
            public void run() {
                moveIt();
                
            }// end run
        };// end task

        timer.schedule(task, 750, 100);
        setBackground(Color.DARK_GRAY);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                gameAction = evt.getKeyCode();
                moving = true;
                //System.err.println(gameAction);
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                gameAction = 0;
                moving = false;
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int Height;
        Height = getHeight();
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Level: "+level, 10, Height / 2);
           
        player = new Rectangle(virtualX, virtualY, imgCurent.getWidth(this) + 5, imgCurent.getHeight(this) + 5);
        g.setColor(Color.RED);
        g.fillRect(player.x, player.y, player.width, player.height);
        g.drawImage(imgCurent, virtualX, virtualY, this);

        coin = new Rectangle(spawn_coin[0], spawn_coin[1], coin_width, coin_height);
        g.setColor(Color.YELLOW);
        g.fillRect(coin.x, coin.y, coin.width, coin.height);
        g.drawRect(coin.x, coin.y, 25, 25);

        speed_down = new Rectangle(spawn_down[0], spawn_down[1], enemy_width, enemy_height);
        g.setColor(Color.PINK);
        g.fillRect(speed_down.x, speed_down.y, speed_down.width, speed_down.height);
        g.drawRect(speed_down.x, speed_down.y, speed_down.width, speed_down.height);

        enemy = new Rectangle(en_x, en_y, enemy_width, enemy_height);
        g.setColor(Color.GREEN);
        g.fillRect(enemy.x, enemy.y, enemy_width, enemy_height);
        g.drawRect(enemy.x, enemy.y, enemy_width, enemy_height);

        Collide();
    }

    public void spawn() {
        spawn_coin = rng.generate_coord();
        spawn_down = rng.generate_coord();
        /* en_x = 50;
        en_y = 50; */
        System.out.println("Coin: " + Arrays.toString(spawn_coin));
    }


    public void change_down() {
        // change position of down buff.
        spawn_down = rng.generate_coord();
    }

    public void Collide() {
        if (player.intersects(coin)) {
            System.out.println("wakokok nagbanggaan!!!");
            /*
             * virtualX= 0;
             * virtualY= 0;
             */
            /*
             * icon = new ImageIcon("die.png");
             * imgCurent = icon.getImage();
             */
            spawn();
            level +=1;
            if(enemy_speed < 5){
                enemy_speed += 1;
            }
            if(level == 10){
                endgame("You have won!");
            }
            System.err.println("Enemy Speed: "+enemy_speed);
            repaint();
        }
        if (player.intersects(speed_down)) {
            if(enemy_speed != 1){
                // enemy should not reach speed 0.
                change_down();
                enemy_speed -= 1;
            }
            change_down();
            System.err.println("enemy speed -");
            repaint();
        }
        if (player.intersects(enemy)) {
            System.err.println("Lose!!");
            endgame("You have died!");
        }
    }

    public void moveIt() {
        Move move = new Move(virtualX, virtualY, player_speed, gameAction, moving, en_x, en_y, enemy_speed); // whoa oop stuff such programmer.
        virtualX = move.player_x;
        virtualY = move.player_y;
        en_x = move.enemy_x;
        en_y = move.enemy_y;
        repaint();
    }
    public void endgame(String msg){
        //this.setEnabled(false);
        System.exit(0);
        en_x = 50;
        en_y = 50;
        virtualX = 100;
        virtualY = 100;
        JOptionPane.showMessageDialog(this, msg,
                "INFORMATION",
                JOptionPane.INFORMATION_MESSAGE);        
    }
}
