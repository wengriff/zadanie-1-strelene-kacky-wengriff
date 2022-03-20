package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Interface.ICheckInput;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class AimCard extends ActionCard implements ICheckInput {
    private int cardNumber;

    public AimCard() {
        super("Aim");
    }

    @Override
    public void envoke(Board board) {
        for(;;) {
            System.out.println("Select an Aim: ");
    
            this.cardNumber = ZKlavesnice.readInt("");

            if(this.checkInput(board)) {
                board.getCrosshairs().set(cardNumber - 1, true);
                break;
            }
        }
    }

    @Override
    public boolean checkInput(Board board) {
        while(this.cardNumber < 1 || this.cardNumber > board.getPond().size() || board.getCrosshairs().get(this.cardNumber - 1)) {
            System.out.println("\nInvalid card number! Try Again!");
            this.cardNumber = ZKlavesnice.readInt("");
        }
        return true;
    }
}
