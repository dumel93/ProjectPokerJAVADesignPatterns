public abstract class Bet {
	double bigBlind ;
	double smallBlind;

	public void setSmallBlind(Double smallBlind){

		 this.smallBlind = smallBlind;
	}

	public double getSmallBlind(){
    	return this.smallBlind;
	}

	public void setBigBlind(Double bigBlind){

		this.bigBlind = bigBlind; 
	}

	public double getBigBlind(){

	    return this.bigBlind;
	}
	

	public abstract String toString();


	public  abstract boolean repOK();


   
}
