import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MainBunco extends JFrame {

    private int numberPlayers = 4;
    private JLabel[] playerLabels;
    private JLabel[] playerScoreDisplay;
    private JLabel numberRounds;
    private JButton rollDiceButton;
    private JButton menuButton;
    private Player[] players;
    private BuncoGame bg;
    private RollButtonListener rollButtonListener;
    private int i;
    private int round;
    private int count;

    // Constructor
    public MainBunco() {
        super("Bunco");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLayout(new BorderLayout());
        setLayout(new GridLayout(4, 1));

        // Display of the names at the top of the screen
        JPanel playerPanel = new JPanel(new FlowLayout());
        // playerPanel.setPreferredSize(new Dimension(400, 100));
        String[] playerNames = { "Player1", "Player2", "Player3", "Player4" }; // we need the string names
        playerLabels = new JLabel[numberPlayers];
        BoxLayout spacesNames = new BoxLayout(playerPanel, BoxLayout.X_AXIS);
        Font font = new Font("Bauhaus 93", Font.PLAIN, 50);
        playerPanel.setLayout(spacesNames);
        for (int i = 0; i < numberPlayers; i++) {
            playerLabels[i] = new JLabel(playerNames[i]);
            playerPanel.add(playerLabels[i]);
            playerPanel.add(Box.createHorizontalStrut(200));
            playerLabels[i].setFont(font); // Configuración del texto

        }
        playerPanel.setBackground(Color.LIGHT_GRAY);
        add(playerPanel, BorderLayout.NORTH);
        // Creación del borde
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3, true);
        // Aplicar el borde al panel
        playerPanel.setBorder(border);

        // Display of the points
        JPanel scoresDisplay = new JPanel(new GridLayout(1, 5));
        scoresDisplay.setPreferredSize(new Dimension(200, 100));
        // BoxLayout spaces = new BoxLayout(scoresDisplay, BoxLayout.X_AXIS);
        // scoresDisplay.setLayout(spaces);
        playerScoreDisplay = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            playerScoreDisplay[i] = new JLabel("0");
            scoresDisplay.add(playerScoreDisplay[i]);
            // scoresDisplay.add(Box.createHorizontalStrut(200));
        }
        // add(scoresDisplay);


        // Button of rolling the dice
        rollDiceButton = new JButton("Roll the dice");
        rollDiceButton.addActionListener(new RollButtonListener());
        Color lightBlue = new Color(173, 216, 230);
        scoresDisplay.setBackground(lightBlue);
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
     
        numberRounds = new JLabel("Roll the dice to start the game, GOOD LUCK!!");
        Font font1 = new Font("Bauhaus 93", Font.PLAIN, 50);
        numberRounds.setFont(font1);
        roundplayer.add(numberRounds);
        numberRounds.setHorizontalAlignment(SwingConstants.CENTER);
        add(roundplayer);
        count = 0;
        bg.addRound();
       /*  
        round = bg.getRound();
        
        
        
        int count = 0;
        i = count % players.length;// i is never going to be bigger than 4, represents the number of the player

        while (round < 7) {
            System.out.println("Hola");
            round++;
        }*/
        /* 
        
        

        while (round < 6 + 1) {
    
            String nameActualPlayer = players[i].getName();
            JLabel numberRounds = new JLabel("Round" + round + "           Turn: " + nameActualPlayer);
            System.out.println("Round" + round + "           Turn: " + nameActualPlayer);
            numberRounds.setHorizontalAlignment(SwingConstants.CENTER);
            add(numberRounds);
            //indexActualPlayer = i;

           
            while (players[i].turn(round)){
                rollDiceButton.addActionListener(new RollButtonListener());
            }
            if (players[i].roundsWon()) {

                //System.out.println(sb.printStats());

                bg.addRound();
            }
            count++;
        }
        */ 


        // Panel para el botón del menú
        JPanel menuPanel = new JPanel();

        menuButton = new JButton("Menu");
        menuButton.addActionListener(new MenuButtonListener());
        menuPanel.add(menuButton);
        Color darkRed = new Color(139, 0, 0);
        UIManager.put("Button.background", darkRed);
        UIManager.put("Button.font", new Font("Bauhaus 93", Font.PLAIN, 16));

        // Aplicar el estilo personalizado al botón
        menuButton.setBackground(UIManager.getColor("Button.background"));
        menuButton.setFont(UIManager.getFont("Button.font"));
        menuButton.setForeground(Color.WHITE);

        add(menuPanel, BorderLayout.SOUTH);



        // for showing the actual display
        pack();
        setVisible(true);

        
    }

     private class RollButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            i = count % players.length;// i is never going to be bigger than 4, represents the number of the player
            round = bg.getRound();
            if(round<7){
                numberRounds.setText("Round: " + round + "           Turn: " + players[i]); 
                while(players[i].turn(round)){}
                    if(players[i].roundsWon()){
                        bg.addRound();
                        for(int j =0;j<players.length;j++){
                            players[j].resetPoints();
                        }
                    }
                    count++;
                    System.out.println(count);
            }else{numberRounds.setText("END OF GAME");}
            /*Dice diceGame = bg.dice;
            int[] diceValues = diceGame.rollingDice;
                        
            // Simulación del giro de los dados y actualización de los resultados
                        
            for (int j = 0; j < 3; j++) {
            int rollResult = diceValues[j];
            System.out.print(rollResult);
            // TENEMOS QUE SACAR EL VALOR DEL DADO***********************************
           playerScoreDisplay[j].setText("" + rollResult);     //las 3 labels en el display
            } */
            
            


          
        }
    }

    // ActionListener para el botón Menu
    private class MenuButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Lógica para mostrar el menú con los botones new game, scoreboard y rules
            // Aquí puedes implementar la lógica para mostrar las vistas correspondientes a
            // cada opción del menú
            JOptionPane.showMessageDialog(null, "Menu options: new game, scoreboard, rules");
        }
    }

    /*
     * static void getNamesInput(Player[] players, BuncoGame bg){
     * int countPlayer = 0;
     * boolean duplicated = false;
     * while(countPlayer < players.length){
     * duplicated = false;
     * }
     * 
     * }
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainBunco();
            }
        });
    }

}