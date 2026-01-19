public class Player {

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
