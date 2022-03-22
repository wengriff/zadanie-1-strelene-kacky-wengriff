package sk.stuba.fei.uim.oop.ActionCard;

import java.util.Collections;
import java.util.List;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.Board.PondCard;

public class ScatterCard extends ActionCard {

    public ScatterCard() {
        super("Scatter");
    }

    private void scatterPond(List<PondCard> pond) { Collections.shuffle(pond); }

    @Override
    public void envoke(Board board) {
        this.scatterPond(board.getPond());
    }
}
