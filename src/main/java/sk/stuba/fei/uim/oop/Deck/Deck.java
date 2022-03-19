package sk.stuba.fei.uim.oop.Deck;

import java.util.ArrayList;
import java.util.Collections;

import sk.stuba.fei.uim.oop.ActionCard.ActionCard;
import sk.stuba.fei.uim.oop.ActionCard.AimCard;
import sk.stuba.fei.uim.oop.ActionCard.DuckDanceCard;
import sk.stuba.fei.uim.oop.ActionCard.DuckMarchCard;
import sk.stuba.fei.uim.oop.ActionCard.ScatterCard;
import sk.stuba.fei.uim.oop.ActionCard.ShootCard;
import sk.stuba.fei.uim.oop.ActionCard.TurboDuckCard;
import sk.stuba.fei.uim.oop.ActionCard.WildBillCard;

public class Deck {
    public static final int AIM_CARDS_NUMBER = 10;
    public static final int SHOOT_CARDS_NUMBER = 12;
    public static final int SCATTER_CARDS_NUMBER = 2;
    public static final int WILD_BILL_CARDS_NUMBER = 2;
    public static final int DUCK_MARCH_CARDS_NUMBER = 6;

    private ArrayList<ActionCard> deck;
    private ArrayList<ActionCard> pile;
    
    public void reShuffleDeck() {
        this.deck = this.pile;
        this.pile = new ArrayList<ActionCard>();
        Collections.shuffle(deck);
    }

    public void createDeck() {
        this.deck = new ArrayList<ActionCard>();
        this.pile = new ArrayList<ActionCard>();
        for(int i = 0; i < AIM_CARDS_NUMBER; i++) {
            this.deck.add(new AimCard());
        }
        for(int i = 0; i < SHOOT_CARDS_NUMBER; i++) {
            this.deck.add(new ShootCard());
        }
        for(int i = 0; i < WILD_BILL_CARDS_NUMBER; i++) {
            this.deck.add(new WildBillCard());
        }
        for(int i = 0; i < DUCK_MARCH_CARDS_NUMBER; i++) {
            this.deck.add(new DuckMarchCard());
        }
        for(int i = 0; i < SCATTER_CARDS_NUMBER; i++) {
            this.deck.add(new ScatterCard());
        }
        this.deck.add(new TurboDuckCard());
        this.deck.add(new DuckDanceCard());
        Collections.shuffle(deck);
    }

    public ArrayList<ActionCard> getDeck() { return this.deck; }
    
    public ArrayList<ActionCard> getPile() { return this.pile; }
}
