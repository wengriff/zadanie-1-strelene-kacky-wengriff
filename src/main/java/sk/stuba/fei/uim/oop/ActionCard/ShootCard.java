package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.Interface.ICheckInput;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class ShootCard extends ActionCard implements ICheckInput {
    private int cardNumber;

    public ShootCard() {
        super("Shoot");
    }

    @Override
    public void envoke(Board board) {
        System.out.println("\nSelect a Target: ");
    
        this.cardNumber = ZKlavesnice.readInt("");
        
        if(this.checkInput(board)) {
            PondCard selected = board.getPond().get(this.cardNumber - 1);
            if(selected instanceof DuckCard) {
                ((DuckCard)selected).setAlive(false);
            }
        }
        board.getCrosshairs().set(this.cardNumber - 1, false);
            
    }

    @Override
    public boolean checkInput(Board board) {
        while(this.cardNumber < 1 || this.cardNumber > board.getPond().size() || !(board.getCrosshairs().get(this.cardNumber - 1))) {
            System.out.println("\nInvalid card number! Try Again!");
            this.cardNumber = ZKlavesnice.readInt("");
        }
        return true;
    }
}
