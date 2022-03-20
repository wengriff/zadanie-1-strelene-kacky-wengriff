package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.Interface.ICheckInput;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class WildBillCard extends ActionCard implements ICheckInput {
    private int cardNumber;

    public WildBillCard() {
        super("Wild Bill");
    }

    @Override
    public void envoke(Board board) {
        System.out.println("\nSelect a Target (1-6): ");

        this.cardNumber = ZKlavesnice.readInt("");

        if(this.checkInput(board)){
            PondCard selected = board.getPond().get(cardNumber - 1);
            if(selected instanceof DuckCard) {
                ((DuckCard)selected).setAlive(false);
            }  else {
                System.out.println("\nInvalid card number! Try Again!");
            }
            board.getCrosshairs().set(cardNumber - 1, false);
        }
    }

    @Override
    public boolean checkInput(Board board) {
        while(this.cardNumber < 1 || this.cardNumber > board.getPond().size()) {
            System.out.println("\nInvalid card number! Try Again!");
            this.cardNumber = ZKlavesnice.readInt("");
        }
        return true;
    } 
}
