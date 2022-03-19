package sk.stuba.fei.uim.oop.ActionCard;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.Interface.IPondAction;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class TurboDuckCard extends ActionCard implements IPondAction {

    private int cardNumber;

    public TurboDuckCard() {
        super("Turbo Duck");
    }

    @Override
    public void envoke(Board board) {       
        for(;;) {
            System.out.println("\nSelect a Duck: ");
    
            this.cardNumber = ZKlavesnice.readInt("");

            while(!(board.getPond().get(cardNumber - 1) instanceof DuckCard)) {
                System.out.println("\nYou Selected Water! Select a Duck: \n");
                cardNumber = ZKlavesnice.readInt("");
            }
            
            if(cardNumber > 0 && cardNumber <= board.getPond().size()) {
                this.moveDucks(board.getPond(), board.getPondPile());
                System.out.println("\nYou moved the Duck to the end!\n");
                return;
            } else {
                System.out.println("\nInvalid card number! Try Again!");
            }
        }
    }

    @Override
    public void moveDucks(ArrayList<PondCard> pond, ArrayList<PondCard> pondPile) {
        PondCard removed = pond.remove(this.cardNumber - 1);
        pond.add(0,removed);
    }
}
