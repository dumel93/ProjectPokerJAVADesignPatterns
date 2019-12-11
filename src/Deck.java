import java.util.ArrayList;

public class Deck{
    private static Card[] newDeck = new Card[52];//52 karty


    private final int DECK_SIZE = 52;
    private       int topCard = 52;
                  
    public static  String[] suitName = {"Spades", "Hearts", "Diamonds", 
                                      "Clubs"};//kolory

    public static  String[] rankName = {"A", "2", "3", "4", "5",
                                       "6", "7", "8", "9",
                                       "10", "J", "Q", "K"};//figury

    
        

    public Deck(){

    
        for(int s = 0; s < suitName.length; s++){

            for(int r = 0; r < rankName.length; r++){

                int rVal = 0,
                sVal = 4 - s;
        
                if(r == 0){
                    rVal = 14;
                }
                else{
                    rVal = r + 1;
                }
                this.newDeck[((rankName.length * s) + r)] = new Card(suitName[s], rankName[r], sVal , rVal);
            
            }
    
        }
    }

 
    public Card getCard(){

        this.topCard = this.topCard - 1;
        if(this.topCard < 0){
            System.out.println("Poza zasiegiem blad");
            this.topCard = 51;
            shuffleDeck();
            return this.newDeck[this.topCard];
        }
        else{
            return this.newDeck[this.topCard];
        }
    }
    
 
    public void shuffleDeck(){


       for(int i = 0; i < this.newDeck.length; i++){
         int shuf = i + (int)(Math.random() * (DECK_SIZE - i));
         
         Card shuffleCard = this.newDeck[shuf];
         this.newDeck[shuf] = this.newDeck[i];
         this.newDeck[i] = shuffleCard;     
       }
    }
  
    public int getNumCardsInDeck(){

        return this.topCard + 1;
    }

    public Card[] getDeck(){

       return this.newDeck;
    }


    
    public String toString(){

        String returnString = "";
        for(int i = 0; i < this.newDeck.length; i++){
          
        returnString += (newDeck[i].getSuit() + " " + 
                         newDeck[i].getRank() + " " + 
                         newDeck[i].getSuitNumber() + " " + 
                         newDeck[i].getRankNumber() + "\n");
         }
        return returnString;
    }

    public boolean repOK(){
        boolean testRepOk = true;
        for(int i = 0; i < newDeck.length; i++){
            if(testRepOk = true){
               testRepOk = !(newDeck[i] == null);
            }
        }
        return testRepOk;
    }   

}
