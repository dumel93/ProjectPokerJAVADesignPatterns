public class Limit extends Bet {

    public Limit(){
        //Default constructor for "Limit" type objects.
    }

    public Limit(double smallBlind, double bigBlind){
        //Constructor for "Limit" type objects that sets the big and small blinds.
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    public String toString(){

        return ("\"Limit\" nie mozesz zagrac all-in");
    }

    public boolean repOK(){

        return bigBlind >=0 && smallBlind >= 0;
    }

}
