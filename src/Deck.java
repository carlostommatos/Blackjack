public class Deck {

    private CardNode top;

    public Deck() {
        this.top = null;
    }

    public boolean isEmpty() {
        return (this.top == null);
    }

    public void push(char suit, Card.Rank rank) {
        CardNode newCard = new CardNode(suit, rank);
        newCard.setNext(this.top);
        this.top = newCard;
    }

    public CardNode pop() {
        CardNode popped = this.top;

        if (this.top != null) {
            this.top = this.top.getNext();
            return popped;
        }
        else
            throw new IllegalStateException("Can't pop an empty deck.");
    }

    public String toString() {
        String data = "";
        CardNode current = this.top;
        while (current != null) {
            data = current.toString() + "\n";
            current = current.getNext();
        }
        return data;
    }

    public int size() {
        if (this.isEmpty())
            return 0;

        int i = 1;
        CardNode current = this.top;
        while (current.getNext() != null) {
            i++;
            current = current.getNext();
        }
        return i;
    }

    public CardNode viewTop() {
        return this.top;
    }

}
