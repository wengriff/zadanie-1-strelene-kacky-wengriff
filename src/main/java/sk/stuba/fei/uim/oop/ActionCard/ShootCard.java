package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.Interface.ISanitizeInput;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class ShootCard extends ActionCard implements ISanitizeInput {
    private int cardNumber;

    public ShootCard() {
        super("Shoot");
    }

    @Override
    public void envoke(Board board) {
        System.out.println("\nSelect a Target: ");
    
        this.cardNumber = ZKlavesnice.readInt("");
        this.sanitizeInput(board);

        PondCard selected = board.getPond().get(this.cardNumber - 1);
        if(selected instanceof DuckCard) {
            ((DuckCard)selected).setAlive(false);
        }
        board.getCrosshairs().set(this.cardNumber - 1, false);
    }

    @Override
    public void sanitizeInput(Board board) {
        while(this.cardNumber < 1 || this.cardNumber > board.getPond().size() || !(board.getCrosshairs().get(this.cardNumber - 1))) {
            System.out.println("\nInvalid card number! Try Again!");
            this.cardNumber = ZKlavesnice.readInt("");
        }
    }
}
