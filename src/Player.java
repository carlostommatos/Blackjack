/*
Player
object for each player with a hand
Carlos Matos
Wednesday, January 21, 2026
 */

public class Player {

    // keeps all of the player's stuff in the same place

    // data fields
    private Hand hand;

    // constructors
    public Player() {
        this.hand = null;
    }

    public Player(Hand hand) {
        this.hand = hand;
    }

    // accessor
    public Hand getHand() {
        return this.hand;
    }

    // mutator
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    // behavioural
}
