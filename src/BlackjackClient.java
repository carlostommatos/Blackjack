public class BlackjackClient {

    public static void main(String[] args) {

        Deck deck = new Deck();

        System.out.println(deck);

        deck.fyShuffle();
        System.out.println();
        System.out.println(deck);


    }

}
