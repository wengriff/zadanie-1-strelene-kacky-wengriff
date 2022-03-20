package sk.stuba.fei.uim.oop.ActionCard;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.DuckCard;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.Interface.IPondAction;
import sk.stuba.fei.uim.oop.Interface.ISanitizeInput;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class TurboDuckCard extends ActionCard implements IPondAction, ISanitizeInput{
    private int cardNumber;

    public TurboDuckCard() {
        super("Turbo Duck");
    }

    @Override
    public void envoke(Board board) {       
        System.out.println("\nSelect a Duck: ");

        this.cardNumber = ZKlavesnice.readInt("");
        this.sanitizeInput(board);

        this.moveDucks(board.getPond(), board.getPondPile());
        System.out.println("\nYou moved the Duck to the end!\n");

    }

    @Override
    public void moveDucks(ArrayList<PondCard> pond, ArrayList<PondCard> pondPile) {
        PondCard removed = pond.remove(this.cardNumber - 1);
        pond.add(0,removed);
    }

    @Override
    public void sanitizeInput(Board board) {
        while(this.cardNumber < 1 || this.cardNumber > board.getPond().size() || !(board.getPond().get(this.cardNumber - 1) instanceof DuckCard)) {
            System.out.println("\nInvalid card number! Try Again!");
            this.cardNumber = ZKlavesnice.readInt("");
        }
    }
}
