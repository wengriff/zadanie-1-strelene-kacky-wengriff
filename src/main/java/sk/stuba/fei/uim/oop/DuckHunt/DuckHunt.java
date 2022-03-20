package sk.stuba.fei.uim.oop.DuckHunt;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Deck.Deck;
import sk.stuba.fei.uim.oop.Player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class DuckHunt {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final Player[] players;
    private int numberOfPlayers;
    private int round;
    private int activePlayerCounter;
    private Board board;
    private Deck deck;
    
    public DuckHunt() {
        this.board = new Board();
        this.deck = new Deck();
        System.out.println("\n<---- DUCK HUNT GAME! ---->\n");
        int num = ZKlavesnice.readInt("\nSet the number of players: ");
        while(num < 2 || num > 6) {
            System.out.println(ANSI_RED + "\nInvalid number of players! Try Again!" + ANSI_RESET);
            num = ZKlavesnice.readInt("\nSet the number of players: ");
        }
        this.setNumberOfPlayers(num);
        this.players = new Player[this.getNumberOfPlayers()];
        for(int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player(this.setPlayerName(i));
        }
        this.board.createBoard(this.players);
        this.deck.createDeck();
        this.playGame();
    }

    private void setNumberOfPlayers(int numberOfPlayers) { this.numberOfPlayers = numberOfPlayers; }

    private int getNumberOfPlayers() { return this.numberOfPlayers; }

    private int getNumberOfAlivePlayers() {
        int numberOfAlivePlayers = 0; 
        for(Player player : this.players) {
            if(player.isAlive()) {
                numberOfAlivePlayers++;
            }
        }
        return numberOfAlivePlayers; // 1
    }

    private String setPlayerName(int i) {
        String playerName = ZKlavesnice.readString("Set Player " + (i + 1) + "'s name: ");
        return playerName;
    }

    private void playGame() {
        System.out.println("\n<---- HUNT BEGAN! ---->\n");
        while(this.getNumberOfAlivePlayers() > 1) { // 1==1
            Player activePlayer = this.players[this.activePlayerCounter];
            this.printRound();
            this.printPlayerLives();
            board.printPond();
            if(!activePlayer.isAlive()) {
                this.nextPlayer();
                continue;
            }
            if(this.deck.getDeck().size() == 0) {
                this.deck.reShuffleDeck();
            }
            activePlayer.draw(this.deck.getDeck());
            activePlayer.playCard(this.board, this.deck.getPile());
            this.nextPlayer();
        }
        System.out.println("\n<---- HUNT IS OVER! ---->\n");
        System.out.println("<---- THE BEST HUNTER IS: " + ANSI_CYAN + this.getWinner().getName() + ANSI_RESET + " ---->\n");
        for(Player player : players) {
            System.out.println(player.getName() + ": " + player.getNumberOfDucks());
        }
    }

    private Player getWinner() {
        Player winner = null;
        for(Player player : this.players) {
            if(player.isAlive()) {
                winner = player;
            }
        }
        return winner;
    }

    private void nextPlayer() {
        this.activePlayerCounter++;
        this.round++;
        if(this.activePlayerCounter >= this.players.length) {
            this.activePlayerCounter = 0;
        }
    }

    private void printRound() {
        System.out.println("\n<------- ROUND: " + (round + 1) +" ------->\n");
    }

    private void printPlayerLives() {
        for(Player player : this.players) {
            System.out.println(ANSI_GREEN + player.getName() + " has " + player.getNumberOfDucks() + " ducks." + ANSI_RESET);
        }
        System.out.println("\n");
    }
}
