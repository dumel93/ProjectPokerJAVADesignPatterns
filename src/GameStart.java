import java.io.Console;
import java.util.*;

public class GameStart {


    private static int smallBlind;
    private static int bigBlind;
    private static int chip;
    private static double totalBettingAmount = 0.0;
    private static Player player1 = null;
    private static Player player2 = null;
    private static Player player3 = null;
    private static Player player4 = null;
    private static boolean isPlay = true;
    private static boolean isAllIn = false;
    private static boolean isNoLimit = false;





    public static void main(String[] args) throws InterruptedException {

        // ====================================== Start a Game ======================================
        // =================================================================================================

        //  =================================== Create ===================================


        inputBlinds(); // start
        GameType gameType = selectGameType();// select between cashGame or Tournament,
        System.out.println("ante to: $" + String.valueOf(gameType.getAnte()));
        // ante in cash is 0 , in tournament is 1
        Bet bet = selectBet();// select between limit or nolimit or potlimit,
        PokerGame game = PokerGameFactory.createPokerGame(gameType, bet); // set up a game


        int tableNum = 1;
        int maxNumPlayer = 4;
        double amount = 0;

        //  dealer
        String dealerName = "Arkadiusz";

        // players

        Scanner sc1 = new Scanner(System.in);
        System.out.println("Wprowadz imie dla swojego gracza - bedzie to Player 1, 3 innych graczy dobierze komputer... ");

        String userName = sc1.nextLine(); // user and below random players
        String playerName2 = "Damian";
        String playerName3 = "Tomek";
        String playerName4 = "Marcin";


        Dealer dealer = new HoldemDealer(dealerName);
        Table table = TableFactory.createTable(tableNum, maxNumPlayer);


        dealer.setPokerGame(game);
        dealer.setTable(table);
        table.setDealer(dealer);
        dealer.setDeck();


        player1 = PlayerFactory.createPlayer(userName, chip); /// user

        player2 = PlayerFactory.createPlayer(playerName2, chip);
        player3 = PlayerFactory.createPlayer(playerName3, chip);
        player4 = PlayerFactory.createPlayer(playerName4, chip);


        player1.joinTable(table);
        player2.joinTable(table);
        player3.joinTable(table);
        player4.joinTable(table);


        dealer.addObserver(player1);
        dealer.addObserver(player2);
        dealer.addObserver(player3);
        dealer.addObserver(player4);


        // =================================== Print out all information ===================================

//        System.out.println(gameType);
//        System.out.println(bet);
        anim();
        System.out.println(game);
        System.out.println(table);
        anim();

        // ======================================== Start this Round ======================================
        // ================================================================================================

        //  =================================== Dealer starts the hand =================================
        System.out.println("\nZaczynamy....!\n");


        System.out.println("Small Blind i BigBlind :");
        dealer.startHands();

        // Print Player Info and each player's position: button, small blind, big blind
        System.out.println("\n");
        System.out.println(player1 + "\n" + " jako BTN przy stole.");
        System.out.println(player2 + "\n" + " jako SB");
        System.out.println(player3 + "\n" + " jako BB");
        System.out.println(player4 + "\n");


        //  =================================== At Pre-Flop Stage ===================================

        System.out.println("===========  Pre-Flop  ===========");
        anim();


        System.out.println("\nAktualne zaklady.. ");
        table.getCurrentBets();
        System.out.println();


        System.out.println("\nAktualne zaklady.. zaczyna Player3......");
        // Each player's action it is made by me
        System.out.println("Player 3's akcja: call");

        totalBettingAmount += player3.call();

        System.out.println("Player 4's akcja: call");
        totalBettingAmount += player4.call();


        /// now you make a decision-----------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (isPlay) userAction();


        System.out.println("Player 2's akcja: check");

        totalBettingAmount += player2.check();

        System.out.println("\nAktualne zaklady_____________________________");
        table.getCurrentBets();
        System.out.println();

        // Show the pot size
        System.out.println("Pula: $" + table.getPot());


        //  =================================== At Flop Stage ===================================
        System.out.println("\n=============  Flop  =============");
        anim();
        totalBettingAmount = 0.0;

        // dealer burn card
        dealer.burnCard();

        // dealer deal community cards (3 cards)
        dealer.dealCommunityCard();
        dealer.dealCommunityCard();
        dealer.dealCommunityCard();

        // Print out the community cards on the table
        System.out.println("\n 3 karty na stole to :");
        System.out.println(table.getCommunityCards());

        // Each player's action
        System.out.println();
        System.out.println("player 2's akcja: bet");
        amount = 10;
        totalBettingAmount += player2.bet(amount);

        System.out.println("player 3's akcja: call");
        totalBettingAmount += player3.call();

        System.out.println("player 4's akcja: call");
        totalBettingAmount += player4.call();

        /// now player1 make a decision
        if (isPlay) userAction();

        System.out.println("\nAktualne zaklady______________________________");
        table.getCurrentBets();
        System.out.println();

        // Show the pot size
        System.out.println("Pula: $" + table.getPot());


        //  =================================== At Turn Stage ===================================
        System.out.println("\n============= Turn  =============");
        anim();
        totalBettingAmount = 0.0;

        // dealer burn card
        dealer.burnCard();

        // dealer deal community cards (1 cards)
        dealer.dealCommunityCard();
        // Print out the community cards on the table
        System.out.println(" \n4 karty na stole to :");
        System.out.println(table.getCommunityCards());

        // Each player's action
        System.out.println();
        System.out.println("player 2's akcja: check");

        totalBettingAmount += player2.check();

        System.out.println("player 3's akcja: bet");
        amount = 30;
        totalBettingAmount += player3.bet(amount);

        System.out.println("player 4's akcja: call");
        totalBettingAmount += player4.call();

        if (isPlay) userAction();

        System.out.println("\nAktualne zaklady_____________________________");
        table.getCurrentBets();
        System.out.println();


        // Show the pot size
        System.out.println("Pula: $" + table.getPot());


        // =================================== At River Stage ===================================
        System.out.println("\n=============  River  =============");
        anim();
        totalBettingAmount = 0.0;

        // dealer burn card
        dealer.burnCard();

        // dealer deal community cards (1 cards)
        dealer.dealCommunityCard();

        // Print out the community cards on the table
        System.out.println("\n5 kart na stole to: ");
        System.out.println(table.getCommunityCards());

        // Each player's action
        System.out.println();
        System.out.println("player 3's akcja: bet");
        amount = 60;
        totalBettingAmount += player3.bet(amount);

        System.out.println("player 4's akcja: call");
        totalBettingAmount += player4.call();

        if (isPlay) userAction();

        System.out.println("\nAktualne zaklady to:_____________________________");
        table.getCurrentBets();
        System.out.println();

        // Show the pot size
        System.out.println("Pula: $" + table.getPot());


        // ======================================== End this Round ======================================
        // ===============================================================================================


        //show up
        System.out.println();
        if (isPlay || isAllIn) System.out.println(player1.getName() + ": " + player1.getPokerHand());
        System.out.println(player2.getName() + ": " + player2.getPokerHand());
        System.out.println(player3.getName() + ": " + player3.getPokerHand());
        System.out.println(player4.getName() + ": " + player4.getPokerHand());

        System.out.println();

        //Game has ended.  Get winners array and print them to the screen.
        ArrayList<Player> winners = dealer.getWinners();

        if (winners.size() > 1) {
            System.out.print("Wygrani : ");
            for (int i = 0; i < winners.size(); i++) {
                if (i + 1 >= winners.size())
                    System.out.println(winners.get(i).getName() + "!");
                else
                    System.out.print(winners.get(i).getName() + " i ");
            }
        } else
            System.out.println("Zwyciezca jest: " + winners.get(0).getName() + "!");


        System.out.println("Koniec partii");
        anim();
        System.out.println("\n");

    }


