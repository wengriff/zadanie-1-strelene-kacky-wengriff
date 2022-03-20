package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Interface.ISanitizeInput;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class AimCard extends ActionCard implements ISanitizeInput {
    private int cardNumber;

    public AimCard() {
        super("Aim");
    }

    @Override
    public void envoke(Board board) {
        System.out.println("Select an Aim: ");

        this.cardNumber = ZKlavesnice.readInt("");
        this.sanitizeInput(board);

        board.getCrosshairs().set(cardNumber - 1, true);
    }

    @Override
    public void sanitizeInput(Board board) {
        while(this.cardNumber < 1 || this.cardNumber > board.getPond().size() || board.getCrosshairs().get(this.cardNumber - 1)) {
            System.out.println("\nInvalid card number! Try Again!");
            this.cardNumber = ZKlavesnice.readInt("");
        }
    }
}
