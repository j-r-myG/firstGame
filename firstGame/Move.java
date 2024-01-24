package firstGame;

import java.awt.event.KeyEvent;


public class Move{
    int player_x;
    int player_y; 
    int player_speed; 
    int gameAction;
    boolean moving;
    int enemy_x; 
    int enemy_y; 
    int enemy_speed;
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
    }
    public void move_player(){
        // return player and enemy coord.
        // int[] player_moved = {0};
        // int[] enemy_moved = new int[2];
        if(moving){
            //this.player_x += 10;
            switch (gameAction) {
            case KeyEvent.VK_DOWN:
                this.player_y += 5;
                teleport();
                move_enemy();
                break;
            case KeyEvent.VK_UP:
                this.player_y -= 5;
                teleport();
                move_enemy();
                break;
            case KeyEvent.VK_LEFT:
                // movementleft();
                this.player_x -= 5;
                teleport();
                move_enemy();
                break;
            case KeyEvent.VK_RIGHT:
                // movementright();
                this.player_x += 5;
                teleport();
                move_enemy();
                break;
            }
            System.err.println(gameAction);
            System.err.println("Player x: " + player_x);
            System.err.println("Player y: " + player_y);
            System.err.println("Enemy x: " + enemy_x);
            System.err.println("Enemy y: " + enemy_y);
        }
        //System.err.println(gameAction);
        //return player_moved;
    }
    private int[] move_enemy() {
        int[] enemy_moved = {enemy_x, enemy_y};
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
        return enemy_moved;
    }
    private void teleport(){
        if(player_x < 10){
            player_x = 290;
        }
        if(player_x > 290){
            player_x = 10;
        }
        if(player_y < 10){
            player_y = 290;
        }
        if(player_y > 290){
            player_y = 10;
        }
    }
}