    //////////////////////////////////////////////////////////////////////////////// other functions




    public static void inputBlinds() {

        System.out.println("wprowadz wielkosc blind'a w $:");
        Scanner sc = new Scanner(System.in);

        smallBlind = sc.nextInt();
        bigBlind = smallBlind * 2;
        chip = smallBlind * 100;
        System.out.println("Grasz w Poker Texas Holdem:");
    }


    private static GameType selectGameType() {
        System.out.println("wybierz typ gry:  cash/tournament");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();

        while (true) {

            if (key.equals("cash")) {
                GameType cashGame = GameTypeFactory.createGameType(key, 0);
                return cashGame;
            } else if (key.equals("tournament")) {
                GameType tour = GameTypeFactory.createGameType(key, 1);
                return tour;
            } else
                System.out.println("zly typ");
            break;
        }
        return null;

    }


    private static Bet selectBet() {
        System.out.println("wybierz strukture zakladow:  limit/nolimit/potlimit");
        Scanner s2 = new Scanner(System.in);
        String key = s2.nextLine();
        boolean isOk = false;
        while (isOk == false) {
            if (key.equals("limit")) {
                Bet limit = BetFactory.createBettingStructure(key, smallBlind, bigBlind);
                return limit;
            } else if (key.equals("nolimit")) {
                isNoLimit = true;
                Bet nolimit = BetFactory.createBettingStructure(key, smallBlind, bigBlind);
                return nolimit;
            } else if (key.equals("potlimit")) {
                Bet potlimit = BetFactory.createBettingStructure(key, smallBlind, bigBlind);
                return potlimit;
            }

        }

        return null;
    }


    private static void anim() {
        ConsoleProgBar consoleHelper = new ConsoleProgBar();
        for (int i = 0; i < 10; i++) {
            consoleHelper.animate(i + "");
            //simulate a piece of task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void userAction() {

        System.out.println("Player 1 ... wybierz akcje fold/call/check/bet/all-in");

        Scanner ac = new Scanner(System.in);
        String key = ac.nextLine();
        if (key.equals("fold")) {
            totalBettingAmount += player1.fold();
            isPlay = false;

        } else if (key.equals("call")) {
            totalBettingAmount += player1.call();
        } else if (key.equals("check")) {
            totalBettingAmount += player1.check();
        } else if (key.equals("bet")) {
            System.out.println("Ile chcesz postawic?");
            double bet$ = ac.nextDouble();
            totalBettingAmount += player1.bet(bet$);
        } else if (key.equals("all-in")){
            if (isNoLimit) {
                System.out.println("All-in!!");
                player1.allin();
                isPlay = false;
                isAllIn = true;

            }
            else{
                System.out.println("nie mozesz zagrac ALL-IN bo nie grasz NO-LIMIT niestety  nie mozesz dalej postawic zakladu  w tej rundzie " +
                        "");
                isPlay=false;
            }

        }

    }
}





