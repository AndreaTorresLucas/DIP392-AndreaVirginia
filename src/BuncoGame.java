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

    /*public void game() {
        addRound();
        int i;
        int count = 0;
        while (round < 6 + 1) {
            i = count % players.length; // i is never going to be bigger than 4, represents the number of the player
                                        // playing and its turns in the game
            indexActualPlayer = i;
            while (players[i].turn(round)) {
            }
            if (players[i].roundsWon()) {

                System.out.println(sb.printStats());

                addRound();
            }
            count++;
        }
    }*/

    public int getNamePlayer() {
        return indexActualPlayer;
    }

    public String toString() {
        return sb.toString();
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

    public String getName() {
        return name;
    }

    public int getPoints() {
        return score;
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
        return name + " has " + score + " points";
    }

}

class Player extends Score {
    int numberOfDice = 3;
    int winningPoints = 21;
    Dice dice;
    int score1player;

    /* constructor */
    Player(Dice dice, String name) {
        this.dice = dice;

        this.name = name;

        score = 0;
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
        score1player = score;
        boolean littleBunco = true;
        for (int i = 0; i < numberOfDice; i++) {
            if (dice.getDiceRolling(i) == round) {
                littleBunco = false;
                score++;
                score1player = score;
            } else if (i > 0 && dice.getDiceRolling(i) != dice.getDiceRolling(i - 1)) {
                littleBunco = false;
            }
        }
        if (score == 3) {
            bigBuncos++;
            score = 21;
            score1player = score;
        } else if (littleBunco) {
            buncos++;
            score = 5;
            score1player = score;
        }
        this.score += score;
        this.roundScore += score;
        if (roundScore > winningPoints) {
            this.score += winningPoints - roundScore;
        }
        return (score != 0 );
    }

    public int getScore1Player(){
        return score1player;
    }

    public boolean roundsWon() {
        if (roundScore >= winningPoints) {
            roundsWon++; // you win a round when you get 21 or more points
            return true;
        }
        return false;
    }

    public void addRound() {
        roundScore = 0;
    }
}