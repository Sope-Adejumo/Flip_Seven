package main;

import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;

public class Panel extends JPanel implements MouseListener {
    int cnt;
    Window window = SwingUtilities.getWindowAncestor(this);
    if(window instanceof Frame)
    {
        cnt = ((Frame) window).getCount();
    }
    game game1 = new game(cnt);
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
    BufferedImage flip_3;
    BufferedImage freeze;
    BufferedImage plus_2;
    BufferedImage plus_4;
    BufferedImage plus_6;
    BufferedImage plus_8;
    BufferedImage plus_10;
    BufferedImage second_chance;
    BufferedImage times_2;

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
            flip_3 = ImageIO.read(new File("Flip_Seven\\src\\cards\\flip_3.png"));
            freeze = ImageIO.read(new File("Flip_Seven\\src\\cards\\freeze.png"));
            plus_2 = ImageIO.read(new File("Flip_Seven\\src\\cards\\plus_2.png"));
            plus_4 = ImageIO.read(new File("Flip_Seven\\src\\cards\\plus_4.png"));
            plus_6 = ImageIO.read(new File("Flip_Seven\\src\\cards\\plus_6.png"));
            plus_8 = ImageIO.read(new File("Flip_Seven\\src\\cards\\plus_8.png"));
            plus_10 = ImageIO.read(new File("Flip_Seven\\src\\cards\\plus_10.png"));
            second_chance = ImageIO.read(new File("Flip_Seven\\src\\cards\\second_chance.png"));
            times_2 = ImageIO.read(new File("Flip_Seven\\src\\cards\\times_2.png"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(255, 255, 0));
        g.drawString("WELCOME TO FLIP SEVEN!", 50, 50);

        g.setColor(Color.GREEN);
        g.fillRect(getWidth() - 380, getHeight() - 70, 180, 60);
        g.setColor(Color.BLACK);
        g.drawString("HIT", getWidth() - 350, getHeight() - 40);
        g.setColor(Color.CYAN);
        g.fillRect(getWidth() - 190, getHeight() - 70, 180, 60);
        g.setColor(Color.BLACK);
        g.drawString("STAND", getWidth() - 150, getHeight() - 40);
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
        numberWords.put("flip_3", flip_3);
        numberWords.put("freeze", freeze);
        numberWords.put("second_chance", second_chance);
        numberWords.put("plus_2", plus_2);
        numberWords.put("times_2", times_2);
        numberWords.put("plus_4", plus_4);
        numberWords.put("plus_6", plus_6);
        numberWords.put("plus_8", plus_8);
        numberWords.put("plus_10", plus_10);

        for (int i = 0; i < gameState.get(0).getHand().size(); i++) {
            g.drawImage(numberWords.get(gameState.get(0).getHand().get(i).getCardID()),
                    (getWidth() / (gameState.get(0).getHand().size() + 1) * (i + 1)) - 120,
                    getHeight() / 2, 240, 360, null);
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= getWidth() - 380 && x <= getWidth() - 200 && y >= getHeight() - 70 && y <= getHeight() - 10) {
            game1.dealCard(game1.getState().get(0));
            repaint();
        }

        if (x >= getWidth() - 190 && x <= getWidth() - 10 && y >= getHeight() - 70 && y <= getHeight() - 10) {
            repaint();
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
