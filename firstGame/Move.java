package firstGame;

import java.awt.event.KeyEvent;

public class Move{
    int player_x, player_y, player_speed; 
    int enemy_x, enemy_y, enemy_speed;
    int gameAction;
    boolean moving;
    int boundary = 700, acceptable = 25;
    //int pos;
    String direction, currImage;
    //GetImages getImages = new GetImages();

    public Move(int player_x, int player_y, int player_speed, int gameAction, boolean moving, int enemy_x, int enemy_y, int enemy_speed){
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

    public String gen_images(String im, int pos){
        pos++; // increment pos.
        if(pos > 4){
            pos = 1; // reset position 
        }
        if(im == "p"){
            currImage = "firstGame/images/"+direction+pos+".png"; // locate image for player.
            //System.out.println(currImage);
        }
        else if (im == "e"){
            currImage = "firstGame/images/enemy"+pos+".png"; // locate image for enemy.
        }
        else{
            currImage = "firstGame/images/buff"+pos+".png"; // locate image for buff.
        }

        return currImage;
    }
    private void move_player(){
        if(moving){
            switch (gameAction) {
            // move player a number of frames to the direction of keys.
            case KeyEvent.VK_DOWN:
                direction = "D";
                this.player_y += player_speed;
                teleport();
                break;
            case KeyEvent.VK_UP:
                direction = "U";
                this.player_y -= player_speed;
                teleport();
                break;
            case KeyEvent.VK_LEFT:
                direction = "L";
                this.player_x -= player_speed;
                teleport();
                break;
            case KeyEvent.VK_RIGHT:
                direction = "R";
                this.player_x += player_speed;
                teleport();
                break;
            }
            
        }
       
    }
    private void move_enemy() {
        //int[] enemy_moved = {enemy_x, enemy_y};
        // move enemy based towards where the player is.
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
        // teleport player to the other side of the frame.
        if(player_x < acceptable-20){
            player_x = boundary - (acceptable + 25); // teleport to right side.
        }
        if(player_x > boundary - (acceptable+20)){
            player_x = acceptable; // teleport to left side.
        }
        if(player_y < acceptable - 20){
            player_y = boundary - (acceptable + 65); // teleport to top side.
        }
        if(player_y > boundary - (acceptable + 65)){ 
            player_y = acceptable; // teleport to bottom side.
        }
    }
}
