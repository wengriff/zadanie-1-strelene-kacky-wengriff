package sk.stuba.fei.uim.oop.Interface;

import java.util.List;

import sk.stuba.fei.uim.oop.Board.PondCard;

public interface IPondAction {
    void moveDucks(List<PondCard> pond, List<PondCard> pondPile);
}
