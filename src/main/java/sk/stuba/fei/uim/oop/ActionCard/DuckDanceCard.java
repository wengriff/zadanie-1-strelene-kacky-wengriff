package sk.stuba.fei.uim.oop.ActionCard;

import java.util.Collections;
import java.util.List;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.PondCard;

public class DuckDanceCard extends ActionCard {

    public DuckDanceCard() {
        super("Duck Dance");
    }

    @Override
    public void envoke(Board board) {
        this.removeCardsFromPond(board.getPond(), board.getPondPile());
        Collections.shuffle(board.getPondPile());
        board.dealCards(board.getPond(), board.getPondPile());
    }

    private void removeCardsFromPond(List<PondCard> pond, List<PondCard> pondPile) {
        for(int i = Board.POND_CARDS_NUMBER - 1; i >= 0; i--) {
            pondPile.add(pond.remove(i));
        }
    }
}