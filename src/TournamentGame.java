public class TournamentGame extends GameType{

    public TournamentGame(){

        this.ante = 1;
    }
   
    public TournamentGame(double ante){

        this.ante = ante;
    }

    public void setAnte(double ante){

        this.ante = ante;}

    public String toString(){

        return ("To jest turniej i ante to: $" + this.ante + ".");
    }

    public boolean repOK(){

        return this.ante > 0;
    }
    

}
