public class BlackjackClient {

    public static void main(String[] args) {

        Deck deck = new Deck();

        deck.push('d', Card.Rank.ACE);
        deck.push('s', Card.Rank.ACE);
        deck.push('c', Card.Rank.ACE);
        deck.push('h', Card.Rank.ACE);

        System.out.println(deck.pop());



    }

}
