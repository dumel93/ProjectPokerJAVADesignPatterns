import java.util.*;

public abstract class Dealer extends Observable{

    protected Deck deck;
    protected Table table;
    protected String name;

    protected PokerGame pokerGame;
    protected ArrayList<Player> activePlayers;

    //W klasie Dealer activePlayers to lista graczy w tej rundzie gry.
    // dla każdego etapu (preflop, flop, turn, river, activePlayers oznacza gracza z ręką gracza, co oznacza rozwinięcie ręki)
    // Na każdym etapie liczba aktywnych graczy może się zmienić, ponieważ niektórzy gracze mogą spasować ręce.
    // bieżący gracze oznacza gracza, który jest w grze na początku tej rundy (preflop)
    // W klasie Table gracze to ci, którzy stawiają żetony na stole, ale nie w grze w tej rundzie.

    public Dealer(){
        this.name = null;
        this.deck = null;
        this.table = null;
        this.pokerGame = null;

    }

    public Dealer(String name){
        this.name = name;
        this.deck = null;
        this.table = null;
        this.pokerGame = null;

    }

    public void setPokerGame(PokerGame pokerGame){
        this.pokerGame = pokerGame;
    }

    public PokerGame getPokerGame(){
        return this.pokerGame;
    }

    public abstract void startHands();


    public void burnCard(){
        // spalanie karty
        getCard();
    }

    public void dealCommunityCard(){

        if(this.pokerGame.getNumCommunityCards() > 0){

        Card communityCard = getCard();

        this.table.getCommunityCards().setCard(communityCard);

        setChanged();
        notifyObservers();
        }
    }

    private void moveButton(){
        this.table.moveButton();
    }

    public ArrayList<Player> getWinners(){

        ArrayList<Player> winners = new ArrayList<Player>(this.activePlayers);
        for(int i = 0; i < winners.size(); i++ ){
        	if( (i+1) < winners.size() )
        		for(int j = i+1; j < winners.size(); j++ ){
        			if( (winners.get(i).getPokerHand()).compareTo(winners.get(j).getPokerHand()) > 0 ){
        				winners.remove(j);
        				j--;
        			}
        			else if( (winners.get(i).getPokerHand()).compareTo(winners.get(j).getPokerHand()) < 0 ){
        				winners.remove(i);
        				i--;
        				break;
        			}
        		}
        }

        double currentNumWinner = winners.size();
        
        for (int i = 0; i < currentNumWinner; i++){
            double amount = this.table.getPot()/currentNumWinner;
            winners.get(i).winPot(amount);
        }


        this.table.clearPot();
        this.table.clearCommunityHand();
        clearBets();

        return winners;
    }

    public void clearBets(){
        for (int i = 0; i < activePlayers.size(); i++){
        	this.activePlayers.get(i).setBet(0.0);
        	this.activePlayers.get(i).clearPlayerHand();
        }
    }

    public Card getCard(){
        return this.deck.getCard();
    }

    public void shuffleDeck(){
        this.deck.shuffleDeck();
    }

    public Deck getDeck(){
        return this.deck;
    }

    // set a new Deck
    public void setDeck(){
        this.deck = new Deck();
    }

    public Table getTable(){
        return this.table;
    }

    public void setTable(Table table){
        this.table = table;
        setActivePlayers();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getAnte(){
        return this.pokerGame.getGameType().getAnte();
    }

    public double getSmallBlind(){
        return this.pokerGame.getBettingStructure().getSmallBlind();
    }

    public double getBigBlind(){
        return this.pokerGame.getBettingStructure().getBigBlind();
    }

    public void getAnteFromPlayer(){
        // Get the ante from each player and add thess ante into pot.
        double totalAnte = 0;
        for(int i = 0; i < this.table.getCurrentPlayers().size(); i++ ){
            totalAnte += this.table.getCurrentPlayers().get(i).bet(getAnte());
        }
        this.table.addPot(totalAnte);
    }

    public void getBlindsFromPlayer(){

    // Na początku każdej rundy krupier otrzyma małą i dużą w ciemno od dwóch graczy z pozycji SB i BB.
        // Gracze to lista tablic = [gracz 1, gracz 2, ......, gracz 4], zakładamy, że maksymalna liczba graczy to 4.
        // Zdobądź małą w ciemno (SB) od jednego gracza w pozycji małej w ciemno: indeks gracza SB powinien wynosić buttonPosition + 1
        // Zdobądź dużą ciemną (BB) od jednego gracza na pozycji dużej ciemnej: indeks gracza BB powinien wynosić buttonPosition + 2


        int currentNumPlayer = this.table.getCurrentNumPlayer(); // <Option1>


        int buttonPosition = this.table.getButtonPosition();


        int smallBlindPostion = (buttonPosition + 1) % currentNumPlayer ;
        int bigBlindPosition = (buttonPosition + 2) % currentNumPlayer;


        Player smallBlindPlayer = this.table.getCurrentPlayers().get(smallBlindPostion);
        double smallBlind = smallBlindPlayer.bet(getSmallBlind());


        Player bigBlindPlayer = this.table.getCurrentPlayers().get(bigBlindPosition);
        double bigBlind = bigBlindPlayer.bet(getBigBlind());
    }

    public ArrayList<Player> getActivePlayers(){
        return activePlayers;
    }

    public void setActivePlayers(){
        this.activePlayers = new ArrayList<Player>(table.getPlayers());
    }

    public String toString(){
        String result = "";
        result += "Dealer : " + this.name + "\n";
        result += "PokerGame : " + this.pokerGame + "\n";
        result += "Stół: " + this.table + "\n";
        return result;
    }

}
