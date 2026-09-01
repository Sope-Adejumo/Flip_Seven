package main;

import java.util.*;

public class game {
    private ArrayList<Card> deck;
    private ArrayList<Player> players;
    private int roundCount = 0;

    public game() {
        players = new ArrayList<Player>();
        Player player1 = new Player("Player 1");
        players.add(player1);
        Player cpu = new Player("CPU");
        players.add(cpu);

        resetDeck();
    }

    public ArrayList<Player> getState(){
        return players;
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
        deck.add(new Card("flip_3"));
        deck.add(new Card("flip_3"));
        deck.add(new Card("freeze"));
        deck.add(new Card("freeze"));
        deck.add(new Card("freeze"));
        deck.add(new Card("plus_2"));
        deck.add(new Card("plus_4"));
        deck.add(new Card("plus_6"));
        deck.add(new Card("plus_8"));
        deck.add(new Card("plus_10"));
        deck.add(new Card("second_chance"));
        deck.add(new Card("second_chance"));
        deck.add(new Card("second_chance"));
        deck.add(new Card("times_2"));
    }

    public void startGame() {
        Player player1 = players.get(0);
        Player cpu = players.get(1);

        Scanner kb = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            playAgain = startRound(player1, cpu);
            player1.resetHand();
            cpu.resetHand();
        }
    }

    private boolean startRound(Player player1, Player cpu) {
        roundCount++;
        dealCard(player1);
        dealCard(cpu);
        boolean isPlaying = true;

        while (isPlaying) {
            String player1State = player1.checkState();
            String cpuState = cpu.checkState();
            if (player1State.equals("busted") || cpuState.equals("busted")) {
                isPlaying = false;
            } else if (player1State.equals("frozen")) {
                // System.out.println("You are frozen and cannot draw a card this round.");
                dealCard(cpu);
                // System.out.println("CPU's hand: " + cpu.getHand().toString());
            } else {
                // System.out.println("Press 1 to draw a card. Press 2 to quit this round.");
                // String input = kb.nextLine();
                // if (input.equals("1")) {
                    dealCard(player1);
                    // System.out.println("Your hand: " + player1.getHand().toString().toString());
                    String player1Hand = player1.getHand().toString();
                    if (player1Hand.contains("flip_3")){
                        // System.out.println("You drew a flip_3 card! Who would you like to use it on? (1) CPU or (2) Yourself?");
                        // String flip3Input = kb.nextLine();
                        // if (flip3Input.equals("1")) {
                        //     dealCard(cpu);
                        //     dealCard(cpu);
                        //     dealCard(cpu);
                        //     // System.out.println("You used the flip_3 card on the CPU! The CPU has drawn 3 cards.");
                        //     cpu.removeFromHand("flip_3");
                        // } else if (flip3Input.equals("2")) {
                        //     dealCard(player1);
                        //     dealCard(player1);
                        //     dealCard(player1);
                        //     // System.out.println("You used the flip_3 card on yourself! You have drawn 3 cards.");
                        //     player1.removeFromHand("flip_3");
                        // }
                    }
                    if (player1Hand.contains("freeze")){
                        // System.out.println("You drew a freeze card! Who would you like to use it on? (1) CPU or (2) Yourself?");
                        // String freezeInput = kb.nextLine();
                        // if (freezeInput.equals("1")) {
                        //     cpu.updateState("frozen");
                        //     // System.out.println("You used the freeze card on the CPU! The CPU is frozen.");
                        //     player1.removeFromHand("freeze");
                        // } else if (freezeInput.equals("2")) {
                        //     player1.updateState("frozen");
                        //     // System.out.println("You used the freeze card on yourself! You are frozen.");
                        //     player1.removeFromHand("freeze");
                        // }
                    }
                    dealCard(cpu);
                    // System.out.println("CPU's hand: " + cpu.getHand().toString());
                // } else if (input.equals("2")) {
                //     isPlaying = false;
                // }
                if (player1State.equals("busted") || cpuState.equals("busted")) {
                    isPlaying = false;
                }
            }
        }
        // System.out.println("The round is over!");
        // System.out.println("Your hand: " + player1.getHand().toString());
        // System.out.println("Your total: " + player1.checkTotal());
        // System.out.println("CPU's hand: " + cpu.getHand().toString());
        // System.out.println("CPU's total: " + cpu.checkTotal());
        // System.out.println("Are you ready to play again? (y/n)");
        // String input = kb.nextLine();
        return true; 
    }

    
    public void dealCard(Player player) {
        if (!deck.isEmpty()) {
            Collections.shuffle(deck);
            Card card = deck.remove(0);
            player.addToHand(card);
        }
    }
}
