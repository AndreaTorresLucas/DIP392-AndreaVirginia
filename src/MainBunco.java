import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MainBunco extends JFrame {

    private int numberPlayers = 4;
    private JLabel[] playerLabels;
    private JLabel[] playerScoreDisplay;
    private JLabel[] playerScorePoints;
    private JLabel numberRounds;
    private JButton rollDiceButton;
    private JButton menuButton;
    private Player[] players;
    private BuncoGame bg;
    private int i;
    private int round;
    private int count;
    private JPanel playerPanel;
    private Dice diceGame;
    private ImageIcon[] diceIcons;
    private int[] dataDice;
    private ImageIcon iconn;
    private JDialog dialog;
    private JPanel infoSBPanel;
    private JPanel inforulesPanel;
    public static MainBunco mainB;

    // Constructor
    public MainBunco() {
        super("BuncoGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // personalized icon window
        iconn = new ImageIcon("src/images/iconbg.png");

        //obtain instance of class Image
        java.awt.Image imagee = iconn.getImage();

        // set personalized icon in the JFrame
        setIconImage(imagee);
        setLayout(new GridLayout(4, 1));

        // Display of the names at the top of the screen
        playerPanel = new JPanel(new GridLayout(2, 4));
        String[] playerNames = { "Player1", "Player2", "Player3", "Player4" }; // we need the string names
        playerLabels = new JLabel[numberPlayers];
        Font font = new Font("Bauhaus 93", Font.PLAIN, 50);
        for (int i = 0; i < numberPlayers; i++) {
            playerLabels[i] = new JLabel(playerNames[i]);

            playerPanel.add(playerLabels[i]);
            playerLabels[i].setFont(font); // text configutarion in the label
        }
        playerScorePoints = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            playerScorePoints[i] = new JLabel("Points: 0");
            playerPanel.add(playerScorePoints[i]);
            playerScorePoints[i].setFont(new Font("Bauhaus 93", Font.PLAIN, 20));
        }
        Color marron = Color.decode("#ECEFF1");
        playerPanel.setBackground(marron);
        add(playerPanel, BorderLayout.NORTH);
        // creation of a border
        Border border = BorderFactory.createLineBorder(Color.BLACK, 8, true);
        // apply border to panel
        playerPanel.setBorder(border);

        // Display of the points
        JPanel scoresDisplay = new JPanel(new GridLayout(1, 4));
        scoresDisplay.setPreferredSize(new Dimension(200, 100));
        playerScoreDisplay = new JLabel[4];
        for (int i = 0; i < 3; i++) {
            playerScoreDisplay[i] = new JLabel("");
            scoresDisplay.add(playerScoreDisplay[i]);
        }

        // Button of rolling the dice
        rollDiceButton = new JButton("Roll the dice");
        rollDiceButton.addActionListener(new RollButtonListener());
        Color darkRed = new Color(139, 0, 0);
        rollDiceButton.setBackground(Color.BLACK);
        Font font1 = new Font("Bauhaus 93", Font.PLAIN, 30);
        rollDiceButton.setFont(font1);
        rollDiceButton.setForeground(Color.WHITE);
        // Color lightBlue = new Color(173, 216, 230);
        // Color forestGreen = Color.decode("#145A32");
        Color forestGreen = Color.decode("#880E4F");
        scoresDisplay.setBackground(forestGreen);
        add(scoresDisplay);
        scoresDisplay.add(rollDiceButton);

        players = new Player[numberPlayers];
        bg = new BuncoGame(players);

        Player p1 = bg.player("Player1");
        players[0] = p1;
        Player p2 = bg.player("Player2");
        players[1] = p2;
        Player p3 = bg.player("Player3");
        players[2] = p3;
        Player p4 = bg.player("Player4");
        players[3] = p4;

        JPanel roundplayer = new JPanel();

        numberRounds = new JLabel("<html><br>Roll the dice to start the game, GOOD LUCK!!</html>");
        numberRounds.setFont(font1);
        numberRounds.setForeground(Color.black);
        roundplayer.add(numberRounds);
        roundplayer.setBackground(forestGreen);
        numberRounds.setHorizontalAlignment(SwingConstants.CENTER);
        add(roundplayer);
        count = 0;
        diceGame = bg.dice;
        bg.addRound();

        // panel for menu button
        JPanel menuPanel = new JPanel();

        menuButton = new JButton("Menu");
        menuButton.addActionListener(new MenuButtonListener());
        menuPanel.add(menuButton);
        darkRed = Color.decode("#000033");
        UIManager.put("Button.background", darkRed);
        UIManager.put("Button.font", new Font("Bauhaus 93", Font.PLAIN, 30));

        // personalized style to button
        menuButton.setBackground(UIManager.getColor("Button.background"));
        menuButton.setFont(UIManager.getFont("Button.font"));
        menuButton.setForeground(Color.WHITE);
        menuPanel.setBackground(Color.BLACK);
        add(menuPanel, BorderLayout.SOUTH);

        // for showing the actual display
        pack();
        setVisible(true);

        // directory path for the images of the dice sides
        String[] diceImages = { "images/dice1.png", "images/dice2.png", "images/dice3.png", "images/dice4.png",
                "images/dice5.png", "images/dice6.png" };

        // Array to store icons of the images
        diceIcons = new ImageIcon[diceImages.length];

        // create icons
        for (int i = 0; i < diceImages.length; i++) {
            diceIcons[i] = new ImageIcon(getClass().getResource(diceImages[i]));
        }

    }

    private class RollButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            i = count % players.length;// i is never going to be bigger than 4, represents the number of the player
            round = bg.getRound();
            if (round < 7) {

                if (players[i].turn(round)) {
                    count = count - 1;
                }

                if (players[i].roundsWon()) {
                    playSound("sounds/Rollingdice.wav");
                    playerScorePoints[0].setText("Points: " + players[0].getPoints());
                    playerScorePoints[1].setText("Points: " + players[1].getPoints());
                    playerScorePoints[2].setText("Points: " + players[2].getPoints());
                    playerScorePoints[3].setText("Points: " + players[3].getPoints());
                    for (int j = 0; j < 3; j++) {
                        for (int i = 1; i < 7; i++) {
                            if (dataDice[j] == i) {
                                playerScoreDisplay[j].setIcon(diceIcons[i - 1]);
                            }
                        }

                    }
                    if(players[i].getScore1Player()==21){
                        playSound("/sounds/bigbunco.wav");
                    }
                    if(players[i].getScore1Player()==5){
                        playSound("/sounds/littlebunco.wav");
                    }
                    playSound("sounds/win.wav");
                    numberRounds
                            .setText("<html><br>" + players[i].getName() + " has won this round " + round + "</html>");
                    bg.addRound();
                    for (int j = 0; j < players.length; j++) {
                        players[j].resetPoints();
                    }
                } else {

                    int pointsPerPlayer = players[i].getScore1Player();
                    if(pointsPerPlayer==5){
                        playSound("/sounds/littlebunco.wav");
                    }
                    numberRounds.setText("<html><br>Round: " + round
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Turn: "
                            + players[i].getName()
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Scored points: "
                            + pointsPerPlayer + "</html>");
                    playSound("sounds/Rollingdice.wav");
                    playerScorePoints[0].setText("Points: " + players[0].getPoints());
                    playerScorePoints[1].setText("Points: " + players[1].getPoints());
                    playerScorePoints[2].setText("Points: " + players[2].getPoints());
                    playerScorePoints[3].setText("Points: " + players[3].getPoints());
                    dataDice = diceGame.rollingDice;
                    for (int j = 0; j < 3; j++) {
                        for (int i = 1; i < 7; i++) {
                            if (dataDice[j] == i) {
                                playerScoreDisplay[j].setIcon(diceIcons[i - 1]);
                            }
                        }

                    }

                }
                count++;
                System.out.println(count);
            } else {
                playSound("sounds/endofgame.wav");
                numberRounds.setText("<html><br>END OF GAME</html>");
            }

        }
    }

    // ActionListener for menu button
    private class MenuButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playSound("sounds/Menu.wav");
            // logic to show menu with scoreboard, new game, rules buttons
            showMenuInfo();
        }
    }

    private void showMenuInfo() {
        dialog = new JDialog(this, "Menu", true);
        dialog.setSize(600, 100);
        dialog.setLocationRelativeTo(null);

        JButton button1 = new JButton("Score Board");
        button1.setForeground(Color.WHITE);
        button1.addActionListener(new ScoreBoardButtonListener());
        JButton button2 = new JButton("New Game");
        button2.addActionListener(new NewGameButtonListener());
        button2.setForeground(Color.WHITE);
        JButton button3 = new JButton("Rules");
        button3.addActionListener(new RulesButtonListener());
        button3.setForeground(Color.WHITE);

        dialog.getContentPane().setLayout(new FlowLayout());
        dialog.getContentPane().add(button1);
        dialog.getContentPane().add(button2);
        dialog.getContentPane().add(button3);
        dialog.getContentPane().setBackground(Color.black);
        dialog.setVisible(true);
    }

    private class ScoreBoardButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playSound("sounds/Menu.wav");
            infoScoreBoard();
        }
    }

    public void infoScoreBoard() {
        if (inforulesPanel != null){
            inforulesPanel.setVisible(false);
        }
        infoSBPanel = new JPanel(new GridLayout(5, 1));

        dialog.setSize(1200, 250);

        String textInfo1 = bg.printStats();
        JLabel infoSB1 = new JLabel(textInfo1);
        infoSBPanel.add(infoSB1);

        String textInfo2 = bg.totalScoreWinner();
        JLabel infoSB2 = new JLabel(textInfo2);
        infoSBPanel.add(infoSB2);

        String textInfo3 = bg.roundsWinner();
        JLabel infoSB3 = new JLabel(textInfo3);
        infoSBPanel.add(infoSB3);

        String textInfo4 = bg.littleBuncoWinner();
        JLabel infoSB4 = new JLabel(textInfo4);
        infoSBPanel.add(infoSB4);

        String textInfo5 = bg.bigBuncoWinner();
        JLabel infoSB5 = new JLabel(textInfo5);
        infoSBPanel.add(infoSB5);

        Font font = new Font("Agency FB", Font.BOLD, 22);
        infoSB1.setFont(font);
        infoSB2.setFont(font);
        infoSB3.setFont(font);
        infoSB4.setFont(font);
        infoSB5.setFont(font);

        infoSBPanel.setBackground(Color.black);
        infoSB1.setForeground(Color.LIGHT_GRAY);
        infoSB2.setForeground(Color.LIGHT_GRAY);
        infoSB3.setForeground(Color.LIGHT_GRAY);
        infoSB4.setForeground(Color.LIGHT_GRAY);
        infoSB5.setForeground(Color.LIGHT_GRAY);
        dialog.add(infoSBPanel);
        dialog.setVisible(true);
    }

    private class NewGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playSound("sounds/Menu.wav");

            mainB.dispose();
            main(null);

        }
    }

    private class RulesButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playSound("sounds/Menu.wav");
            inforules();
        }
    }

    public void inforules(){
        
        if (infoSBPanel != null){
            infoSBPanel.setVisible(false);
        }
        inforulesPanel = new JPanel(new GridLayout(5, 1));
        inforulesPanel.setBackground(Color.black);

        dialog.setSize(1300, 525);

        String text1 = "<html>AIM OF THE GAME: Win majority of the rounds by scoring 21 or more points, each player takes turns rolling the dice to score points.<br>HOW TO PLAY: Player1 is selected to go first in 1st ROUND, then turns go in a cyclical way.<br><br>If you are in ROUND 1, roll the dice:<br>You roll 1 # # -> + 1 POINT.<br>You roll 1 1 # -> + 2 POINTS.<br> You roll 1 1 1 -> BUNCO! + 21 POINTS, automatically win the round.<br>You roll THREE-OF-A-KIND that doesn't match the actual ROUND, like 4 4 4 in ROUND 1 -> LITTLE BUNCO! + 5 POINTS.<br><br>You continue rolling the dice as long as you keep scoring points.<br>If you don't roll a number that matches the actual round or is not a three-of-a-kind non-round number, then you forfeit your turn to the next player and you get + 0 POINTS.<BR><BR>GAME WINNER -> MOST ROUNDS WON.<BR>IF A TIE HAPPENS -> PLAYER WITH MOST BUNCOS WINS.<BR>IF IT'S STILL A TIE -> PLAYER WITH GRETEST TOTAL SCORE WINS.</html>";
        JLabel infoR1 = new JLabel(text1);
        inforulesPanel.add(infoR1);

        Font font = new Font("Agency FB", Font.BOLD, 22);
        infoR1.setFont(font);
        
        infoR1.setForeground(Color.LIGHT_GRAY);
        dialog.add(inforulesPanel);
        dialog.setVisible(true);
    }

    private void playSound(String soundFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(MainBunco.class.getResource(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainB = new MainBunco();
            }
        });
    }

}