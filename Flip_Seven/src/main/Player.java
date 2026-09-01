package main;

import java.util.*;

public class Player {
    private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private String state = "active";
    private int total = 0;

    public Player(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public void addToHand(Card card) {
        hand.add(card);
    }
    public void removeFromHand(String cardID) {
        for (Card card : hand) {
            if (card.getCardID().equals(cardID)) {
                hand.remove(card);
                break;
            }
        }
    }

    public void updateState(String newState) {
        state = newState;
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
        for (Card card : hand) {
            String cardID = card.getCardID();

            // To-do: make the logic for the special cards work better, and change to Flip-Seven's actual rules for calculating the total.
            if (cardID.equals("flip_3") || cardID.equals("freeze") || cardID.equals("second_chance")) {
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

    public void resetHand() {
        hand.clear();
        state = "active";
    }
}
