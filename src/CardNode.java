
/*
CardNode
child of the Card class. Nodes for the Deck stack and Hand linked list
Carlos Matos
Wednesday, January 21, 2026
 */

public class CardNode extends Card {

    // DATA FIELDS
    private CardNode next; // pointer to the next Card

    // CONSTRUCTORS

    // create a card node with just the suit and rank
    public CardNode(char suit, Rank rank) {
        super(suit, rank);
    }

    // create a card node with the pointer as well.
    public CardNode(char suit, Rank rank, CardNode next) {
        super(suit, rank);
        this.next = next;
    }


    // ACCESSORS
    public CardNode getNext() {
        return this.next;
    }

    // MUTATORS
    public void setNext(CardNode next) {
        this.next = next;
    }


    // BEHAVIOURAL
    public String toString() {
        if (this.next != null)
            return (super.toString());
        else
            return (super.toString());
    }

    public CardNode clone() {
        return new CardNode(this.getSuit(), this.getRank());
    }




}
