import java.io.*;
import java.util.Scanner;

public class Game {

    // DATA FIELDS

    // the deck that the cards will be dealt from
    // 4 of each suit, ace to king
    private Deck deck = new Deck();

    // each player has a hand object that is used instead of the default constructor
    private Hand dealerHand = new Hand();
    private Hand playerHand = new Hand();

    private Player player = new Player(playerHand);
    private Player dealer = new Player(dealerHand);

    private int balance = 1000;
    private final String saveFile = "balance.txt";


    // ACCESSORS

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

    public int getBalance() {
        return this.balance;
    }



    // MUTATORS
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

    public void setBalance(int balance) {
        this.balance = balance;
    }



    // BEHAVIOURAL

    // runs at the beginning of the game and makes the balance the number in the file
    public void loadBalance() throws IOException {
        File file = new File(saveFile);
        Scanner sc = new Scanner(file);
        while (sc.hasNextInt()) {
            this.balance = sc.nextInt();
        }

    }

    public void saveBalance() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(saveFile));
        writer.println(this.balance);
        writer.close();
    }


    public void pause(int time) {
        try {
            Thread.sleep (time);
        }
        catch (InterruptedException ie) {
        }
    }

    // adds a card to the player's hand by popping it from the deck stack
    public void drawCard(Player player) {
        player.getHand().addCardNodeOnEnd(deck.pop());
    }

    // clears player and dealer hands and reshuffles the deck
    // called at the beginning of every game
    public void reset() {
        playerHand.clear();
        dealerHand.clear();

        deck.clear();
        deck.fill();
        deck.fyShuffle();

        System.out.println("\n========================================");
        System.out.println("      WELCOME TO BLACKJACK!");
        System.out.println("Your balance: $" + this.balance);
        System.out.println("========================================\n");
    } // reset method


    // this is the only method that the client needs to play the game
    public void play() throws IOException {
        Scanner sc = new Scanner(System.in); // scanner to get user input
        boolean playAgain = true; // used to repeat the game if the user wants to keep playing
        while (playAgain == true) {
            reset(); // reshuffles the deck and clears the player and dealer's hands every run

            // check if the player has any money left
            if (this.balance <= 0) {
                System.out.println("You're out of money! Game over.");
                break; // end the game by breaking out of the while loop
            }

            // let the user make a bet
            int bet = 0;
            boolean validBet = false;
            while (validBet == false) {
                System.out.print("How much would you like to bet? $");
                // take the input as a string just in case they don't type a number
                bet = sc.nextInt(); // take the best as a string
                sc.nextLine(); // fixes the scanner error

                    if (bet > 0 && bet <= this.balance) {
                        validBet = true;
                    }
                    else if (bet > this.balance) {
                        System.out.println("\n***ERROR: INSUFFICIENT FUNDS***");
                        System.out.println("Your balance is $" + this.balance + "\n");
                    }
                    else {
                        System.out.println("\n***ERROR: INVALID BET***");
                        System.out.println("Please bet a positive number.\n");
                    }

            }

            // deal initial cards, two for each player
            System.out.println("Dealing initial cards...\n");

            for (int i = 0; i < 2; i++) { // in real blackjack, this is the order that cards are drawn
                drawCard(player);
                drawCard(dealer);
            }

            // show the dealer's first card
            pause(1000);
            System.out.println("Dealer shows: " + dealerHand.getHeadCardNode());
            pause(1000);

            // player's turn
            System.out.println("\n---- Your Hand ----");
            System.out.println(playerHand); // print the player's hand
            pause(2000);

            System.out.println("Your total: " + playerHand.sumHand()); // print the player's total
            System.out.println("-------------------\n");
            pause(1000);


            boolean stand = false; // if the player stands, they stop drawing cards
            // if the player has over 21, their turn ends and they lose
            // if they have 21, they can't draw any more cards
            // if they choose stand, their turn ends
            while (playerHand.sumHand() < 21 && stand == false) {
                System.out.print("Hit or stand? ");
                // user picks hit or stand
                // makes the string input lowercase and removes whitespace
                String hitOrStand = sc.nextLine().toLowerCase().trim();
                pause(1000);

                if (hitOrStand.equals("hit")) { // if the user wants to draw another card
                    System.out.println();
                    drawCard(player); // draw a card
                    System.out.println("---- Your Hand ----");
                    System.out.println(playerHand); // print the player's hand with the added card
                    pause(2000);
                    System.out.println("Your total: " + playerHand.sumHand()); // print the new total
                    System.out.println("-------------------\n");
                    pause(2000);
                }

                else if (hitOrStand.equals("stand")) { // if the user doesn't want to draw more cards
                    stand = true;
                    System.out.println("\nYou stand with " + playerHand.sumHand() + "\n"); // print their total
                    pause(1000);
                }

                else { // if the user doesn't type hit or stand, make them type again
                    System.out.println("\n***ERROR: INVALID INPUT***");
                    System.out.println("Please type 'hit' or 'stand'.\n");
                }
            } // while loop


            if (playerHand.sumHand() > 21) { // if their total is over 21, the dealer wins
                System.out.println("========================================");
                System.out.println("BUST! You went over 21.");
                System.out.println("Dealer wins.");
                this.balance = balance - bet; // lose the bet
                System.out.println("You lost $" + bet);
                System.out.println("New balance: $" + this.balance);
                System.out.println("========================================\n");
            }

            else { // if they didn't go over 21, it's the dealer's turn
                System.out.println("========================================");
                System.out.println("         DEALER'S TURN");
                System.out.println("========================================\n");
                pause(2000);

                // dealer drew 2 cards at the beginning but only revealed one
                System.out.println("Dealer reveals hidden card...");
                pause(2000);
                System.out.println(dealerHand); // reveal the dealer's hand with the second card revealed
                pause(2000);
                System.out.println("Dealer total: " + dealerHand.sumHand() + "\n"); // print the dealer's total
                pause(1000);

                // the dealer has to keep drawing while his hand has less than 17
                while (dealerHand.sumHand() < 17) {
                    System.out.println("Dealer must hit...");
                    pause(1000);
                    drawCard(dealer); // the dealer draws a card
                    System.out.println("Dealer total: " + dealerHand.sumHand() + "\n"); // print the dealer's total
                    pause(2000);
                }

                if (dealerHand.sumHand() > 21) { // if the dealer goes over 21, you win
                    System.out.println("========================================");
                    System.out.println("Dealer busts! YOU WIN!");
                    this.balance = balance + bet; // win the bet
                    System.out.println("You won $" + bet + "!");
                    System.out.println("New balance: $" + this.balance);
                    System.out.println("========================================\n");
                }

                // if the dealer didn't go over 21, find who has the higher score
                else {
                    // make int variables for each player's total
                    // i didn't do this before because the totals kept changing
                    // i didn't think of just updating a variable
                    int playerTotal = playerHand.sumHand();
                    int dealerTotal = dealerHand.sumHand();

                    System.out.println("========================================");
                    System.out.println("            RESULTS");
                    System.out.println("========================================");
                    pause(2000);
                    // print the player and dealer's totals
                    System.out.println("Your total:   " + playerTotal);
                    pause(2000);
                    System.out.println("Dealer total: " + dealerTotal);
                    System.out.println("========================================\n");
                    pause(2000);

                    // if the player scored higher than the dealer, the player wins
                    if (playerTotal > dealerTotal) {
                        System.out.println("YOU WIN!\n");
                        this.balance = balance + bet; // win the bet
                        System.out.println("You won $" + bet + "!");
                        System.out.println("New balance: $" + this.balance);
                    }
                    // if the dealer scored higher than the player, the dealer wins
                    else if (dealerTotal > playerTotal) {
                        System.out.println("Dealer wins.\n");
                        this.balance = balance - bet; // lose the bet
                        System.out.println("You lost $" + bet);
                        System.out.println("New balance: $" + this.balance + "\n");
                    }
                    // if the dealer and the player scored the same, it is a tie (push)
                    else {
                        System.out.println("PUSH! It's a tie.\n");
                        System.out.println("Your balance remains: $" + this.balance + "\n");
                    } // inner else
                } // outer else
            } // outer outer else

            saveBalance(); // save the balance after each round to the txt file

            // ask the user if they want to play again
            pause(2000);
            System.out.print("Play again? (y/n): ");
            // make their answer a lowercase string and remove whitespace
            String yn = sc.nextLine().toLowerCase().trim();

            while (!yn.equals("y") && !yn.equals("n")) {
                System.out.println("\n***ERROR: INVALID INPUT***");
                System.out.println("Please type 'y' or 'n'\n");
                yn = sc.nextLine().toLowerCase().trim();
            }

            pause(1000);
            // if the user says yes, repeat the game
            if (yn.equals("y"))
                playAgain = true;
                // if the user says no, stop the game
            else if (yn.equals("n")) {
                playAgain = false;
            }


        } // while loop

        // print a kind message if the user doesn't want to play anymore
        System.out.println("\n========================================");
        System.out.println("       Thanks for playing!");
        System.out.println("       Final balance: $" + this.balance);
        System.out.println("========================================\n");
    } // play method


}