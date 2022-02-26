/**
 * 
 * This is the Dice class the imitates the function of a dice
 *  @author Dhylan Usi
 * 
 */
public class Dice {
    public static int length;
    public int value;
    //set default value to -1
    public Dice(){
        value = -1;
    }
    public Dice (int num){
    //set the num into a variable named value
        value = num;
    }
    //set parameter for RandomNumber class and assign to variable
    public void roll(){
         value = RandomNumber.getRandomNumber(1, 6);
    }
    //return the value from public void roll()
    public int getValue(){
        return value;
    }
}


