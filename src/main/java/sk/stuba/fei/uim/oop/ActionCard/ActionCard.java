package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;


public abstract class ActionCard {
    private final String name;

    public ActionCard(String name) {
        this.name = name;
    }

    public abstract void envoke(Board board);

    public String getName() { return this.name; }
}
