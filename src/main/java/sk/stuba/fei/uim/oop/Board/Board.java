package sk.stuba.fei.uim.oop.Board;

import java.util.ArrayList;
import java.util.Collections;

import sk.stuba.fei.uim.oop.Interface.IPondAction;
import sk.stuba.fei.uim.oop.Player.Player;

public class Board implements IPondAction {
    public static final int CROSSHAIR_CARDS_NUMBER = 6;
    public static final int POND_CARDS_NUMBER = 6;
    public static final int DUCKS_CARDS_PER_PLAYER = 5;
    public static final int WATER_CARDS_NUMBER = 5;

    private ArrayList<PondCard> pond;
    private ArrayList<PondCard> pondPile;
    private ArrayList<Boolean> crosshairs;

    public Board() {
        this.pond = new ArrayList<PondCard>();
        this.pondPile = new ArrayList<PondCard>();
        this.crosshairs = new ArrayList<Boolean>();
    }

    public ArrayList<Boolean> getCrosshairs() { return this.crosshairs; }
    
    public ArrayList<PondCard> getPond() { return this.pond; }

    public ArrayList<PondCard> getPondPile() { return this.pondPile; }

    private void createBoardDeck(Player[] players) {
        this.pond = new ArrayList<>();
        this.pondPile = new ArrayList<>();
        for(int i = 0; i < players.length; i++) {
            for(int j = 0; j < DUCKS_CARDS_PER_PLAYER; j++){
                DuckCard duck = new DuckCard("Duck", true, players[i]);
                players[i].getDucks().add(duck);
                this.pondPile.add(duck);
            }
        }
        for(int i = 0; i < WATER_CARDS_NUMBER; i++) {
            this.pondPile.add(new WaterCard("Water"));
        }
        Collections.shuffle(pondPile);
    }

    public void createBoard(Player[] players) {
        this.createBoardDeck(players);
        this.createCrosshairDeck();
        for(int i = 0; i < POND_CARDS_NUMBER; i++) {
            PondCard removed = pondPile.remove(0);
            pond.add(removed);
        }
    }

    private void createCrosshairDeck() {
        for(int i = 0; i < CROSSHAIR_CARDS_NUMBER; i++) {
            this.crosshairs.add(false);
        }
    }

    public void printPond() {
        for(int i = 0; i < POND_CARDS_NUMBER; i++) {
            if(this.pond.get(i) instanceof DuckCard) {
                System.out.println((i == 0 ? "↑ " : "| ") + (i + 1) + (crosshairs.get(i) ? ". Targeted --> " : ". Not Targeted --> ") + this.pond.get(i).getName() + " (" + ((DuckCard)this.pond.get(i)).getOwner().getName() + ")");
            } else {
                System.out.println((i == 0 ? "↑ " : "| ") + (i + 1) + (crosshairs.get(i) ? ". Targeted --> " : ". Not Targeted --> ") + this.pond.get(i).getName());
            }
        }
    }

    public void dealCards(ArrayList<PondCard> pond, ArrayList<PondCard> pondPile) {
        for(int i = 0; i < Board.POND_CARDS_NUMBER; i++) {
            PondCard removed = pondPile.remove(0);
            pond.add(removed);
        }
    }

    public void checkForDeadDucks(ArrayList<PondCard> pond) {
        for(int i = 0; i < pond.size(); i++) {
            PondCard card = this.getPond().get(i);
            if(card instanceof DuckCard) {
                if(!((DuckCard)card).isAlive()) {
                    ((DuckCard)card).getOwner().decrementDucks();
                    pond.remove(card);
                }
            }
        }
        this.moveDucks(pond, pondPile);
    }

    @Override
    public void moveDucks(ArrayList<PondCard> pond, ArrayList<PondCard> pondPile) {
        while(pond.size() < 6) {
            pond.add(pondPile.remove(0));
        }
    }
}
