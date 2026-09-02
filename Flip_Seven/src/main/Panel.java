package main;

import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;

public class Panel extends JPanel implements MouseListener{
    game game1 = new game();
    BufferedImage zero;
    BufferedImage one;
    BufferedImage two;
    BufferedImage three;
    BufferedImage four;
    BufferedImage five;
    BufferedImage six;
    BufferedImage seven;
    BufferedImage eight;
    BufferedImage nine;
    BufferedImage ten;
    BufferedImage eleven;
    BufferedImage twelve;

    public Panel() {
        setBackground(Color.BLACK);
        game1.init();
        addMouseListener(this);
        setFocusable(true);
        requestFocus();

        try {
            zero = ImageIO.read(new File("Flip_Seven\\src\\cards\\0.png"));
            one = ImageIO.read(new File("Flip_Seven\\src\\cards\\1.png"));
            two = ImageIO.read(new File("Flip_Seven\\src\\cards\\2.png"));
            three = ImageIO.read(new File("Flip_Seven\\src\\cards\\3.png"));
            four = ImageIO.read(new File("Flip_Seven\\src\\cards\\4.png"));
            five = ImageIO.read(new File("Flip_Seven\\src\\cards\\5.png"));
            six = ImageIO.read(new File("Flip_Seven\\src\\cards\\6.png"));
            seven = ImageIO.read(new File("Flip_Seven\\src\\cards\\7.png"));
            eight = ImageIO.read(new File("Flip_Seven\\src\\cards\\8.png"));
            nine = ImageIO.read(new File("Flip_Seven\\src\\cards\\9.png"));
            ten = ImageIO.read(new File("Flip_Seven\\src\\cards\\10.png"));
            eleven = ImageIO.read(new File("Flip_Seven\\src\\cards\\11.png"));
            twelve = ImageIO.read(new File("Flip_Seven\\src\\cards\\12.png"));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(255, 255, 0));
        g.drawString("WELCOME TO FLIP SEVEN!", 10, 10);
        // g.drawString("Press 1 to draw, 2 to quit round. " + "(Round " + game1.getRoundCount() + ")", 10, 30);

        g.setColor(Color.GREEN);
        g.fillRect(getWidth()-380, getHeight()-70, 180, 60);
        g.setColor(Color.BLUE);
        g.fillRect(getWidth()-190, getHeight()-70, 180, 60);
        ArrayList<Player> gameState = game1.getState();
        Map<String, BufferedImage> numberWords = new HashMap<>();

        numberWords.put("0", zero);
        numberWords.put("1", one);
        numberWords.put("2", two);
        numberWords.put("3", three);
        numberWords.put("4", four);
        numberWords.put("5", five);
        numberWords.put("6", six);
        numberWords.put("7", seven);
        numberWords.put("8", eight);
        numberWords.put("9", nine);
        numberWords.put("10", ten);
        numberWords.put("11", eleven);
        numberWords.put("12", twelve);
        
        for (int i = 0; i < gameState.get(0).getHand().size(); i++) {
            g.drawImage(numberWords.get(gameState.get(0).getHand().get(i).getCardID()), (getWidth() / (gameState.get(0).getHand().size()+1) * (i+1)) - 60,
                    getHeight() / 2, 120, 180, null);
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= getWidth() - 360 && x <= getWidth() - 180 &&
        y >= getHeight() - 360 && y <= getHeight() - 300) {
        game1.dealCard(game1.getState().get(0));
        repaint();
    }

    if (x >= getWidth() - 180 && x <= getWidth() &&
        y >= getHeight() - 180 && y <= getHeight() - 120) {
        repaint();
    }
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
