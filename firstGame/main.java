package firstGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import java.util.Timer;

public class main extends JFrame{
    CustomCanvas DrawingArea;
    int x, y;
    
    public main(){
        Container Pane;
        Pane = getContentPane();
        Pane.setLayout(null);
        DrawingArea = new CustomCanvas();
        DrawingArea.setBounds(0, 0, 500, 500);

        Pane.add(DrawingArea);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(500, 500);
        show();
    }

    public static void main(String[] args){
        main app = new main();
    }
}