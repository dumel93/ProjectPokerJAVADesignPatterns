public class PokerGameFactory extends PokerFactory{

    public static PokerGame createPokerGame(){


        return new TexasHoldEm();


    }




    public static PokerGame createPokerGame( GameType gameType, Bet bet){


        return new TexasHoldEm(bet,gameType);


    }
}
