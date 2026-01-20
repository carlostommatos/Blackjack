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
    public void addCardNodeAtFront(char suit, Card.Rank rank) {
        CardNode card = new CardNode(suit, rank);
        if (this.headCardNode == null)
            this.headCardNode = card;
        else {
            card.setNext(this.headCardNode);
            this.headCardNode = card;
        }
    }

    public void addCardNodeAtFront(CardNode card) {
        if (this.headCardNode == null)
            this.headCardNode = card;
        else {
            card.setNext(this.headCardNode);
            this.headCardNode = card;
        }
    }

    public void addCardNodeOnEnd(char suit, Card.Rank rank) {
        CardNode card = new CardNode(suit, rank);
        if (this.headCardNode == null)
            this.headCardNode = card;
        else {
            CardNode current = this.headCardNode;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(card);
        }
    }

    public void addCardNodeOnEnd(CardNode card) {
        if (this.headCardNode == null)
            this.headCardNode = card;
        else {
            CardNode current = this.headCardNode;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(card);
        }
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

    public int howManyCardNodes(char suit, Card.Rank rank) {
        int i = 0;
        CardNode current = this.headCardNode;
        do {
            if ((current.getSuit() == suit) && (current.getRank() == rank))
                i++;
            current = current.getNext();
        }
        while (current != null);
        return i;
    }

    // mutators
    public CardNode cardNodeAt(int whichNode) {
        CardNode current = this.getHeadCardNode();
        for (int i = 0; i < whichNode - 1; i++)
            current = current.getNext();
        return current;
    }

    public void removeCardNodes(char suit, Card.Rank rank) {
        CardNode current = this.getHeadCardNode();
        do {
            if ((current.getNext().getSuit() == suit) && (current.getNext().getRank() == rank))
                current.setNext(current.getNext().getNext());
            current = current.getNext();
        }
        while (current.getNext() != null);
    }

    public int sumHand() {
        int sum = 0;
        int aces = 0;
        CardNode current = this.headCardNode;

        while (current != null) {

                sum = sum + current.getValue();
            if (current.getRank() == Card.Rank.ACE)
                aces++;
            current = current.getNext();
        }
        while (aces > 0 && sum > 21) {
            sum = sum - 10;
            aces--;
        }

        if (sum == 0)
            System.out.println("The hand is clear.");
        return sum;
    }

    public void clear() {
        this.headCardNode = null;
    }

}
