public abstract class GameType{
  
    double ante;//



    public abstract void setAnte(double ante);


    public double getAnte(){
        return this.ante;
    }

    public abstract String toString();


    public abstract boolean repOK();

}
