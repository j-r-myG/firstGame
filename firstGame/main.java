package firstGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class main extends JFrame{
    CustomCanvas DrawingArea;
    int x, y;
    int boundary = 700;
    public main(){
        int width = boundary, height = boundary;
        Container Pane;
        Pane = getContentPane();
        Pane.setLayout(null);
        DrawingArea = new CustomCanvas();
        DrawingArea.setBounds(0, 0, width, height);

        Pane.add(DrawingArea);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(width, height);
        show();
    }

    public static void main(String[] args){
        main app = new main();
    }
}