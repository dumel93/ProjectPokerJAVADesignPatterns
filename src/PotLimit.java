public class PotLimit extends Bet {

    public PotLimit(){

    }

    public PotLimit(double smallBlind, double bigBlind){

        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    public String toString(){

        return ("\"PotLimit nie mozesz zagrac wiecej niz za pule w zakladzie ");
    }

    public boolean repOK(){

        return this.bigBlind >=0 && this.smallBlind >= 0;
    }

}
