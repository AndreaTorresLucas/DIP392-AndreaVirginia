/*Andrea Torres Lucas 
Virginia Torres Lucas
APPLIED COMPUTER SYSTEMS SOFTWARE */
public class BuncoGame {
    
    Dice dice;
    Player[] players;
    int round;
    Scoreboard sb;
    boolean flagDice;
    boolean flagScore;

    /*contructor */
    BuncoGame(Player[] players){
        dice = new Dice();
        sb = new Scoreboard(players);
        this.flagDice = flagDice;
        this.flagScore = flagScore;
        this.players = players;
        round = 0; /* initializing round */
    }

    void addRound(){
        round++; /* changes to the new round */
        for (int i =0;i<players.length;i++){
            players[i].addRound();
        }
        if (round > 6){
        }else {System.out.println("Round: " + round);}
    }

    public void game(){ 
        addRound();
        int i ;
        int count = 0;         
        while (round < 6+1) {
            i = count%players.length;               //i is never going to be bigger than 4, represents the number of the player playing and its turns in the game
            while ( players[i].turn(round) ) { }
            if ( players[i].roundsWon()) {
                if (flagScore) {
                    System.out.println(sb.printStats());
                }                 
                addRound();
            }
            count++;
        }
     }

     public String toString(){return sb.toString();}
     public Player player(String name){return new Player( dice, flagDice,name);}
}

class Score
{
    int score;
    String name;
    int roundScore;
    int buncos;
    int roundsWon;
    int bigBuncos;

    public String getName(){return name;}
    public int getPoints(){return score;}
    public int getBuncos(){return buncos;}
    public int getbigBuncos(){return bigBuncos;}
    public int getroundsWon(){return roundsWon;}
    public String toString(){return name + "has" + score + "points";}
    
}

class Player extends Score{
    int numberOfDice = 3;
    int winningPoints = 21;
    Dice dice;
    boolean flagDice;
    boolean flagScore;
    /*constructor */
    Player(Dice dice,boolean flagDice,String name){
        this.dice = dice;
        this.flagDice=flagDice;
        this.name = name;
        this.flagScore = flagScore;
        score = 0;
        buncos = 0;
        bigBuncos=0;
    }
    public boolean turn(int round){
        dice.roll();                    //roll the dice in each turn
        boolean ifgetpoints;
        if (flagDice){
            System.out.println(name + "'s turn!");
            System.out.println(dice);
        }
        ifgetpoints = scoreRollingDice(round);
        return ifgetpoints;
    }
    private boolean scoreRollingDice(int round){
        int score = 0;
        boolean littleBunco = true; 
        for(int i=0;i<numberOfDice;i++){
            if(dice.getDiceRolling(i) == round){
                littleBunco = false;
                score++;
            }else if (i>0 && dice.getDiceRolling(i) !=dice.getDiceRolling(i-1)){
                littleBunco = false;
            }
        }
        if (score ==3){
            bigBuncos++;
            score = 5;
        } else if (littleBunco){
            buncos++;
            score = 3;
        }
        this.score +=score;
        this.roundScore += score;
        if(roundScore > winningPoints){
            this.score += winningPoints-roundScore;
        }
        return (roundScore<=winningPoints && score != 0 && score != 3);
    }
    public boolean roundsWon(){
        if(roundScore>=winningPoints){
            roundsWon++;        //you win a round when you get 21 or more points
            return true;
        }
        return false;
    }
    public void addRound(){roundScore=0;}
}
