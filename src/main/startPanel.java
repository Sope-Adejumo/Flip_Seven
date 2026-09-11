package main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class startPanel extends JPanel implements KeyListener {
    public startPanel() {
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
    }

    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(255, 255, 0));
        g.drawString("SELECT A NUMBER (2-5) for the number of players!", 50, 50);
    }

    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (key < '2' || key > '5') {
            return;
        }

        int x = key - '0';
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof Frame) {
            Frame frame = (Frame) window;
            frame.setCount(x);
            frame.setContentPane(new Panel(x));
            frame.revalidate();
            frame.repaint();
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
