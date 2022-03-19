package sk.stuba.fei.uim.oop.Interface;

import java.util.ArrayList;

import sk.stuba.fei.uim.oop.Board.PondCard;

public interface IPondAction {
    void moveDucks(ArrayList<PondCard> pond, ArrayList<PondCard> pondPile);
}
