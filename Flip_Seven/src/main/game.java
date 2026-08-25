package main;

import java.util.*;

public class game {
    private ArrayList<Card> deck;
    private ArrayList<Player> players;

    public game() {
        players = new ArrayList<Player>();
        Player player1 = new Player("Player 1");
        players.add(player1);
        Player cpu = new Player("CPU");
        players.add(cpu);
        players.add(player1);
        players.add(cpu);

        resetDeck();
    }

    private void resetDeck() {
        deck = new ArrayList<Card>();
        deck.add(new Card("0"));
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= i; j++) {
                deck.add(new Card(Integer.toString(i)));
            }
        }
        deck.add(new Card("flip_3"));
        deck.add(new Card("flip_7"));
        deck.add(new Card("freeze"));
        deck.add(new Card("plus_2"));
        deck.add(new Card("plus_4"));
        deck.add(new Card("plus_6"));
        deck.add(new Card("plus_8"));
        deck.add(new Card("plus_10"));
        deck.add(new Card("second_chance"));
        deck.add(new Card("times_2"));
    }

    public void startGame() {
        Player player1 = players.get(0);
        Player cpu = players.get(1);
        System.out.println("Welcome to Flip Seven!\n You are Player 1.");

        Scanner kb = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            playAgain = startRound(kb, player1, cpu);
        }
    }

    private boolean startRound(Scanner kb, Player player1, Player cpu) {
        dealCard(player1);
        dealCard(cpu);
        System.out.println("Your hand: " + player1.getHand());
        System.out.println("CPU's hand: " + cpu.getHand());
        boolean isPlaying = true;

        while (isPlaying) {
            String player1State = player1.checkState();
            String cpuState = cpu.checkState();
            if (player1State.equals("busted") || cpuState.equals("busted")) {
                isPlaying = false;
            } else if (player1State.equals("frozen")) {
                System.out.println("You are frozen and cannot draw a card this round.");
                dealCard(cpu);
                System.out.println("CPU's hand: " + cpu.getHand());
            } else {
                System.out.println("Press 1 to draw a card. Press 2 to quit this round.");
                String input = kb.nextLine();
                if (input.equals("1")) {
                    dealCard(player1);
                    System.out.println("Your hand: " + player1.getHand());
                    dealCard(cpu);
                    System.out.println("CPU's hand: " + cpu.getHand());
                } else if (input.equals("2")) {
                    isPlaying = false;
                }
                if (player1State.equals("busted") || cpuState.equals("busted")) {
                    isPlaying = false;
                }
            }
        }
        System.out.println("The round is over!");
        System.out.println("Your hand: " + player1.getHand());
        System.out.println("Your total: " + player1.checkTotal());
        System.out.println("CPU's hand: " + cpu.getHand());
        System.out.println("CPU's total: " + cpu.checkTotal());
        System.out.println("Are you ready to play again? (y/n)");
        String input = kb.nextLine();
        return input.equalsIgnoreCase("y");
    }

    
    public void dealCard(Player player) {
        if (!deck.isEmpty()) {
            Collections.shuffle(deck);
            Card card = deck.remove(0);
            player.addToHand(card);
        }
    }
}
