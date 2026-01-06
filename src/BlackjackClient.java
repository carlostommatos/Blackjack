public class BlackjackClient {

    public static void main(String[] args) {

        Hand hand = new Hand();
        hand.addCardNodeAtFront('d', Card.Rank.TEN);
        hand.addCardNodeAtFront('s', Card.Rank.ACE);

    }

}
