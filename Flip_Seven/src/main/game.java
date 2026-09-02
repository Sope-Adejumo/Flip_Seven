package main;

import java.util.*;

public class game {
    private ArrayList<Card> deck;
    private ArrayList<Player> players;
    private int roundCount = 0;
    
    private boolean gameInitialized = false;
    private boolean isPlaying = false;
    private String gameMode = "waiting_for_start";  
    private String lastCardDrawn = ""; 

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
    
    public String getGameMode() {
        return gameMode;
    }
    
    public String getLastCardDrawn() {
        return lastCardDrawn;
    }
    
    public int getRoundCount() {
        return roundCount;
    }
    
    public boolean isGamePlaying() {
        return isPlaying;
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

    public void init() {
        if (!gameInitialized) {
            gameInitialized = true;
            startNewRound();
        }
    }
    
    public void processInput(int key) {
        if (!gameInitialized) return;
        
        Player player1 = players.get(0);
        Player cpu = players.get(1);
        
        if (gameMode.equals("waiting_for_start")) {
            gameMode = "waiting_for_action";
            isPlaying = true;
        }
        else if (gameMode.equals("waiting_for_action")) {
            handleMainAction(key, player1, cpu);
        }
        else if (gameMode.equals("waiting_for_flip3_choice")) {
            handleFlip3Choice(key, player1, cpu);
        }
        else if (gameMode.equals("waiting_for_freeze_choice")) {
            handleFreezeChoice(key, player1, cpu);
        }
    }
    
    private void startNewRound() {
        roundCount++;
        Player player1 = players.get(0);
        Player cpu = players.get(1);
        
        dealCard(player1);
        dealCard(cpu);
        gameMode = "waiting_for_start";
        isPlaying = false;
    }
    
    private void handleMainAction(int key, Player player1, Player cpu) {
        if (key == 1) {
            dealCard(player1);
            
            String player1Hand = player1.getHand().toString();
            if (player1Hand.contains("flip_3")) {
                lastCardDrawn = "flip_3";
                gameMode = "waiting_for_flip3_choice";
                return;
            }
            if (player1Hand.contains("freeze")) {
                lastCardDrawn = "freeze";
                gameMode = "waiting_for_freeze_choice";
                return;
            }
            
            dealCard(cpu);
            
            checkRoundEnd(player1, cpu);
            gameMode = "waiting_for_action";
        } 
        else if (key == 2) {
            endRound(player1, cpu);
        }
    }
    
    private void handleFlip3Choice(int key, Player player1, Player cpu) {
        if (key == 1) {
            dealCard(cpu);
            dealCard(cpu);
            dealCard(cpu);
            player1.removeFromHand("flip_3");
        } 
        else if (key == 2) {
            dealCard(player1);
            dealCard(player1);
            dealCard(player1);
            player1.removeFromHand("flip_3");
        }
        
        dealCard(cpu);
        checkRoundEnd(player1, cpu);
        gameMode = "waiting_for_action";
    }
    
    private void handleFreezeChoice(int key, Player player1, Player cpu) {
        if (key == 1) {
            cpu.updateState("frozen");
            player1.removeFromHand("freeze");
        } 
        else if (key == 2) {
            player1.updateState("frozen");
            player1.removeFromHand("freeze");
        }
        
        dealCard(cpu);
        checkRoundEnd(player1, cpu);
        gameMode = "waiting_for_action";
    }
    
    private void checkRoundEnd(Player player1, Player cpu) {
        String player1State = player1.checkState();
        String cpuState = cpu.checkState();
        if (player1State.equals("busted") || cpuState.equals("busted")) {
            endRound(player1, cpu);
        }
    }
    
    private void endRound(Player player1, Player cpu) {
        String player1State = player1.checkState();
        String cpuState = cpu.checkState();
               
        isPlaying = false;
        gameMode = "waiting_for_start";
        
        player1.resetHand();
        cpu.resetHand();
        startNewRound();
    }

    
    public void dealCard(Player player) {
        if (!deck.isEmpty()) {
            Collections.shuffle(deck);
            Card card = deck.remove(0);
            player.addToHand(card);
        }
    }
}
