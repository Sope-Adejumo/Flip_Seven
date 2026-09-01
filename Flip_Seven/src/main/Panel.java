package main;

import java.util.*;

import javax.imageio.ImageIO;
import javax.imageio.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;

public class Panel extends JPanel {
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
            game1.startGame();
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
                g.drawImage(numberWords.get(gameState.get(0).getHand().get(i).getCardID()), getWidth() / 2,
                        getHeight() / 2, null);
            }
        

    }
}
