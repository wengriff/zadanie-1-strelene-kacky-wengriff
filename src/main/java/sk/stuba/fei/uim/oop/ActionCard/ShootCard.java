package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class ShootCard extends ActionCard {

    public ShootCard() {
        super("Shoot");
    }

    @Override
    public void envoke(Board board) {
        for(;;) {
            if(board.getCrosshairs().contains(true)){
                System.out.println("\nSelect a Target: ");
            
                int cardNumber = ZKlavesnice.readInt("");

                while(!(board.getCrosshairs().get(cardNumber - 1))) {
                    System.out.println("\n" + board.getPond().get(cardNumber - 1).getName() + " is not targeted!\n");
                    cardNumber = ZKlavesnice.readInt("");
                }
                
                PondCard selected = board.getPond().get(cardNumber - 1);
    
                if(cardNumber > 0 && cardNumber <= board.getPond().size() && board.getCrosshairs().get(cardNumber - 1)) {
                    if(selected instanceof DuckCard && ((DuckCard)selected).isAlive() && board.getCrosshairs().get(cardNumber - 1)) {
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
}
