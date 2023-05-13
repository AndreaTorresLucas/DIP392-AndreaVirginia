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

    private int round;
    private int actualPlayer;
    private int numberPlayers = 4;
    private JLabel[] playerLabels;
    private JLabel[] playerScoreDisplay;
    private JButton rollDiceButton;
    private JButton menuButton;

    // Constructor
    public MainBunco() {
        super("Bunco");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        round = 1;
        actualPlayer = 0;
        Player[] players = new Player[numberPlayers];
        BuncoGame bg = new BuncoGame(players);

        // Display of the names at the top of the screen
        JPanel playerPanel = new JPanel(new FlowLayout());
        String[] playerNames = { "Player1", "Player2", "Player3", "Player4" }; // we need the string names
        playerLabels = new JLabel[numberPlayers];
        BoxLayout spacesNames = new BoxLayout(playerPanel, BoxLayout.X_AXIS);
        Font font = new Font("Bauhaus 93", Font.PLAIN, 24);
        playerPanel.setLayout(spacesNames);
        for (int i = 0; i < numberPlayers; i++) {
            playerLabels[i] = new JLabel(playerNames[i]);
            playerPanel.add(playerLabels[i]);
            playerPanel.add(Box.createHorizontalStrut(200)); 
            playerLabels[i].setFont(font); // Configuración del texto
            repaint();
        }
        playerPanel.setBackground(Color.LIGHT_GRAY);
        add(playerPanel, BorderLayout.NORTH);
         // Creación del borde
         Border border = BorderFactory.createLineBorder(Color.BLACK, 2, true);
         // Aplicar el borde al panel
         playerPanel.setBorder(border);


        setLayout(new GridLayout(2, 1));

        // Display of the points
        JPanel scoresDisplay = new JPanel(new FlowLayout());
        BoxLayout spaces = new BoxLayout(scoresDisplay, BoxLayout.X_AXIS);
        scoresDisplay.setLayout(spaces);
        playerScoreDisplay = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            playerScoreDisplay[i] = new JLabel("0");
            scoresDisplay.add(playerScoreDisplay[i]);
            scoresDisplay.add(Box.createHorizontalStrut(200));
        }
        add(scoresDisplay);
        // Button of rolling the dice
        rollDiceButton = new JButton("Roll the dice");
        rollDiceButton.addActionListener(new RollButtonListener());
        Color lightBlue = new Color(173, 216, 230);
        scoresDisplay.setBackground(lightBlue);
        add(rollDiceButton);
        

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
            // Simulación del giro de los dados y actualización de los resultados
            for (int i = 0; i < 4; i++) {
                int rollResult = (int) (Math.random() * 6) + 1;
                // TENEMOS QUE SACAR EL VALOR DEL DADO***********************************
                playerScoreDisplay[i].setText("" + rollResult);
            }
        }
    }

     // ActionListener para el botón Menu
     private class MenuButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Lógica para mostrar el menú con los botones new game, scoreboard y rules
            // Aquí puedes implementar la lógica para mostrar las vistas correspondientes a cada opción del menú
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
