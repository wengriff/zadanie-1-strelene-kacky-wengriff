package sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.ActionCard.ActionCard;
import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Interface.ISanitizeInput;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Player implements ISanitizeInput {
    private final String name;
    private ArrayList<ActionCard> hand;
    private ArrayList<DuckCard> ducks;
    private int cardNumber;
    private int discardCardNumber;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<ActionCard>();
        this.ducks = new ArrayList<DuckCard>();
    }

    public boolean isAlive() { return this.ducks.size() > 0 ? true : false; }

    public String getName() { return this.name; }

    public ArrayList<ActionCard> getHand () { return this.hand; }

    public ArrayList<DuckCard> getDucks() { return this.ducks; }

    public int getNumberOfDucks() { return this.ducks.size(); }

    public void setHand(ArrayList<ActionCard> hand) { this.hand = hand; }

    public void decrementDucks() { this.ducks.remove(0); }

    private void addCard(ActionCard card) { this.hand.add(card); }

    public void playCard(Board board, ArrayList<ActionCard> pile) {

        System.out.println("\n---> " + this.getName() + "'s turn!");
        this.printHand();
        System.out.println("\n");

        if(!this.canPlayCard(board.getCrosshairs(), hand)){
            this.discard(pile);
        } else {
            this.play(board, pile);
        }
    }

    public void draw(ArrayList<ActionCard> deck) {
        while(this.getHand().size() < 3) {
            this.addCard(deck.remove(0));
        }
    }

    private boolean canPlayCard(ArrayList<Boolean> crosshairs,ArrayList<ActionCard> hand) {
        return this.canPlayCardCheck(crosshairs, "Shoot") && this.canPlayCardCheck(crosshairs, "Aim");
    }

    private boolean canPlayCardCheck(ArrayList<Boolean> crosshairs, String name) {
        boolean ok = true;
        if(!(crosshairs.contains(name == "Shoot" ? true : false))) {
            for(ActionCard card : this.hand) {
                if(card.getName().equals(name)) {
                    ok = false;
                } else {
                    return true;
                }
            }
        }
        return ok;
    }

    private void discardCard(ActionCard card, ArrayList<ActionCard> pile) {
        pile.add(card);
        this.hand.remove(card);
    }

    private void discard(ArrayList<ActionCard> pile) {
        System.out.println("You don't have any cards to play! You have to discard one card!");
        this.discardCardNumber = ZKlavesnice.readInt("Choose a card (1-3) to discard: ");
        this.sanitizeDiscardInput();
        ActionCard toDiscard = this.hand.get(this.discardCardNumber - 1);
        this.discardCard(toDiscard, pile);
    }

    private void play(Board board, ArrayList<ActionCard> pile) {
        this.cardNumber = ZKlavesnice.readInt("Choose a card: ");
        this.sanitizeInput(board);
        ActionCard selected = this.hand.get(cardNumber - 1);
        selected.envoke(board);
        board.checkForDeadDucks(board.getPond());
        this.discardCard(selected, pile);
    }

    private void printHand() {
        System.out.println("\nYour Cards: ");
        for(int i = 0; i < this.hand.size(); i++) {
            System.out.println((i + 1) + ". " + this.getHand().get(i).getName());
        }
    }

    private void sanitizeDiscardInput() {
        while (this.discardCardNumber < 1 || this.discardCardNumber > this.hand.size()) {
            System.out.println("Invalid number!");
            this.discardCardNumber = ZKlavesnice.readInt("Choose a card (1-3) to discard: ");
        }
    }

    @Override
    public void sanitizeInput(Board board) {
        if(this.canPlayCard(board.getCrosshairs(), this.hand) && !(board.getCrosshairs().contains(true))) {
            while(this.cardNumber < 1 || this.cardNumber > this.hand.size() || this.getHand().get(this.cardNumber - 1).getName().equals("Shoot")) {
                System.out.println("\nCard is not playable!\n");
                this.cardNumber = ZKlavesnice.readInt("Choose another card: ");
            }
        }
    }
}
