package firstGame;

import java.awt.*;
import java.io.File;
import javax.swing.ImageIcon;

public class GetImages {
    // store images
    // Image[] idle_Images = new Image[1];
    // Image[] up_Images = new Image[4];
    // Image[] down_Images = new Image[4];
    // Image[] left_Images = new Image[4];
    // Image[] right_Images = new Image[4];
    // // locate files. 
    // File idle_folder = new File("firstGame/images/idle"); 
    // File up_folder = new File("firstGame/images/up"); 
    // File down_folder = new File("firstGame/images/down");
    // File left_folder = new File("firstGame/images/left");
    // File right_folder = new File("firstGame/images/right");
    //ImageIcon icon;
    //int index = 0;

    String currImage, direction;
    
    int position = 0;

    String[] up_images = new String[4];
    String[] down_images = new String[4];
    String[] left_images = new String[4];
    String[] right_images = new String[4];

    public GetImages(){
        // this.direction = direction;
        // //this.currImage = currImage;
        // store_images();
        // gen_images(this.position, this.direction);
        store_images();
    }

    private void store_images(){
        up_images[0] = "firstGame/images/U1.png";
        up_images[1] = "firstGame/images/U2.png";
        up_images[2] = "firstGame/images/U3.png";
        up_images[3] = "firstGame/images/U4.png";

        down_images[0] = "firstGame/images/D1.png";
        down_images[1] = "firstGame/images/D2.png";
        down_images[2] = "firstGame/images/D3.png";
        down_images[3] = "firstGame/images/D4.png";

        left_images[0] = "firstGame/images/L1.png";
        left_images[1] = "firstGame/images/L2.png";
        left_images[2] = "firstGame/images/L3.png";
        left_images[3] = "firstGame/images/L4.png";

        right_images[0] = "firstGame/images/R1.png";
        right_images[1] = "firstGame/images/R2.png";
        right_images[2] = "firstGame/images/R3.png";
        right_images[3] = "firstGame/images/R4.png";
    }
    public String gen_images(String direction){
        if (position > 4){
            position = 0;
        }
        switch (direction) {
            case "U":
                currImage = up_images[position];
                position++;
                break;
            case "D":
                currImage = down_images[position];
                position++;
                break;
            
            case "L":
                currImage = left_images[position];
                position++;
                break;
            
            case "R":
                currImage = right_images[position];
                position++;
                break;
            
            default:
                break;
        }
        return currImage;
    }
    // public Image get_idle_image(){
    //     File[] list_idle = idle_folder.listFiles();
    //     for (File file : list_idle) {
    //         if (file.isFile()) {
    //             //System.out.println("Up Image: " + file.getName());
    //             icon = new ImageIcon(file.getName());
    //             idle_Images[index] = icon.getImage();
    //             index++;
    //         }
    //     }
    //     index = 0;
    //     return idle_Images[0];
    // }
    
    // private void store_images(){
    //     // access files in file path.
    //     File[] list_up = up_folder.listFiles();
    //     File[] list_down = down_folder.listFiles();
    //     File[] list_left = left_folder.listFiles();
    //     File[] list_right = right_folder.listFiles();

    //     for (File file : list_up) {
    //         if (file.isFile()) {
    //             System.out.println("Up Image: " + file.getName());
    //             icon = new ImageIcon(file.getName()); // icon stuff because idk.
    //             up_Images[index] = icon.getImage(); // store images from file path.
    //             index++;
    //         }
    //     }
    //     index = 0;
    //     for (File file : list_down) {
    //         if (file.isFile()) { // null pointer error here
    //             System.out.println("Down Image: " + file.getName());
    //             icon = new ImageIcon(file.getName());
    //             down_Images[index] = icon.getImage();
    //             index++;
    //         }
    //     }
    //     index = 0;
    //     for (File file : list_left) {
    //         if (file.isFile()) {
    //             System.out.println("Left Image: " + file.getName());
    //             icon = new ImageIcon(file.getName());
    //             left_Images[index] = icon.getImage();
    //             index++;
    //         }
    //     }
    //     index = 0;
    //     for (File file : list_right) {
    //         if (file.isFile()) {
    //             System.out.println("Right Image: " + file.getName());
    //             icon = new ImageIcon(file.getName());
    //             right_Images[index] = icon.getImage();
    //             index++;
    //         }
    //     }
    //     index = 0;
    // }
}
