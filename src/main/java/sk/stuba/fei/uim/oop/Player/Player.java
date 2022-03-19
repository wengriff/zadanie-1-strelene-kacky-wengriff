package sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.ActionCard.ActionCard;
import sk.stuba.fei.uim.oop.ActionCard.ShootCard;
import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Player {
    private final String name;
    private ArrayList<ActionCard> hand;
    private ArrayList<DuckCard> ducks;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<ActionCard>();
        this.ducks = new ArrayList<DuckCard>();
    }

    public boolean isAlive() {
        for(DuckCard duck : this.ducks) {
            if(duck.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public String getName() { return this.name; }

    public ArrayList<ActionCard> getHand () { return this.hand; }

    public void setHand(ArrayList<ActionCard> hand) { this.hand = hand; }

    public void playCard(Board board, ArrayList<ActionCard> pile) {
        for(;;) {
            System.out.println("\n" + this.getName() + " is playing a card!");
            System.out.println("\nYour Cards: \n");
            for(int i = 0; i < this.hand.size(); i++) {
                System.out.println((i + 1) + ". " + this.getHand().get(i).getName());
            }
            System.out.println("\n");

            int cardNumber = 0;

            if(!this.canPlay(board, hand)) {
                System.out.println("\nYou don't have any cards to play! You have to discard one card!\n");
                cardNumber = ZKlavesnice.readInt("Choose a card (1-3) to discard: ");
                for(;;) {
                    if(cardNumber > this.hand.size() || cardNumber < 1) {
                        System.out.println("\nInvalid card number! Try Again!");
                        cardNumber = ZKlavesnice.readInt("Choose card (1-3) to discard: ");
                    } else {
                        break;
                    }
                }
                ActionCard toDiscard = this.hand.get(cardNumber - 1);
                this.discardCard(toDiscard, pile);
                return;
            }
    
            cardNumber = ZKlavesnice.readInt("Choose a card: ");
            
            if(cardNumber > 0 && cardNumber <= this.hand.size()) {
                if(!(board.getCrosshairs().contains(true))) {
                    while(this.getHand().get(cardNumber - 1) instanceof ShootCard) {
                        System.out.println("\n" + this.getHand().get(cardNumber - 1).getName() + " Card is not playable!\n");
                        cardNumber = ZKlavesnice.readInt("Choose another card: ");
                    }
                }
                ActionCard selected = this.hand.get(cardNumber - 1);
                selected.envoke(board);
                board.checkForDeadDucks(board.getPond());
                this.discardCard(selected, pile);
                return;
            } else {
                System.out.println("\nInvalid card number!");
            }
        }
    }

    private void addCard(ActionCard card) { this.hand.add(card); }

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

    public ArrayList<DuckCard> getDucks() { return this.ducks; }

    public int getNumberOfDucks() { return this.ducks.size(); }

    public void decrementDucks() {
        this.ducks.remove(0);
    }
}
