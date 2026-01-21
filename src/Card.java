/*
Card
parent of the CarNode class
Carlos Matos
Wednesday, January 21, 2026
 */

public class Card {

    // DATA FIELDS
    private char suit;
    public enum Rank { // public so the list can be used by other classes
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    }
    private Rank rank;


    // CONSTRUCTOR METHODS

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
            case ACE:
                return 11;
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

            // ten, jack, queen, and king are all worth 10
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;

            // if the rank somehow isn't in the list, throw an error
            default:
                throw new IllegalStateException("Invalid rank: " + this.rank);
        } // switch
    } // getValue method

    public String toString() {
        return (this.rank + " of " + this.suit + "\n");
    }

    public Card clone() {
        return new Card(this.suit, this.rank);
    }

}
