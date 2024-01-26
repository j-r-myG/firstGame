package firstGame;

import java.awt.*;
import java.io.File;

import javax.swing.ImageIcon;

public class GetImages {
    Image[] idle_Images = new Image[1];
    Image[] up_Images = new Image[4];
    Image[] down_Images = new Image[4];
    Image[] left_Images = new Image[4];
    Image[] right_Images = new Image[4];
    int position;
    String direction;
    File up_folder = new File("images/up");
    File down_folder = new File("images/down");
    File left_folder = new File("images/left");
    File right_folder = new File("images/right");
    ImageIcon icon;
    public GetImages(int position, String direction){
        this.position = position;
        this.direction = direction;
    }

    private void store_images(){
        File[] list_up = up_folder.listFiles();
        File[] list_down = down_folder.listFiles();
        File[] list_left = left_folder.listFiles();
        File[] list_right = right_folder.listFiles();
        int index = 0;

        for (File file : list_up) {
            if (file.isFile()) {
                //System.out.println("Up Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                up_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
        for (File file : list_down) {
            if (file.isFile()) {
                //System.out.println("Down Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                down_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
        for (File file : list_left) {
            if (file.isFile()) {
                //System.out.println("Left Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                left_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
        for (File file : list_right) {
            if (file.isFile()) {
                //System.out.println("Right Image: " + file.getName());
                icon = new ImageIcon(file.getName());
                right_Images[index] = icon.getImage();
                index++;
            }
        }
        index = 0;
    }
}
