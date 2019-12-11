public class NoLimit extends Bet {

    public NoLimit(){

    }

    public NoLimit(double smallBlind, double bigBlind){

        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    public String toString(){
        //AF implementation for the "Limit" class.
        return ("\"No-Limit\" Gra bez limitu zakladu moze byc all-in");
    }

    public boolean repOK(){
        //RI implementation for the "Limit" class.
        return this.bigBlind >=0 && this.smallBlind >= 0;
    }

}
