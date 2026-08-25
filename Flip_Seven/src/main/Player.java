package main;

import java.util.*;

public class Player {
    private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private String state = "active";

    public Player(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getHand() {
        return hand.toString();
    }
    public void addToHand(Card card) {
        hand.add(card);
    }

    public String checkState() {
        Set<String> uniqueCards = new HashSet<>();
        for (Card card : hand) {
            uniqueCards.add(card.getCardID());
        }
        if (uniqueCards.size() != hand.size()) {
            state = "busted";
        }
        if (uniqueCards.contains("freeze")) {
            state = "frozen";
        }
        return state;
    }

    public int checkTotal() {
        int total = 0;
        for (Card card : hand) {
            String cardID = card.getCardID();
            if (cardID.equals("flip_3") || cardID.equals("flip_7") || cardID.equals("freeze") || cardID.equals("second_chance")) {
                continue;
            } else if (cardID.equals("plus_2")) {
                total += 2;
            } else if (cardID.equals("plus_4")) {
                total += 4;
            } else if (cardID.equals("plus_6")) {
                total += 6;
            } else if (cardID.equals("plus_8")) {
                total += 8;
            } else if (cardID.equals("plus_10")) {
                total += 10;
            } else if (cardID.equals("times_2")) {
                total *= 2;
            } else {
                total += Integer.parseInt(cardID);
            }
        }
        return total;
    }
}
