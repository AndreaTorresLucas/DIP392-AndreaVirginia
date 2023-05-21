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
    ImageIcon[] diceIcons;
    private int[] dataDice;

    // Constructor
    public MainBunco() {
        super("BuncoGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // personalized icon window
                    ImageIcon iconn = new ImageIcon("images/iconbg.png");

                    // Obtener una instancia de la clase Image
                    java.awt.Image imagee = iconn.getImage();
            
                    // Establecer el icono personalizado en el JFrame
                    setIconImage(imagee);

        // setLayout(new BorderLayout());
        setLayout(new GridLayout(4, 1));

            

        // Display of the names at the top of the screen
        playerPanel = new JPanel(new GridLayout(2, 4));
        String[] playerNames = { "Player1", "Player2", "Player3", "Player4" }; // we need the string names
        playerLabels = new JLabel[numberPlayers];
        Font font = new Font("Bauhaus 93", Font.PLAIN, 50);
        for (int i = 0; i < numberPlayers; i++) {
            playerLabels[i] = new JLabel(playerNames[i]);

            playerPanel.add(playerLabels[i]);
            playerLabels[i].setFont(font); // Configuración del texto
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
        // Creación del borde
        Border border = BorderFactory.createLineBorder(Color.BLACK, 8,true);
        // Aplicar el borde al panel
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
        //Color lightBlue = new Color(173, 216, 230);
        //Color forestGreen = Color.decode("#145A32");
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

        // Panel para el botón del menú
        JPanel menuPanel = new JPanel();

        menuButton = new JButton("Menu");
        menuButton.addActionListener(new MenuButtonListener());
        menuPanel.add(menuButton);
        darkRed = Color.decode("#000033");
        UIManager.put("Button.background", darkRed);
        UIManager.put("Button.font", new Font("Bauhaus 93", Font.PLAIN, 30));

        // Aplicar el estilo personalizado al botón
        menuButton.setBackground(UIManager.getColor("Button.background"));
        menuButton.setFont(UIManager.getFont("Button.font"));
        menuButton.setForeground(Color.WHITE);
        menuPanel.setBackground(Color.BLACK);
        add(menuPanel, BorderLayout.SOUTH);


        // for showing the actual display
        pack();
        setVisible(true);

        // Ruta de las imágenes de las caras del dado
String[] diceImages = { "images/dice1.png", "images/dice2.png", "images/dice3.png", "images/dice4.png", "images/dice5.png", "images/dice6.png"};

// Array para almacenar los iconos de las imágenes
diceIcons = new ImageIcon[diceImages.length];

// Cargar las imágenes y crear los iconos
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

                if (players[i].roundsWon()) {playSound("sounds/Rollingdice.wav");
                    playerScorePoints[0].setText("Points: " + players[0].getPoints());
                    playerScorePoints[1].setText("Points: " + players[1].getPoints());
                    playerScorePoints[2].setText("Points: " + players[2].getPoints());
                    playerScorePoints[3].setText("Points: " + players[3].getPoints());
                    for (int j = 0; j < 3; j++) {
                        for(int i =1;i<7;i++){
                           if(dataDice[j]==i){playerScoreDisplay[j].setIcon(diceIcons[i-1]);} 
                        }
                      
                    }
                    playSound("sounds/win.wav");
                    numberRounds.setText("<html><br>" +players[i].getName() + " has won this round " + round + "</html>");
                    bg.addRound();
                    for (int j = 0; j < players.length; j++) {
                        players[j].resetPoints();
                    }
                } else {

                    int pointsPerPlayer = players[i].getScore1Player();
                    numberRounds.setText("<html><br>Round: " + round + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Turn: " + players[i].getName()
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Scored points: " + pointsPerPlayer+"</html>");
                    playSound("sounds/Rollingdice.wav");
                    playerScorePoints[0].setText("Points: " + players[0].getPoints());
                    playerScorePoints[1].setText("Points: " + players[1].getPoints());
                    playerScorePoints[2].setText("Points: " + players[2].getPoints());
                    playerScorePoints[3].setText("Points: " + players[3].getPoints());
                    dataDice = diceGame.rollingDice;
                    for (int j = 0; j < 3; j++) {
                        for(int i =1;i<7;i++){
                           if(dataDice[j]==i){playerScoreDisplay[j].setIcon(diceIcons[i-1]);} 
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

    // ActionListener para el botón Menu
    private class MenuButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playSound("sounds/Menu.wav");
            // Lógica para mostrar el menú con los botones new game, scoreboard y rules
            // Aquí puedes implementar la lógica para mostrar las vistas correspondientes a
            // cada opción del menú
            JOptionPane.showMessageDialog(null, "Menu options: new game, scoreboard, rules");
        }
    }


     private void playSound(String soundFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(MainBunco.class.getResource(soundFilePath));
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
                new MainBunco();
            }
        });
    }

}