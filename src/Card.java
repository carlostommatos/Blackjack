public class Card {

    // DATA FIELDS
    private char suit;
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    }
    private Rank rank;


    // CONSTRUCTOR METHODS

//    public Card() {
//        this.suit = 'd';
//        this.rank = Rank.ACE;
//    }

    public Card(char suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // ACCESSOR METHODS

    public char getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    // returns the integer value of a card based on its rank
    public int getValue() {
        switch (this.rank) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11;
            default:
                throw new IllegalStateException("Invalid rank: " + this.rank);
        } // switch
    } // getValue method

    public String toString() {
        return (this.rank + " of " + this.suit);
    }

}
