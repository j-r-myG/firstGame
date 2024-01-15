package firstGame;

import java.util.Random;

public class RNG {

    public int[] generate_coord(){
        Random rand = new Random();
        int[] spawn = new int[3]; // for storing x and y coordinates.

        for(int i = 0; i < 2; i++){
            spawn[i] = rand.nextInt(250);
        }

        spawn[2] = rand.nextInt(10); // not sure...

        /* if(spawn[0] == 150 && spawn[1] == 150){
            generate_coin(); // must not spawn on top of character
        } */
        return spawn;
    }    
}
