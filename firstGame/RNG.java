package firstGame;

import java.util.Random;

public class RNG {
    int boundary = 400, min = 50, max = boundary-50;

    public int[] generate_coord(){
        Random rand = new Random();
        int[] spawn = new int[3]; // for storing x and y coordinates.

        for(int i = 0; i < 2; i++){
            //spawn[i] = rand.nextInt(10, boundary - 10); // coordinate should not be outside the frame.
            spawn[i] = (int)(Math.random() * (max - min)) + min; // this is so stupid lol.
        }

        spawn[2] = rand.nextInt(10); // not sure...

        if(spawn[0] == boundary / 2 && spawn[1] == boundary / 2){
            generate_coord(); // must not spawn on top of character at the start.
        } 
        return spawn; // return randomly generated coordinates.
    }    
}
