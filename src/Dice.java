public class Dice {
    int[] rollingDice= new int[3];
    public Dice(){}

    public void roll(){     /*just three dices to roll */
        rollingDice[0] = (int) ((Math.random()*6))+1;
        rollingDice[1] = (int) ((Math.random()*6))+1;
        rollingDice[2] = (int) ((Math.random()*6))+1;
    }
    public int getDiceRolling(int i){
        return rollingDice[i];
    }
    
    public String toString() {
        return "Roll: [ " + rollingDice[0] + " " 
            + rollingDice[1] + " " + rollingDice[2] + " ]";
    }
}
