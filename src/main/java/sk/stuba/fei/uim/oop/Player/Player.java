package sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.ActionCard.ActionCard;
import sk.stuba.fei.uim.oop.ActionCard.ShootCard;
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

        if(!this.canPlay(board, hand)){
            System.out.println("You don't have any cards to play! You have to discard one card!");
            this.discardCardNumber = ZKlavesnice.readInt("Choose a card (1-3) to discard: ");
            this.sanitizeDiscardInput(board);
            ActionCard toDiscard = this.hand.get(this.discardCardNumber - 1);
            this.discardCard(toDiscard, pile);

        } else {
            this.cardNumber = ZKlavesnice.readInt("Choose a card: ");
            this.sanitizeInput(board);
    
            ActionCard selected = this.hand.get(cardNumber - 1);
            selected.envoke(board);
            board.checkForDeadDucks(board.getPond());
            this.discardCard(selected, pile);
        }
    }

    public void draw(ArrayList<ActionCard> deck) {
        while(this.getHand().size() < 3) {
            this.addCard(deck.remove(0));
        }
    }

    private boolean canPlay(Board board,ArrayList<ActionCard> hand) {
        if(board.getCrosshairs().contains(true)) {
            return true;
        } else {
            for(ActionCard card : hand) {
                if(!(card instanceof ShootCard)) {
                    return true;
                }
            }
            return false;
        }
    }

    private void discardCard(ActionCard card, ArrayList<ActionCard> pile) {
        pile.add(card);
        this.hand.remove(card);
    }

    private void printHand() {
        System.out.println("\nYour Cards: ");
        for(int i = 0; i < this.hand.size(); i++) {
            System.out.println((i + 1) + ". " + this.getHand().get(i).getName());
        }
    }

    @Override
    public void sanitizeInput(Board board) {
        if(this.canPlay(board, this.hand) && !(board.getCrosshairs().contains(true))) {
            while(this.cardNumber < 1 || this.cardNumber > this.hand.size() || this.getHand().get(this.cardNumber - 1) instanceof ShootCard) {
                System.out.println("\nCard is not playable!\n");
                this.cardNumber = ZKlavesnice.readInt("Choose another card: ");
            }
        }
    }

    private void sanitizeDiscardInput(Board board) {
        while (this.discardCardNumber < 1 || this.discardCardNumber > this.hand.size()) {
            System.out.println("Invalid number!");
            this.discardCardNumber = ZKlavesnice.readInt("Choose a card (1-3) to discard: ");
        }
    }
}
