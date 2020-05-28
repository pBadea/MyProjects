package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowKeysListenerFrame extends JFrame implements KeyListener {

    ArrowKeysListenerFrame(String title){
        super(title);
        this.addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            System.out.println("LEFT PRESSED");
        }
        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("RIGHT PRESSED");
        }
        if (key == KeyEvent.VK_UP) {
            System.out.println("UP PRESSED");
        }
        if (key == KeyEvent.VK_DOWN) {
            System.out.println("DOWN PRESSED");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        ArrowKeysListenerFrame klt = new ArrowKeysListenerFrame("Titlu sugestiv");
        klt.pack();
        klt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        klt.setSize(600, 600);
        klt.setBackground(Color.RED);
        klt.setVisible(true);

    }
}
