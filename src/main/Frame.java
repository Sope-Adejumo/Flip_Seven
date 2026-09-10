package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import javax.swing.JFrame;

public class Frame extends JFrame implements MouseListener{
    private int playerCount;
    public void setCount(int n){
        playerCount = n;
    }
    public int getCount(){
        return playerCount;
    }
    public Frame(String framename, JPanel panel){
        super(framename);
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
