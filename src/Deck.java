import java.util.Random;

/*
Deck
a 52 card deck
Carlos Matos
Wednesday, January 21, 2026
 */
// stack
public class Deck {

    // DATA FIELDS
    private CardNode top;
    private char [] suits = {'d', 'h', 'c', 's'}; // diamond, heart, club, spade
    private Card.Rank [] ranks = Card.Rank.values(); // make an array of the card ranks from the enum


    // CONSTRUCTORS
    public Deck() {
        for (int i = 0; i < this.suits.length; i++) { // i for loop: suits
            for (int n = 0; n < this.ranks.length; n++) { // n for loop: ranks
                this.push(this.suits [i], this.ranks [n]); // make a new card with every suit and rank
            } // i for loop
        } // n for loop
    }

    // returns whether or not the deck is empty
    public boolean isEmpty() {
        return (this.top == null);
    }

    // push method using suit and rank (for initial deck)
    public void push(char suit, Card.Rank rank) {
        CardNode newCard = new CardNode(suit, rank);
        newCard.setNext(this.top); // make the new card point to the current top
        this.top = newCard; // make the new card the top card
    }

    // overloaded push method using CardNode (for shuffling)
    public void push(CardNode card) {
        card.setNext(this.top);// make the new card point to the current top
        this.top = card; // make the new card the top card
    }

    // unlike the regular node class,
    // returns the whole CardNode object
    public CardNode pop() {
        CardNode popped = this.top.clone(); // make a copy of the top card

        if (this.top != null) { // if there is a card to pop
            this.top = this.top.getNext(); // make the next card the top card
            return popped;
        }
        else // if there isn't a card to pop
            throw new IllegalStateException("Can't pop an clear deck.");
    }

    // TO STRING
    public String toString() {

        if (this.isEmpty())
            return "The deck is clear.";
        String data = "";
        CardNode current = this.top;
        while (current != null) { // traverse
            data += current.toString();
            current = current.getNext();
        }
        return data;
    }

    // ACCESSORS
    public CardNode getTop() {
        return this.top;
    }

    // how many cards are in the array
    public int getSize() {
        if (this.isEmpty()) // if the deck is empty
            return 0;

        int i = 1; // minimum 1 card
        CardNode current = this.top;
        while (current.getNext() != null) { // traverse
            i++;
            current = current.getNext();
        }
        return i;
    }


    // fischer yates shuffle from geeksforgeeks.com
    public void fyShuffle() {

        // 1. put the cards in an array so they can be shuffled
        int size = this.getSize();
        CardNode [] cards = new CardNode [size];

        // 2. shuffle the cards using the fischer yates shuffle
        for (int i = 0; i < size; i++)
            cards [i] = this.pop();

        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int n = random.nextInt(i + 1);
            CardNode temp = cards [i];
            cards [i] = cards [n];
            cards [n] = temp;
        }

        // 3. put the shuffled cards back into the deck
        for (int i = 0; i < size; i++) {
            this.push(cards [i]);
        }

    }

    // clear all card from the deck by popping them
    public void clear() {
        while (this.isEmpty() == false)
            this.pop();
    }

    public void fill() {
        for (int i = 0; i < this.suits.length; i++) { // i for loop: suits
            for (int n = 0; n < this.ranks.length; n++) { // n for loop: ranks
                this.push(this.suits [i], this.ranks [n]); // make a new card with every suit and rank
            } // i for loop
        } // n for loop
    }



}
