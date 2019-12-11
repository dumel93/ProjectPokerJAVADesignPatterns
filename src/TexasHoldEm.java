public class TexasHoldEm extends PokerGame{
    public TexasHoldEm(){
        super();
        this.numPlayerCards = 2;
        this.numCommunityCards = 5;
    }
    public TexasHoldEm(Bet bet, GameType gameType){
        super(bet, gameType);
        this.bet=bet;
        this.gameType=gameType;
        this.numPlayerCards = 2;
        this.numCommunityCards = 5;
    }



    public String toString(){
        String result = "\n To jest Texas Hold 'Em .\n";
        result += "Każdy gracz ma " + this.numPlayerCards + " kart ";
        result += "i ma wspolnych " + this.numCommunityCards + " kart.\n";
        result +=  "Struktura : " + this.bet + "\n";
        result +=  "Typ gry : " + this.gameType + "\n";
        result += "\n";
        return result;
    }
}
