
// like a node, but a card
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


    public String toString() {
        if (this.next != null)
            return (super.toString() + " is on top of \n");
        else
            return (super.toString() + " is on the bottom of the deck.");
    }






}
