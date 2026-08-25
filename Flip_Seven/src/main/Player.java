package main;

import java.util.*;

public class Player {
    private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private String state = "active";

    public Player(String name) {
        this.name = name;
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
}
