/*
Hand
linked list that holds CardNodes
Carlos Matos
Wednesday, January 21, 2026
 */

public class Hand {

    // data fields
    private CardNode headCardNode;

    // constructors
    public Hand() {
        this.headCardNode = null;
    }

    public Hand(char suit, Card.Rank rank) {
        CardNode card = new CardNode(suit, rank);
        this.headCardNode = card;
    }

    // accessors
    public CardNode getHeadCardNode() {
        return this.headCardNode;
    }

    public boolean isEmpty() {
        return this.headCardNode == null;
    }
    // mutators
    public void setHeadCardNode(CardNode headCardNode) {
        this.headCardNode = headCardNode;
    }

    // behavioural methods

    // a lot of methods are overloaded to use a CardNode object or input the suit and rank

    public void addCardNodeAtFront(char suit, Card.Rank rank) {
        CardNode card = new CardNode(suit, rank);
        if (this.headCardNode == null) // if the hand is empty
            this.headCardNode = card;
        else {
            card.setNext(this.headCardNode); // if the hand is not empty
            this.headCardNode = card;
        }
    }

    // add a card node at the front of the hand
    public void addCardNodeAtFront(CardNode card) {
        if (this.headCardNode == null) // if the hand is empty
            this.headCardNode = card;
        else { // if the hand is not empty
            card.setNext(this.headCardNode);
            this.headCardNode = card;
        }
    }

    // add a card node at the end of the hand
    public void addCardNodeOnEnd(char suit, Card.Rank rank) {
        CardNode card = new CardNode(suit, rank);
        if (this.headCardNode == null) // if the hand is empty
            this.headCardNode = card;
        else { // if the hand is not empty
            CardNode current = this.headCardNode;
            while (current.getNext() != null) { // get to the end of the hand
                current = current.getNext();
            } // while
            current.setNext(card);
        } // else
    }

    public void addCardNodeOnEnd(CardNode card) {
        if (this.headCardNode == null) // if the hand is empty
            this.headCardNode = card;
        else { // if the hand is not empty
            CardNode current = this.headCardNode; // traverse
            while (current.getNext() != null) { // traverse
                current = current.getNext();
            } // while
            current.setNext(card);
        } // else
    }

    public String toString() {
        CardNode current = this.headCardNode;
        String linkStuff = "";

        while (current != null) {
            linkStuff = linkStuff + current.toString();
            current = current.getNext();
        }

        if (linkStuff.equals(""))
            linkStuff = "The deck is clear.";

        return linkStuff;
    }

    // returns how many nodes have a specific suit and rank
    public int howManyCardNodes(char suit, Card.Rank rank) {
        int i = 0;
        CardNode current = this.headCardNode;
        do { // traverse
            if ((current.getSuit() == suit) && (current.getRank() == rank)) // if it has what we're looking for
                i++;
            current = current.getNext();
        }
        while (current != null); // until the end of the hand
        return i;
    }

    // mutators

    // finds the card node at a specific location like an array
    public CardNode cardNodeAt(int whichNode) {
        CardNode current = this.getHeadCardNode();
        for (int i = 0; i < whichNode - 1; i++) // find the Node at the specific location
            current = current.getNext();
        return current;
    }

    // remove card nodes with a specific suit and rank
    public void removeCardNodes(char suit, Card.Rank rank) {
        CardNode current = this.getHeadCardNode();
        do {
            if ((current.getNext().getSuit() == suit) && (current.getNext().getRank() == rank)) // look at the next node
                current.setNext(current.getNext().getNext()); // move two nodes ahead
            current = current.getNext();
        }
        while (current.getNext() != null);
    }

    // return the number total of all cards in the hand
    public int sumHand() {
        int sum = 0;
        int aces = 0;
        CardNode current = this.headCardNode;

        while (current != null) {
            sum = sum + current.getValue(); // add the current card value to the sum

            if (current.getRank() == Card.Rank.ACE) // if the card is an ace
                aces++;
            current = current.getNext(); // move to the next
        }
        while (aces > 0 && sum > 21) { // each ace can be 1 or 11
            sum = sum - 10; // subtract ten from the total for each ace.
            aces--;
        }

        if (sum == 0)
            System.out.println("The hand is clear.");
        return sum;
    }

    // clears all cards from the hand
    public void clear() {
        this.headCardNode = null;
    }

}
