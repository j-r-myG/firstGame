package firstGame;

import java.awt.event.KeyEvent;

public class Move{
    int player_x, player_y, player_speed; 
    int enemy_x, enemy_y, enemy_speed;
    int gameAction;
    boolean moving;
    int boundary = 400, acceptable = 25, offset = 10;
    public  Move(int player_x, int player_y, int player_speed, int gameAction, boolean moving, int enemy_x, int enemy_y, int enemy_speed){
        //int player_x, int player_y, int player_speed, int gameAction, boolean moving
        this.player_x = player_x;
        this.player_y =  player_y; 
        this.player_speed = player_speed; 
        this.gameAction = gameAction;
        this.moving = moving;
        this.enemy_x = enemy_x;
        this.enemy_y = enemy_y;
        this.enemy_speed = enemy_speed;
        move_player();
        move_enemy();
    }
    private void move_player(){
        if(moving){
            switch (gameAction) {
            case KeyEvent.VK_DOWN:
                this.player_y += 5;
                teleport();
                //move_enemy();
                break;
            case KeyEvent.VK_UP:
                this.player_y -= 5;
                teleport();
                //move_enemy();
                break;
            case KeyEvent.VK_LEFT:
                this.player_x -= 5;
                teleport();
                //move_enemy();
                break;
            case KeyEvent.VK_RIGHT:
                this.player_x += 5;
                teleport();
                //move_enemy();
                break;
            }
            //System.err.println(gameAction);
            // System.err.println("Player x: " + player_x);
            // System.err.println("Player y: " + player_y);
            // System.err.println("Enemy x: " + enemy_x);
            // System.err.println("Enemy y: " + enemy_y);
        }
        //System.err.println(gameAction);
        //return player_moved;
    }
    private void move_enemy() {
        //int[] enemy_moved = {enemy_x, enemy_y};
        if (enemy_y > player_y) {
            enemy_y -= enemy_speed;
        }
        if (enemy_y < player_y) {
            enemy_y += enemy_speed;
        }
        if (enemy_x > player_x) {
            enemy_x -= enemy_speed;
        }
        if (enemy_x < player_x) {
            enemy_x += enemy_speed;
        }
        //return enemy_moved;
    }

    private void teleport(){
        if(player_x < acceptable){
            player_x = boundary - acceptable;
        }
        if(player_x > boundary - acceptable){
            player_x = acceptable;
        }
        if(player_y < acceptable){
            player_y = boundary - acceptable;
        }
        if(player_y > boundary - acceptable){
            player_y = acceptable;
        }
    }
}
