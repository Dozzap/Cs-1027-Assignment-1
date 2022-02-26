/** 
 * 
 * This is the Yahtzee class
 * @author Dhylan Usi
 * 
*/

// import java.util.Arrays for future use
import java.util.Arrays;
//initialize Yahtzee class
public class Yahtzee{
//initialize dice array
    private Dice[] dice;

 /**
  * 
  *This class recalls a function from Dice.java that make rolls that dice using roll() method
  *the value gotten from the roll() will be assigned to the array called dice[]
  */   
    public Yahtzee(){;
        dice = new Dice[5];
        for (int i = 0; i< dice.length ; i++){
                dice[i] = new Dice();
                dice[i].roll();
        }
    }
  /**
  * 
  *Setting the values into a variable to turn it into an int instead of just the memory address 
  *
  */    
    public Yahtzee(Dice[] dice){
        this.dice = dice;
    }
   /**
  * 
  *This method intializes an array that takes into account on the dice roll's values
  *For loop iterates through the array if looks for how much a value's repetition in an array
  *Everytime there is a repetition, the getValue array corresponding it will increase by 1
  */       
    public int[] getValueCount(){
        int[] getValue = new int[6];
        for (int i = 0 ;i< dice.length; i++){
            if (dice[i].getValue() == 1){
                getValue[0] = getValue[0] +1;
            }
            else if (dice[i].getValue() == 2){
                getValue[1] ++;
            }
            else if (dice[i].getValue() == 3){
                getValue[2] ++;
            }
            else if (dice[i].getValue() == 4){
                getValue[3] ++;
            }
            else if (dice[i].getValue() == 5){
                getValue[4] ++;
            }
            else if (dice[i].getValue() == 6){
                getValue[5] ++;
            }
        }
		return getValue;
    }
/**
 * 
 * This method intiliazes two array variable, one is just renaming another variable, the other is for taking into account the possible scores
 * 
 */
    public int[ ] getScoreOptions(){
        int[] scoreOptions = new int[13];
        int[] rollValues= getValueCount();
        //this for loop takes into account all the possible values from ONES to SIXES and add them into the scoreOptions array
        for (int i= 0; i< rollValues.length; i++){
            scoreOptions[i] = rollValues[i]*(i+1);
        }
        //this for loop is responsible for taking the responsibles beyond the SIXES, this takes into account the special categories
        for (int i = 0; i < rollValues.length; i++){
            //checks if a value form getValueCount has a value of 3 for three of a kind
            if (rollValues[i] >= 3){
                //Since a full house is consists of a three of a kind and a two of a kind, we will use a loop to find if the two of a kind exist
                for (int j = 0; j< rollValues.length; j++){
                    if (rollValues[j]  == 2){
                        scoreOptions[8] = 25;
                    }
                }        
                //if there is a value equals to 3, a helper function called totalSum() will add all the dice values and add it to the corresponding index at the scoreOptions array
                scoreOptions[6] = totalSum();   
            }

            //checks if a value form getValueCount has a value of 4 for four of a kind
            if (rollValues[i] >= 4){
                //if there is a value equals to 3, a helper function called totalSum() will add all the dice values and add it to the corresponding index at the scoreOptions array
                scoreOptions[7] = totalSum();
            }
            //checks if a value form getValueCount has a value of 5 for a yahtzee
            if (rollValues[i] == 5){
                //if there is a value that equals 5, a fixed score of 50 will be added to the corresponding index at the scoreOptions array
                scoreOptions[11] = 50;
            }
            /**
             * checks if a small straight exist
             * it checks by looking if theres atleast 4 values, increasing by 1, and does not skip a number. that has a rollValues of 1 or greater than 1.
             * for example, since its 4 values of array, a rolledValues can be [0,1,2,1,1,0] or [0,1,1,1,1,0,] etc. this logic works if there are 2 indexes with value of 0s for getValues
             */
            if (rollValues[0] >= 1 && rollValues[1] >= 1 &&rollValues[2] >= 1 && rollValues[3] >= 1 || rollValues[1] >= 1 && rollValues[2] >= 1 &&rollValues[3] >= 1 && rollValues[4] >= 1 || rollValues[2] >= 1 && rollValues[3] >= 1 &&rollValues[4] >= 1 && rollValues[5] >= 1){
                scoreOptions[9] = 30;
            }
            /**
             * 
             * checks if a large straight exist
             * this follows almost the same logic as the small straight
             * since an getValues array can either be [0,1,1,1,1,1] or [1,1,1,1,1,0], we just check the indexes at the beginning and at the end, since the values at the middle (fomr index 1 to 4) will always be filled
             */
            if ((rollValues[1] == 1 && rollValues[2] == 1 && rollValues[3] == 1 && rollValues[4] == 1) && (rollValues[0] == 1 || rollValues[5] == 1)) {
                scoreOptions[10] = 40;
            }
            //since there is no logic needed for the chance value, it just uses the private helper and add the sum of 5 dice into the corresponding index for chance
            scoreOptions[12] = totalSum();
        }
        return scoreOptions;
    }


    //private method helper that calculates the value of all 5 dices and return it as an int
    private int totalSum(){
        int sum = 0;
        for (int i = 0; i< dice.length; i++){
            sum += dice[i].getValue();
        }
        return sum;
    }



    /**
     * intializes an array taht will take the highest integer and its index
     * This method locates the highest integer and its index and put it into an array
     * This works by comparing the value of the index to its consequent index, and the higher value will be assigned into a variable called "largest"
     * at the end of the loop, the index of the highst value will be returned and be used to located the value to then put into the array
     */
    public int[] score(){
        int [] scoreOption = getScoreOptions();
        int[] maxValue = new int [2];
        int largest = 0;
        for (int i = 0; i< getScoreOptions().length; i++){
            if ( scoreOption[i] > scoreOption[largest] ) largest = i;
        }
        maxValue[0] = getScoreOptions()[largest];
        maxValue[1] = largest;
        return maxValue;
    }


    //returns a boolean for checking if the values of 2 arrays are the same, in any order.
    public boolean equals(Yahtzee otherYahtzee) {
        return Arrays.equals(this.getValueCount(), otherYahtzee.getValueCount());
    }


    //returns a string that shows the value of the rolled dices
    public String toString(){
        return "Dice: " + "{" + dice[0].getValue() + ", "+ dice[1].getValue()  + ", "+ dice[2].getValue() + ", "+ dice[3].getValue() + ", "+ dice[4].getValue() + "}";
    }
}
