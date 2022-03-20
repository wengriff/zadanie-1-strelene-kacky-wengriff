package sk.stuba.fei.uim.oop.Board;

public abstract class PondCard {
    private final String name;    

    public PondCard(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
