/*Andrea Torres Lucas 
Virginia Torres Lucas
APPLIED COMPUTER SYSTEMS SOFTWARE */


import javax.swing.*;

public class BuncoGame{

    Dice dice;
    Player[] players;
    int round;
    Scoreboard sb;
    int indexActualPlayer;

    public JButton rollDiceButton;

    /* contructor */
    BuncoGame(Player[] players) {
        dice = new Dice();
        sb = new Scoreboard(players);
        this.players = players;
        round = 0; /* initializing round */
    }

    void addRound() {
        round++; /* changes to the new round */
        for (int i = 0; i < players.length; i++) {
            players[i].addRound();
        }
        if (round > 6) {
        } else {
            System.out.println("Round: " + round);
        }
    }

    public int getRound() {
        return round;
    }


    public int getNamePlayer() {
        return indexActualPlayer;
    }

    public String toString() {
       return sb.toString();
      // return sb.printStats();
    }

    public String printStats() {
        return sb.printStats();
       // return sb.printStats();
     }

     public String totalScoreWinner() {
        return sb.totalScoreWinner();
       // return sb.printStats();
     }

     public String roundsWinner() {
        return sb.roundsWinner();
       // return sb.printStats();
     }

     public String littleBuncoWinner() {
        return sb.littleBuncoWinner();
       // return sb.printStats();
     }

     
     public String bigBuncoWinner() {
        return sb.bigBuncoWinner();
       // return sb.printStats();
     }


    public Player player(String name) {
        return new Player(dice, name);
    }

 

    
}

class Score {
    int score;
    String name;
    int roundScore;
    int buncos;
    int roundsWon;
    int bigBuncos;

    int totalScore;
    

    public String getName() {
        return name;
    }

    public int getPoints() {
        return score;
    }

    public int getTotalPoints(){
        return totalScore;
    }

    public void resetPoints() {
        score = 0;
    }

    public int getBuncos() {
        return buncos;
    }

    public int getbigBuncos() {
        return bigBuncos;
    }

    public int getroundsWon() {
        return roundsWon;
    }

    public String toString() {
        return name + " has " + totalScore + " points";
    }

}

class Player extends Score {
    int numberOfDice = 3;
    int winningPoints = 21;
    Dice dice;
    int score1player;
    int score1playerTotal;

    /* constructor */
    Player(Dice dice, String name) {
        this.dice = dice;

        this.name = name;

        score = 0;
        totalScore = 0;
        buncos = 0;
        bigBuncos = 0;
    }

    public boolean turn(int round) {
        dice.roll(); // roll the dice in each turn
        boolean ifgetpoints;

        System.out.println(name + "'s turn!");
        System.out.println(dice);

        ifgetpoints = scoreRollingDice(round);
        return ifgetpoints;
    }

    private boolean scoreRollingDice(int round) {
        int score = 0;
        int totalScore = 0;
        score1player = score;
        score1playerTotal = totalScore;
        boolean littleBunco = true;
        for (int i = 0; i < numberOfDice; i++) {
            if (dice.getDiceRolling(i) == round) {
                littleBunco = false;
                score++;
                totalScore++;
                score1player = score;
                score1playerTotal = totalScore;
            } else if (i > 0 && dice.getDiceRolling(i) != dice.getDiceRolling(i - 1)) {
                littleBunco = false;
            }
        }
        if (score == 3) {
            bigBuncos++;
            score = 21;
            totalScore = 21;
            score1player = score;
            score1playerTotal = totalScore;
        } else if (littleBunco) {
            buncos++;
            score = 5;
            totalScore = 5;
            score1player = score;
            score1playerTotal = totalScore;
        }
        this.score += score;
        this.totalScore += totalScore;
        this.roundScore += score;
        
        if (roundScore > winningPoints) {
            this.score += winningPoints - roundScore;
            this.totalScore += winningPoints - roundScore;
        }
        return (score != 0 );
    }

    public int getScore1Player(){
        return score1player;
    }

    public boolean roundsWon() {
        if (roundScore >= + winningPoints) {
            roundsWon++; // you win a round when you get 21 or more points
            return true;
        }
        return false;
    }

    public void addRound() {
        roundScore = 0;
    }
}