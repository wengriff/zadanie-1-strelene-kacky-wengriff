package sk.stuba.fei.uim.oop.ActionCard;

import java.util.List;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.PondCard;
import sk.stuba.fei.uim.oop.Interface.IPondAction;

public class DuckMarchCard extends ActionCard implements IPondAction {

    public DuckMarchCard() {
        super("Duck March");
    }

    @Override
    public void envoke(Board board) {
        this.moveDucks(board.getPond(), board.getPondPile());
    }    

    @Override
    public void moveDucks(List<PondCard> pond, List<PondCard> pondPile) {
        PondCard removed = pond.remove(0);
        pondPile.add(removed);
        pond.add(Board.POND_CARDS_NUMBER - 1, pondPile.remove(0));
    }
}
