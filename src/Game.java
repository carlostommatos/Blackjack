import java.util.Scanner;

public class Game {

    // data fields
    private Deck deck = new Deck();

    private Hand dealerHand = new Hand();
    private Hand playerHand = new Hand();

    private Player player = new Player(playerHand);
    private Player dealer = new Player(dealerHand);

    // accessors
    public Deck getDeck() {
        return this.deck;
    }

    public Hand getDealerHand() {
        return this.dealerHand;
    }
    public Hand getPlayerHand() {
        return this.playerHand;
    }

    public Player getPlayer() {
        return this.player;
    }
    public Player getDealer() {
        return this.dealer;
    }

    // mutators
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }
    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public void drawCard(Player player) {
        player.getHand().addCardNodeOnEnd(deck.pop());
    }


    // behavioural
    public void play() {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;
        this.reset();
        do {
            System.out.println("here");

            for (int i = 0; i < 2; i++) {
                drawCard(player);
                drawCard(dealer);
            }
            System.out.println("Player Hand: \n" + playerHand + "\n");
            System.out.println("Dealer Hand: \n" + dealerHand + "\n");

            boolean stand = false;
            while (playerHand.sumHand() < 21 && stand == false) {
                System.out.println(playerHand.sumHand());
                switch (sc.nextLine()) {
                    case "hit":
                        drawCard(player);
                        break;
                    case "stand":
                        stand = true;
                        break;
                }
            }
            System.out.println("Your total: " + playerHand.sumHand());
            System.out.println();
            while (dealerHand.sumHand() < 17)
                drawCard(dealer);
            System.out.println("Dealer total: " + dealerHand.sumHand());

            reset();
        }
        while (sc.nextBoolean() == true);

    }

    public void reset() {
        playerHand.clear();
        dealerHand.clear();

        deck.clear();
        deck.fill();
        deck.fyShuffle();
        System.out.println("\033[H\033[2J");
        System.out.flush();

    }


}

