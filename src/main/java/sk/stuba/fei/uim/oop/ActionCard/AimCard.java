package sk.stuba.fei.uim.oop.ActionCard;

import sk.stuba.fei.uim.oop.Board.Board;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class AimCard extends ActionCard {

    public AimCard() {
        super("Aim");
    }

    @Override
    public void envoke(Board board) {
        for(;;) {
            System.out.println("Select an Aim: ");
    
            int cardNumber = ZKlavesnice.readInt("");

            if(cardNumber > 0 && cardNumber <= board.getPond().size()) {
                while(cardNumber < 1 && cardNumber > board.getPond().size()) {
                    System.out.println("\nInvalid card number! Try Again!");
                    cardNumber = ZKlavesnice.readInt("");
                }

                for(;;) {
                    if(cardNumber > 0 && cardNumber <= board.getPond().size()) {
                        if(board.getCrosshairs().get(cardNumber - 1)) {
                            System.out.println("\nPosition " + cardNumber + ". already targeted, select another target: ");
                            cardNumber = ZKlavesnice.readInt("");
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("\nInvalid card number! Try Again!");
                        cardNumber = ZKlavesnice.readInt("");
                    }
                }
                board.getCrosshairs().set(cardNumber - 1, true);
                break;
            } else {
                System.out.println("Invalid Aim number! Try Again!");
            }

            // if(cardNumber > 0 && cardNumber <= board.getCrosshairs().size()) {
            //     board.getCrosshairs().set(cardNumber - 1, true);
            // } else {
            //     System.out.println("\nInvalid card number! Try Again!");
            // }
            // while(board.getCrosshairs().get(cardNumber - 1)) {
            //     System.out.println("\nPosition " + cardNumber + ". already targeted, select another target: ");
            //     cardNumber = ZKlavesnice.readInt("");
            // }
            
            // if(cardNumber > 0 && cardNumber <= board.getPond().size() && !board.getCrosshairs().get(cardNumber - 1)) {
            //     board.getCrosshairs().set(cardNumber - 1, true);
            //     return;
            // } else {
            //     System.out.println("\nInvalid card number! Try Again!");
            // }
        }
    }
}
