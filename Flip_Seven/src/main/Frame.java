package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Frame extends JFrame implements MouseListener{
    public Frame(String framename){
        super(framename);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        add(panel);
        setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
    }

    public void mousePressed(MouseEvent e){};
    public void mouseEntered(MouseEvent e){};
    public void mouseReleased(MouseEvent e){};
    public void mouseClicked(MouseEvent e){};
    public void mouseExited(MouseEvent e){};
}
