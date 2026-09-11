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
import java.io.File;

public class Panel extends JPanel implements MouseListener {
    int cnt;
    game game1;
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

    public Panel(int count) {
        this.cnt = Math.max(2, Math.min(count, 5));

        setBackground(Color.BLACK);
        game1 = new game(cnt);
        game1.init();
        addMouseListener(this);
        setFocusable(true);
        requestFocus();

        try {
            zero = ImageIO.read(new File("src/cards/0.png"));
            one = ImageIO.read(new File("src/cards/1.png"));
            two = ImageIO.read(new File("src/cards/2.png"));
            three = ImageIO.read(new File("src/cards/3.png"));
            four = ImageIO.read(new File("src/cards/4.png"));
            five = ImageIO.read(new File("src/cards/5.png"));
            six = ImageIO.read(new File("src/cards/6.png"));
            seven = ImageIO.read(new File("src/cards/7.png"));
            eight = ImageIO.read(new File("src/cards/8.png"));
            nine = ImageIO.read(new File("src/cards/9.png"));
            ten = ImageIO.read(new File("src/cards/10.png"));
            eleven = ImageIO.read(new File("src/cards/11.png"));
            twelve = ImageIO.read(new File("src/cards/12.png"));
            flip_3 = ImageIO.read(new File("src/cards/flip_3.png"));
            freeze = ImageIO.read(new File("src/cards/freeze.png"));
            plus_2 = ImageIO.read(new File("src/cards/plus_2.png"));
            plus_4 = ImageIO.read(new File("src/cards/plus_4.png"));
            plus_6 = ImageIO.read(new File("src/cards/plus_6.png"));
            plus_8 = ImageIO.read(new File("src/cards/plus_8.png"));
            plus_10 = ImageIO.read(new File("src/cards/plus_10.png"));
            second_chance = ImageIO.read(new File("src/cards/second_chance.png"));
            times_2 = ImageIO.read(new File("src/cards/times_2.png"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(255, 255, 0));
        g.drawString("WELCOME TO FLIP SEVEN!", 50, 50);

        int buttonY = getHeight() - 70;
        int hitX = getWidth() - 380;
        int standX = getWidth() - 190;

        g.setColor(Color.GREEN);
        g.fillRect(hitX, buttonY, 180, 60);
        g.setColor(Color.BLACK);
        g.drawString("HIT", hitX + 60, buttonY + 36);

        g.setColor(Color.CYAN);
        g.fillRect(standX, buttonY, 180, 60);
        g.setColor(Color.BLACK);
        g.drawString("STAND", standX + 55, buttonY + 36);

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

        int activePlayerIndex = game1.getCurrentPlayerIndex();
        int playerCount = gameState.size();
        int playerBoxWidth = Math.max(500, getWidth() - 80);
        int playerBoxHeight = Math.max(90, Math.min(150, (getHeight() - 200) / Math.max(playerCount, 1)));
        int xStart = 30;
        int yStart = 90;
        int verticalGap = Math.max(8, (getHeight() - yStart - playerCount * playerBoxHeight) / Math.max(playerCount - 1, 1));

        int buttonTop = getHeight() - 70;
        int buttonBottom = getHeight();
        int cardReservedBottom = buttonTop - 10;

        for (int p = 0; p < playerCount; p++) {
            Player player = gameState.get(p);
            player.checkState();
            int boxX = xStart;
            int boxY = yStart + p * (playerBoxHeight + verticalGap);
            boolean isActive = p == activePlayerIndex;

            g.setColor(new Color(35, 48, 62));
            g.fillRoundRect(boxX, boxY, playerBoxWidth, playerBoxHeight, 16, 16);
            g.setColor(Color.WHITE);
            g.drawRoundRect(boxX, boxY, playerBoxWidth, playerBoxHeight, 16, 16);

            if (isActive) {
                g.setColor(Color.YELLOW);
                g.drawString("CURRENT PLAYER: " + player.getName(), boxX + 10, boxY + 22);
                g.setColor(new Color(255, 178, 54));
                g.fillRoundRect(boxX + 10, boxY + playerBoxHeight - 34, 90, 20, 10, 10);
                g.setColor(Color.BLACK);
                g.drawString("ACTIVE", boxX + 36, boxY + playerBoxHeight - 19);
            } else {
                g.setColor(Color.WHITE);
                g.drawString("PLAYER: " + player.getName(), boxX + 10, boxY + 22);
            }

            g.setColor(Color.CYAN);
            g.drawString("TOTAL: " + player.checkTotal(), boxX + 220, boxY + 22);
            g.setColor(Color.WHITE);

            if (player.getState().equals("busted")) {
                g.setColor(Color.RED);
                g.drawString("BUSTED", boxX + playerBoxWidth - 80, boxY + 22);
                g.setColor(new Color(160, 0, 0, 170));
                g.fillRoundRect(boxX, boxY, playerBoxWidth, playerBoxHeight, 16, 16);
            }

            int cardWidth = Math.max(30, Math.min(84, playerBoxHeight / 2));
            int cardHeight = Math.max(46, Math.min(110, playerBoxHeight - 40));
            int cardXStart = boxX + 15;
            int cardY = boxY + 30;
            int spacing = Math.max(18, cardWidth + 8);

            int maxCardY = cardReservedBottom - (boxY + 30 + cardHeight);
            if (maxCardY < 0) {
                cardY = boxY + 30;
            }

            int handPixelsUsed = Math.max(0, player.getHand().size() * spacing);
            int handRightEdge = cardXStart + handPixelsUsed;
            if (handRightEdge > boxX + playerBoxWidth - 20) {
                spacing = Math.max(10, (playerBoxWidth - 40) / Math.max(player.getHand().size(), 1));
            }

            for (int i = 0; i < player.getHand().size(); i++) {
                String cardID = player.getHand().get(i).getCardID();
                BufferedImage img = numberWords.get(cardID);
                if (img != null) {
                    if (boxY + 30 + cardHeight > buttonTop) {
                        continue;
                    }
                    g.drawImage(img,
                            cardXStart + i * spacing,
                            cardY,
                            cardWidth,
                            cardHeight,
                            null);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Player current = game1.getCurrentPlayer();

        if (x >= getWidth() - 380 && x <= getWidth() - 200 && y >= getHeight() - 70 && y <= getHeight() - 10) {
            if (current != null && !current.getState().equals("busted")) {
                game1.dealCard(current);
                current.checkState();
                if (current.getState().equals("busted")) {
                    game1.advanceToNextPlayer();
                }
                repaint();
            }
        }

        if (x >= getWidth() - 190 && x <= getWidth() - 10 && y >= getHeight() - 70 && y <= getHeight() - 10) {
            game1.advanceToNextPlayer();
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
