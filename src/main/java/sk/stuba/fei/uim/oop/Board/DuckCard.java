package sk.stuba.fei.uim.oop.Board;

import sk.stuba.fei.uim.oop.Player.Player;

public class DuckCard extends PondCard {
    private boolean alive;
    private Player owner;

    public DuckCard(String name, boolean alive, Player owner) {
        super(name);
        this.alive = alive;
        this.owner = owner;
    }

    @Override
    public void envoke() {
        
    }

    public boolean isAlive() { return this.alive; }

    public void setAlive(boolean alive) { this.alive = alive; }

    public Player getOwner() { return this.owner; }
}
