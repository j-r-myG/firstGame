package firstGame;

import java.awt.*;
import java.io.File;
import javax.swing.ImageIcon;

public class GetImages {
    int position;
    String direction;
    // store images
    Image[] idle_Images = new Image[1];
    Image[] up_Images = new Image[4];
    Image[] down_Images = new Image[4];
    Image[] left_Images = new Image[4];
    Image[] right_Images = new Image[4];
    // locate files. 
    File idle_folder = new File("firstGame/images/idle"); 
    File up_folder = new File("firstGame/images/up"); 
    File down_folder = new File("firstGame/images/down");
    File left_folder = new File("firstGame/images/left");
    File right_folder = new File("firstGame/images/right");

    ImageIcon icon;
    int index = 0;
    
    public GetImages(int position, String direction){
        this.position = position;
        this.direction = direction;
        store_images();
    }

    public Image get_idle_image(){
        File[] list_idle = idle_folder.listFiles();
        for (File file : list_idle) {
            if (file.isFile()) {
                //System.out.println("Up Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                idle_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
        return idle_Images[0];
    }
    
    private void store_images(){
        // access files in file path.
        File[] list_up = up_folder.listFiles();
        File[] list_down = down_folder.listFiles();
        File[] list_left = left_folder.listFiles();
        File[] list_right = right_folder.listFiles();

        for (File file : list_up) {
            if (file.isFile()) {
                System.out.println("Up Image: " + file.getName());
                icon = new ImageIcon(file.getName()); // icon stuff because idk.
                up_Images[index] = icon.getImage(); // store images from file path.
                index++;
            }
        }
        index = 0;
        for (File file : list_down) {
            if (file.isFile()) { // null pointer error here
                System.out.println("Down Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                down_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
        for (File file : list_left) {
            if (file.isFile()) {
                System.out.println("Left Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                left_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
        for (File file : list_right) {
            if (file.isFile()) {
                System.out.println("Right Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                right_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
    }
}
