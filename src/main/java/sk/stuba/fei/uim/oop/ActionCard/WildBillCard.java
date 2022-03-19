package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class WildBillCard extends ActionCard {

    public WildBillCard() {
        super("Wild Bill");
    }

    @Override
    public void envoke(Board board) {
        for(;;) {
            System.out.println("\nSelect a Target: ");
    
            int cardNumber = ZKlavesnice.readInt("");
            
            if(cardNumber > 0 && cardNumber <= board.getPond().size()) {
                PondCard selected = board.getPond().get(cardNumber - 1);
                if(selected instanceof DuckCard && ((DuckCard)selected).isAlive()) {
                    ((DuckCard)selected).setAlive(false);
                }
            } else {
                System.out.println("\nInvalid card number! Try Again!");
            }
            board.getCrosshairs().set(cardNumber - 1, false);
            return;
        }
    } 
}
