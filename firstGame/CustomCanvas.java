package firstGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Timer;

public class CustomCanvas extends Canvas {
    int boundary = 700;
    public int x, y;
    public int virtualX = boundary / 2, virtualY = boundary / 2;
    public int pos = 1, en_pos = 1;
    Timer timer;
    TimerTask task;

    Image imgCurrent, enemyImage, buffImage, coinImage, backgroundImage ;
    boolean moving = false;
    int gameAction, frame, index = 0;

    String[] directions = { "U", "D", "L", "R" };
    Image[] img = new Image[16]; // Store character images
    ImageIcon icon;
    Rectangle player, coin, enemy, speed_down;
    String direction;

    int enemy_width = 20, enemy_height = 20;
    int coin_width = 25, coin_height = 25;
    int level = 1;
    int player_speed = 10, enemy_speed = 5, en_max_speed = 9, max_level = 11;

    RNG rng = new RNG(); // for generating coordinates
    int[] spawn_coin = rng.generate_coord(); // generate random coordinate of coin.
    int[] spawn_down = rng.generate_coord(); // generate coordinate of buff.
    
    public int en_x = 10, en_y = 10;// spawn enemy only once.

    //GetImages getImages = new GetImages(position, direction);


    public CustomCanvas() {
        set_images();

        timer = new Timer(true);
        task = new TimerTask() {
            public void run() {
                moveIt();
                
            }// end run
        };// end task

        timer.schedule(task, 750, 100);
        setBackground(Color.GRAY);

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
                icon = new ImageIcon("firstGame/images/idle.png");
                imgCurrent = icon.getImage();
            }
        });

    }

    private void set_images(){
        icon = new ImageIcon("firstGame/images/idle.png");
        imgCurrent = icon.getImage(); // initial image of naruto upon running code
        icon = new ImageIcon("firstGame/images/enemy.png");
        enemyImage = icon.getImage();
        icon = new ImageIcon("firstGame/images/buff.png");
        buffImage = icon.getImage();
        icon = new ImageIcon("firstGame/images/coin.png");
        coinImage = icon.getImage();
        icon = new ImageIcon("firstGame/images/background.png");
        backgroundImage = icon.getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.drawImage(backgroundImage, 0, 0, this);
        
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Avoid the shuriken.",10,30);
        g.drawString("Collect ramen to advance level.",10,50);
        g.drawString("Collect rasengan to slow shuriken.",10,70);
        g.drawString("Level: "+level+"/10", 10, 90);
        g.drawString("Enemy Speed = "+enemy_speed, 10, 110);

        // paint player.
        player = new Rectangle(virtualX, virtualY, imgCurrent.getWidth(this), imgCurrent.getHeight(this));
        //g.setColor(Color.RED);
        //g.fillRect(player.x, player.y, player.width, player.height);
        g.drawImage(imgCurrent, virtualX, virtualY, this);

        // coin, and down (x,y) rely on rng object to spawn at random places.
        // paint coin.
        coin = new Rectangle(spawn_coin[0], spawn_coin[1], coinImage.getWidth(this), coinImage.getHeight(this));
        g.drawImage(coinImage, coin.x, coin.y, this);

        // paint down buff.
        speed_down = new Rectangle(spawn_down[0], spawn_down[1], buffImage.getWidth(this), buffImage.getHeight(this));
        g.drawImage(buffImage, speed_down.x, speed_down.y, this);

        // paint enemy
        enemy = new Rectangle(en_x, en_y, enemyImage.getWidth(this), enemyImage.getHeight(this));
        g.drawImage(enemyImage, enemy.x, enemy.y, this);

        Collide();
    }

    public void spawn() {
        // call rng object to generate new coordinates for coin and down buff.
        spawn_coin = rng.generate_coord();
        //spawn_down = rng.generate_coord();
        System.out.println("Coin: " + Arrays.toString(spawn_coin));
    }


    public void change_down() {
        // change position of down buff everytime player collects it.
        spawn_down = rng.generate_coord();
    }

    public void Collide() {
        if(coin.intersects(speed_down)){
            spawn(); // coin and buff should not spawn on each other.
        }
        if (player.intersects(coin)) {
            spawn(); // call spawn method for new coordinates.
            level +=1;
            if(enemy_speed < en_max_speed){
                // enemy's maximum speed must not be exceeded.
                enemy_speed += 2;
            }
            if(level == max_level){
                System.out.println("Win!!");
                endgame("You have won!");
            }
            System.out.println("+ Enemy Speed: "+enemy_speed);
            repaint();
        }
        if (player.intersects(speed_down)) {
            if(enemy_speed != 3){
                // enemy speed should not reach 0.
                change_down();
                enemy_speed -= 1;
            }
            change_down(); // call to change down buff position.
            System.out.println("- Enemy Speed: " +enemy_speed);
            repaint();
        }
        if (player.intersects(enemy)) {
            System.out.println("Lose!!");
            endgame("You have died!");
        }
    }

    public void moveIt() {
        Move move = new Move(virtualX, virtualY, player_speed, gameAction, moving, en_x, en_y, enemy_speed); // whoa oop stuff such programmer.
        // oop stuff to move player and enemy. 
        // DO THIS
        if(moving){
            if(pos > 4){
                pos = 1;
            }
            icon = new ImageIcon(move.gen_images("p",pos));
            imgCurrent = icon.getImage(); 
            pos++;
        }
        if(en_pos > 4){
            en_pos = 1;
        }
        icon = new ImageIcon(move.gen_images("e", en_pos)); // rotating enemy using an index.
        enemyImage = icon.getImage();
        icon = new ImageIcon(move.gen_images("x", en_pos)); // rotating buff using an ndex.
        buffImage = icon.getImage();
        en_pos++;

        virtualX = move.player_x;
        virtualY = move.player_y;
        en_x = move.enemy_x;
        en_y = move.enemy_y;
        repaint();
    }

    public void endgame(String msg){
        //this.setEnabled(false);
        en_x = boundary - 50;
        en_y = boundary / 2;
        // set speeds of enemy and player to 0 to disable them.
        enemy_speed = 0;
        player_speed = 0;
        virtualX = boundary - 100;
        virtualY = boundary / 2;
        JOptionPane.showMessageDialog(this, msg,
                "Game Has Ended.",
                JOptionPane.INFORMATION_MESSAGE);        
                System.exit(0);
    }
}
