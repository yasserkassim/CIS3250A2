package pokedefence;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Justin
 */
public class PokeDefence {
	
    /********************Variables***********************/
    private static int[][] Towers = new int[4][275];
    private static int waveCount=0;
    private static int score=0;
    private static int lives = 10;
    private static int gold = 375;
    private static int totalGold=0;
    private static int mouseClickCountOne=0;
    private static int mouseClickCountTwo=0;
    private static int mouseClickCountThree=0;
    private static String[][] mapLayout = new String[11][26];
    private static int count=0;
    private static int tick=0;
    private static int nextIndex = randIndex();
    private static int index = nextIndex;
    private static String playerName="";
    private static int winCondition=0;
    private static int mapPreviewCounter=0;
    private static JLabel mapPreviewImage = new JLabel();
    private static String[] mapImagePath = {"./Images/Maps/map1.png", "./Images/Maps/map2.png", "./Images/Maps/map3.png"};
    private static String[] mapFilePath = {"./Images/Maps/map1.txt", "./Images/Maps/map2.txt", "./Images/Maps/map3.txt"};
    private static int[] enemyHealth = {100,85,150,90,76};
    /****************End of Variables********************/
    
    /****************Main Window Items*******************/
    private static JFrame mainWindow = new JFrame("Tower Defense");
    private static JLabel mainTitle = new JLabel();
    private static JLabel towerImage = new JLabel();
    private static JLabel enemyImage = new JLabel();
    private static JLabel enemyImageIcon = new JLabel();
    private static JButton playButton = new JButton("Play");
    private static JButton infoButton = new JButton("Info");
    private static JButton quitButton = new JButton("Quit");//Used in end-game screen also
    /*************End Main Window Items******************/
    
    /**************Game Options Window Items******************/
    private static JFrame gameOptionsWindow = new JFrame("Options");
    private static JLabel mapSelectionTitle = new JLabel("---Select a Map---", JLabel.CENTER);
    private static JPanel mapPreview = new JPanel();
    private static JLabel mapPreviewLabel = new JLabel();
    private static JButton mapPreviewNext = new JButton();
    private static JButton mapPreviewPrevious = new JButton();
    private static JTextArea mapDescription = new JTextArea();
    private static JButton backButton = new JButton("Back");
    private static JButton beginGameButton = new JButton("Begin Game");
    private static JLabel image = new JLabel();
    /***********End Game Options Window Items*****************/
    
    /*************In-Game Window Items******************/
    private static JFrame gridWindow = new JFrame(); 
    private static JLabel currentCurrency = new JLabel();
    private static JLabel livesLeft = new JLabel();
    private static JLabel currentScore = new JLabel();
    //private static JButton pauseGame = new JButton("Pause Game");
    private static JPanel currentEnemies = new JPanel();
    private static JPanel nextWaveEnemies = new JPanel();
    private static JTextArea currentEnemiesText = new JTextArea();
    private static JTextArea nextWaveText = new JTextArea();
    private static JButton towerOne = new JButton("Archer Tower - $150");
    private static JButton towerTwo = new JButton("Cannon Tower - $300");
    private static JButton towerThree = new JButton("Mage Tower - $500");
    private static JPanel topBorder = new JPanel();
    private static ArrayList<JPanel> battleField = new ArrayList<>();
    private static ArrayList<Integer> enemyPath = new ArrayList<>();
	
    private static ArrayList<JButton> tower3 = new ArrayList<>();
    private static ArrayList<JButton> tower2 = new ArrayList<>();
    private static ArrayList<JButton> tower1 = new ArrayList<>();
    /**********End of In-Game Window Items**************/
    
    
    /**********Info Window Items**************/
    private static JFrame gameInfoWindow = new JFrame("Information about our Game");
    private static JTextArea gameInfo = new JTextArea();
    /********End of Info Window Items*********/
    
    
    /************End-Game Window Items******************/
    private static JFrame endGameScreen = new JFrame();
    private static JPanel winOrLose = new JPanel();
    private static JLabel winLabel = new JLabel();
    private static JLabel loseLabel = new JLabel();
    private static JLabel gameStatsBanner = new JLabel();
    private static JTextArea gameStats = new JTextArea();
    private static JLabel highScoreBanner = new JLabel();
    private static JTextArea highScoreDisplay = new JTextArea();
    private static ArrayList<String> highScoreList = new ArrayList();
    private static JButton returnToMenu = new JButton("Return to Menu");
    //Use quit button predefined above in this window.

    /**********End of End-Game Window Items*************/
    
    /****************Mobs/Towers************************/
    private static ArrayList<String> mobs = new ArrayList<>();
    private static ArrayList<String> towers = new ArrayList<>();
    /*************End Mobs/Towers***********************/
    
    
    
    
    public static void  main(String[] args) throws IOException{
        createMainWindow();
        //createInfoWindow();
        //createGameOptionsWindow();
        //createInGameWindow();
        //createEndGameWindow();
        //readMapIn();
        //getHighScores();
        //writeNewHighScores();

        
        
        //Damage, range, cost, fire speed
        towers.add("25, 6, 150, 2"); //index 0
        towers.add("55, 4, 300, 1");
        towers.add("80, 3, 500, 4");

        //Health, Speed, Defence, Gold
        mobs.add("50, 2, 5, 25");
        mobs.add("25, 4, 2, 15");
        mobs.add("150, 1, 10, 75");
        mobs.add("500, 2, 15, 200");
        mobs.add("2000, 1, 50, 2000");
        
        int x = 0;
        //towers.set(x, towerUpgrade(towers.get(x)));
        
    }

    //I took this as a method to get the sell price of a tower where after it is called then you can delete said tower
    public static int towerSell(String currentTower){
        //Would use it like setGold(towerSell(towers.get(x))) then remove the tower
        String Tokens[];
        int sellPrice;
        //Splits the tower string into tokens to get the value of the tower
        Tokens = currentTower.split(", ");
        //Gets the sell value of the tower
        sellPrice = Integer.parseInt(Tokens[1]);
        sellPrice = (int) (sellPrice * 0.5);
        

        return sellPrice;
    }
    
    //I took this as a method to deal damage to a mob with a tower by first getting it's damage, then minusing the mobs health by said amount
    public static String towerShot(String currentTower, String currentMob){
        String Tokens[];
        //Splits the tower string into tokens to get the damage of the tower
        Tokens = currentTower.split(", ");
        //Gets the damage of the tower
        int Damage = Integer.parseInt(Tokens[0]);
        Tokens = currentMob.split(", ");
        int newHealth = Integer.parseInt(Tokens[0]) - Damage;
        
        //Create the new mob string with updated health
        String updatedMob = "" + newHealth;
        for (int i = 1; i < Tokens.length;i++){
            updatedMob = updatedMob + Tokens[i] + ", ";
        }
        
        return updatedMob;
    }

    
    //Takes in the mob array and checks if any have their HP below 0
    public static int mobKilled(String[] mob){
        for(int i = 0;i<mob.length;i++){
            //Condition that one of the mobs hp is 0 or below
            int hp;
            //Changes the mob info from string into an integer value for hp
            if(mob[i].charAt(1) == ',') {
                hp = Integer.parseInt("" + mob[i].charAt(0));
            }
            else {
                hp = Integer.parseInt("" + mob[i].charAt(0) + mob[i].charAt(1));
            }
            
            if(hp <= 0){
                //Returns the mob that is dead
                return i;
            }
        }
        return -1;
    }

    public static void collision(){
        
    }
	/***********************Menus Functions Start Here***********************/
    /********************************************************************************
    * createMainWindow
    * This function creates the Main Menu
    *******************************************************************************/
    private static void createMainWindow(){
        endGameScreen.dispose();
        gameOptionsWindow.dispose();
        gameInfoWindow.dispose();
        
        //Frame
        mainWindow.setLayout(null);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 3, (screenSize.height / 4) - 80);
        Point newLocation = new Point(middle.x - (mainWindow.getWidth() / 2+225), middle.y - (mainWindow.getHeight() / 2));
        mainWindow.setSize(1016,600);
        mainWindow.setLocation(newLocation);
        mainWindow.setVisible(true);
        
        //Panel
        mainTitle.setSize(400,150);
        mainTitle.setLocation(275,20);
        mainTitle.setVisible(true);
        mainTitle.setIcon(new ImageIcon("./Images/MainMenu/Logo2.gif"));
        mainWindow.add(mainTitle);
        
        //Panel
        towerImage.setSize(250,400);
        towerImage.setLocation(20,85);
        towerImage.setIcon(new ImageIcon("./Images/MainMenu/tower.gif"));
        mainWindow.add(towerImage);
        
        //Panel
        enemyImage.setSize(296,330);
        enemyImage.setLocation(700,165);
        enemyImage.setIcon(new ImageIcon("./Images/MainMenu/enemy.gif"));
        //enemyImage.add(enemyImageIcon);
        //enemyImage.setBackground(Color.red);
        mainWindow.add(enemyImage);
        mainWindow.repaint();
        
        //Button
        playButton.setSize(100,40);
        playButton.setLocation(420,250);
        playButton.setVisible(true);
        Action changeWindow = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                createGameOptionsWindow();
            }
        };
        playButton.addActionListener(changeWindow);
        mainWindow.add(playButton);
        
        //Button
        infoButton.setSize(100,40);
        infoButton.setLocation(420,300);
        infoButton.setVisible(true);
        Action gotoInfoWindow = new AbstractAction(""){
            public void actionPerformed(ActionEvent e){
                createInfoWindow();
            }
        };
        infoButton.addActionListener(gotoInfoWindow);
        mainWindow.add(infoButton);
        
        //Button
        quitButton.setSize(100,40);
        quitButton.setLocation(420,350);
        quitButton.setVisible(true);
        Action closeProgram = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        quitButton.addActionListener(closeProgram);
        mainWindow.add(quitButton);
        
        mainWindow.repaint();
        
    }
    /********************************************************************************
    * createGameOptionsWindow
    * This function creates the avaliable Map options before starting the game
    *******************************************************************************/
    private static void createGameOptionsWindow(){
        mainWindow.dispose();
        
        mapPreviewImage.setIcon(new ImageIcon(mapImagePath[mapPreviewCounter]));
        
        gameOptionsWindow.setLayout(null);
        gameOptionsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOptionsWindow.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 3, (screenSize.height / 4) - 80);
        Point newLocation = new Point(middle.x - (gameOptionsWindow.getWidth() / 2+225), middle.y - (gameOptionsWindow.getHeight() / 2));
        gameOptionsWindow.setSize(1016,600);
        gameOptionsWindow.setLocation(newLocation);
        gameOptionsWindow.setVisible(true);
        
        
        mapSelectionTitle.setSize(400,150);
        mapSelectionTitle.setLocation(300,20);
        mapSelectionTitle.setVisible(true);
        mapSelectionTitle.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        gameOptionsWindow.add(mapSelectionTitle);
        
        
        mapPreview.setSize(150,150);
        mapPreview.setLocation(150,250);
        mapPreviewLabel.setIcon(new ImageIcon(mapImagePath[mapPreviewCounter]));
        mapPreview.setVisible(true);
        mapPreview.add(mapPreviewLabel);
        
        gameOptionsWindow.add(mapPreview);
        
        mapPreviewNext.setSize(50,145);
        mapPreviewNext.setLocation(300,255);
        mapPreviewNext.setText(">");
        mapPreviewNext.setVisible(true);
        
        Action previewNext = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                previewCycler(1);
            }
        };
        mapPreviewNext.addActionListener(previewNext);
        
        
        gameOptionsWindow.add(mapPreviewNext);
        
        mapPreviewPrevious.setSize(50,145);
        mapPreviewPrevious.setLocation(100,255);
        mapPreviewPrevious.setText("<");
        mapPreviewPrevious.setVisible(true);
        
        Action previewPrevious = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                previewCycler(-1);
            }
        };
        mapPreviewPrevious.addActionListener(previewPrevious);
        
        gameOptionsWindow.add(mapPreviewPrevious);
        Color lightGray=new Color(238,238,238);
        mapDescription.setSize(400,150);
        mapDescription.setLocation(500,250);
        mapDescription.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        mapDescription.setEditable(false);
        mapDescription.setLineWrap(true);
        mapDescription.setWrapStyleWord(true);
        mapDescription.setBackground(lightGray);
        mapDescription.setText("This map has a medium diffculty because it has a few turns and provides a slightly easier game than map two.");
        mapDescription.setVisible(true);
        gameOptionsWindow.add(mapDescription);
        
        backButton.setSize(150,40);
        backButton.setLocation(325,500);
        backButton.setVisible(true);
        Action gotoMainWindow = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                createMainWindow();
            }
        };
        backButton.addActionListener(gotoMainWindow);
        gameOptionsWindow.add(backButton);
        
        beginGameButton.setSize(150,40);
        beginGameButton.setLocation(525,500);
        beginGameButton.setVisible(true);
        Action startGame = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                try {
                    createInGameWindow();
                    gameStart();
                } catch (IOException ex) {
                    System.exit(0);
                }
            }
        };
        beginGameButton.addActionListener(startGame);
        gameOptionsWindow.add(beginGameButton);
    }
    /********************************************************************************
    * createInfoWindow
    * This function creates the information window
    *******************************************************************************/
    private static void createInfoWindow(){
        mainWindow.dispose();

        gameInfoWindow.setLayout(null);
        gameInfoWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameInfoWindow.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 3, (screenSize.height / 4) - 80);
        Point newLocation = new Point(middle.x - (gameInfoWindow.getWidth() / 2 + 225), middle.y - (gameInfoWindow.getHeight() / 2));
        gameInfoWindow.setSize(1016, 600);
        gameInfoWindow.setLocation(newLocation);
        gameInfoWindow.setVisible(true);

        gameInfo.setSize(800, 400);
        gameInfo.setLocation(100, 20);
        gameInfo.setEditable(false);
        gameInfo.setLineWrap(true);
        gameInfo.setWrapStyleWord(true);
        Color lightGrey = new Color(238,238,238);
        gameInfo.setBackground(lightGrey);
        gameInfo.setText("Game Name: POKETOWER\n\n"
                + "Description: \n      This is a Tower Defense game in which the enemy Pokemon are trying to attack the player's base."
                + " They follow the set path and are being attacked by towers placed by the user trying to defend their base.\n\n"
                + "The win condition is passing 11 waves.\n\n"
                + "Authors:\n"
                + "   Anton Nosov\n"
                + "   Vincent Yong\n"
                + "   Justin Gruber\n"
                + "   Josiah Menezes\n"
                + "   Santiago Florez\n"
                + "   Yasser Kassim\n"
                + "   Alex Newton\n"
                + "   Andrew Macaulay\n\n"
                + "Course: CIS*3250\n"
                + "Professor: David Calvert");
        gameInfo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        gameInfo.setVisible(true);
        gameInfoWindow.add(gameInfo);

        //returns user to previous screen
        backButton.setSize(150, 40);
        backButton.setLocation(400, 500);
        backButton.setVisible(true);
        Action gotoMainWindow = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                createMainWindow();
            }
        };
        backButton.addActionListener(gotoMainWindow);
        gameInfoWindow.add(backButton);
        gameInfoWindow.repaint();
    }
    /********************************************************************************
    * createInGameWindow
    * This function creates the main map display. The game is displayed on this screen.
    *******************************************************************************/
    private static void createInGameWindow() throws IOException{
        gameOptionsWindow.dispose();
        readMapIn();
        
        //Frame
        gridWindow.setLayout(null);
        gridWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridWindow.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 3, (screenSize.height / 4) - 80);
        Point newLocation = new Point(middle.x - (gridWindow.getWidth() / 2+225), middle.y - (gridWindow.getHeight() / 2));
        gridWindow.setSize(1016,600);
        gridWindow.setLocation(newLocation);
        gridWindow.setVisible(true);
        
        //Label
        currentCurrency.setSize(150,50);
        currentCurrency.setLocation(50,0);
        currentCurrency.setText("Gold: " + getGold());
        currentCurrency.setVisible(true);
        gridWindow.add(currentCurrency);
        
        //Label
        livesLeft.setSize(150,50);
        livesLeft.setLocation(150,0);
        livesLeft.setText("Lives: " + getLives());
        gridWindow.add(livesLeft);
        
        //Label
        currentScore.setSize(100,80);
        currentScore.setLocation(250,-15);
        currentScore.setText("Score: " + getScore());
        currentScore.setVisible(true);
        gridWindow.add(currentScore);
        
        //Label
        topBorder.setSize(1000,50);
        topBorder.setLocation(0,0);
        topBorder.setBackground(Color.ORANGE);
        topBorder.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        gridWindow.add(topBorder);
        
        
        for(int i=0;i<26;i++){
            for(int j=0;j<11;j++){
                battleField.add(new JPanel());
                battleField.get(count).setSize(40,40);
                battleField.get(count).setLocation((i*40)-40,(j*40)+50);
                gridWindow.add(battleField.get(count));

                tower1.add(new JButton());
                tower1.get(count).setSize(40, 40);
                tower1.get(count).setLocation((i * 40) - 40, (j * 40) + 50);
                gridWindow.add(tower1.get(count));
                tower1.get(count).setVisible(false);
                tower1.get(count).setEnabled(false);

                tower2.add(new JButton());
                tower2.get(count).setSize(40, 40);
                tower2.get(count).setLocation((i * 40) - 40, (j * 40) + 50);
                gridWindow.add(tower2.get(count));
                tower2.get(count).setVisible(false);
                tower2.get(count).setEnabled(false);

                tower3.add(new JButton());
                tower3.get(count).setSize(40, 40);
                tower3.get(count).setLocation((i * 40) - 40, (j * 40) + 50);
                gridWindow.add(tower3.get(count));
                tower3.get(count).setVisible(false);
                tower3.get(count).setEnabled(false);

                if (mapLayout[j][i].equals("0")) {
                    //JLabel number = new JLabel();
                    //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    battleField.get(count).setBackground(Color.blue); 
                    //battleField.get(count).add(number);
                    count++;
                } 
                else if (mapLayout[j][i].equals("1")) {
                    //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    battleField.get(count).setBackground(Color.gray);
                    enemyPath.add(count);
                    count++;
                }
                else if (mapLayout[j][i].equals("S")) {
                    //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    battleField.get(count).setBackground(Color.black);
                    enemyPath.add(count);
                    count++;
                }
                else if (mapLayout[j][i].equals("F")) {
                    //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    //battleField.get(count).setBackground(Color.red);
                    JLabel image = new JLabel();
                    image.setIcon(new ImageIcon("./Images/Towers/mainBase.png"));
                    
                    battleField.get(count).add(image);
                    enemyPath.add(count);
                    count++;
                }
            }
        }
        for(int i=0;i<275;i++){
          Towers[0][i]=-1;  
        }
        gridWindow.repaint();
        //Button
        //pauseGame.setSize(50,50);
        //pauseGame.setLocation(950,0);
        //gridWindow.add(pauseGame);

        //Panel
        currentEnemies.setSize(200,72);
        currentEnemies.setLocation(0,490);
        currentEnemies.setVisible(true);
        currentEnemies.setBackground(Color.orange);
        gridWindow.add(currentEnemies);
        
        //TextArea
        currentEnemiesText.setSize(160,42);
        currentEnemiesText.setLocation(30,520);
        currentEnemiesText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        currentEnemiesText.setVisible(true);
        currentEnemiesText.setBackground(Color.orange);
        gridWindow.add(currentEnemiesText);
        
        //Panel
        nextWaveEnemies.setSize(200,72);
        nextWaveEnemies.setLocation(200,490);
        nextWaveEnemies.setVisible(true);
        nextWaveEnemies.setBackground(Color.red);
        gridWindow.add(nextWaveEnemies);
        
        //TextArea
        nextWaveText.setSize(160,42);
        nextWaveText.setLocation(240,520);
        nextWaveText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nextWaveText.setVisible(true);
        nextWaveText.setBackground(Color.red);
        gridWindow.add(nextWaveText);
        
        //Button
        towerOne.setSize(200, 72);
	towerOne.setLocation(400, 490);
        Action towerOneLocation = new AbstractAction("") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 275; i++) {
                    if ((JButton) e.getSource() == tower1.get(i)) {
                        if(getGold()>=150){
                            int x1=0;
                            setGold(-150);
                            updateStats();
                            JLabel image = new JLabel();
                            image.setIcon(new ImageIcon("./Images/Towers/archerTower.jpg"));
                            battleField.get(i).add(image);

                            //battleField.get(i).setBackground(Color.ORANGE);

                            tower1.get(i).setEnabled(false);
                            tower1.get(i).setVisible(false);
                            int x = i / 11;
                            int y = i % 11;
                            while(Towers[0][x1]!=-1){
                                x1++;
                            }
                            Towers[0][x1] = y;
                            Towers[1][x1] = x + 1;
                            Towers[2][x1] = i;
                            Towers[3][x1] = 25;
                            mapLayout[y][x + 1] = "1";
                            gridWindow.repaint();
                        }
                    }
                }
            }
        };
        count = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 11; j++) {
                tower1.get(count).addActionListener(towerOneLocation);
                count++;
            }
        }
        Action addTowerOne = new AbstractAction("") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseClickCountOne++;
                towerThree.setEnabled(false);
                towerTwo.setEnabled(false);

                if (mouseClickCountOne == 1) {
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //    battleField.get(count).setBackground(Color.GREEN);
                                tower1.get(count).setEnabled(true);
                                tower1.get(count).setVisible(true);

                                count++;
                            }
                            else if (mapLayout[j][i].equals("1")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //     battleField.get(count).setBackground(Color.gray);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("S")) {
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("F")) {
                                count++;
                            }
                        }
                    }
                    gridWindow.repaint();
                } else {
                    mouseClickCountOne = 0;
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //   battleField.get(count).setBackground(Color.blue);
                                tower1.get(count).setEnabled(false);
                                tower1.get(count).setVisible(false);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("1")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //   battleField.get(count).setBackground(Color.gray);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("S")) {
                                count++;
                            }
                            else if (mapLayout[j][i].equals("F")) {
                                count++;
                            }
                        }
                    }
                    towerThree.setEnabled(true);
                    towerTwo.setEnabled(true);
                    gridWindow.repaint();
                }
            }
        };
        towerOne.addActionListener(addTowerOne);

        gridWindow.add(towerOne);
        gridWindow.repaint();
        /*________________________________________________________________*/
        towerTwo.setSize(200, 72);
        towerTwo.setLocation(600, 490);
        Action towerTwoLocation = new AbstractAction("") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 275; i++) {
                    if ((JButton) e.getSource() == tower2.get(i)) {
                         if(getGold()>=300){
                            int x1=0;
                            setGold(-300);
                            updateStats();
                            JLabel image = new JLabel();
                            image.setIcon(new ImageIcon("./Images/Towers/cannonTower.jpg"));
                            battleField.get(i).add(image);
                            //battleField.get(i).setBackground(Color.PINK);
                            tower2.get(i).setEnabled(false);
                            tower2.get(i).setVisible(false);
                            int x = i / 11;
                            int y = i % 11;
                            while(Towers[0][x1]!=-1){
                                 x1++;
                             }
                             Towers[0][x1] = y;
                             Towers[1][x1] = x + 1;
                             Towers[2][x1] = i;
                             Towers[3][x1] = 55;
                             mapLayout[y][x + 1] = "1";
                            gridWindow.repaint();
                         }
                    }
                }
            }
        };
        count = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 11; j++) {
                tower2.get(count).addActionListener(towerTwoLocation);
                count++;
            }
        }
        Action addTowerTwo = new AbstractAction("") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseClickCountTwo++;
                towerOne.setEnabled(false);
                towerThree.setEnabled(false);

                if (mouseClickCountTwo == 1) {
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //    battleField.get(count).setBackground(Color.GREEN);
                                tower2.get(count).setEnabled(true);
                                tower2.get(count).setVisible(true);

                                count++;
                            }
                            else if (mapLayout[j][i].equals("1")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //     battleField.get(count).setBackground(Color.gray);
                                count++;
                            }
                            else if (mapLayout[j][i].equals("S")) {
                                count++;
                            }
                            else if (mapLayout[j][i].equals("F")) {
                                count++;
                            }
                        }
                    }
                    gridWindow.repaint();
                } else {
                    mouseClickCountTwo = 0;
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //   battleField.get(count).setBackground(Color.blue);
                                tower2.get(count).setEnabled(false);
                                tower2.get(count).setVisible(false);
                                count++;
                            } else if (mapLayout[j][i].equals("1")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //   battleField.get(count).setBackground(Color.gray);
                                count++;
                            } else if (mapLayout[j][i].equals("S")) {
                                count++;
                            } else if (mapLayout[j][i].equals("F")) {
                                count++;
                            }
                        }
                    }
                    towerOne.setEnabled(true);
                    towerThree.setEnabled(true);
                    gridWindow.repaint();
                }
            }
        };
        towerTwo.addActionListener(addTowerTwo);

        gridWindow.add(towerTwo);
        gridWindow.repaint();
        /* _________________________________________________________________________*/
        //Button
        towerThree.setSize(200, 72);
        towerThree.setLocation(800, 490);
        Action towerThreeLocation = new AbstractAction("") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 275; i++) {
                    if ((JButton) e.getSource() == tower3.get(i)) {
                        if(getGold()>=500){
                            int x1=0;
                            setGold(-500);
                            updateStats();
                            JLabel image = new JLabel();
                            image.setIcon(new ImageIcon("./Images/Towers/mageTower.png"));
                            battleField.get(i).add(image);
                            //battleField.get(i).setBackground(Color.BLACK);
                            tower3.get(i).setEnabled(false);
                            tower3.get(i).setVisible(false);
                            int x = i / 11;
                            int y = i % 11;
                            while (Towers[0][x1] != -1) {
                                x1++;
                            }
                            Towers[0][x1] = y;
                            Towers[1][x1] = x + 1;
                            Towers[2][x1] = i;
                            Towers[3][x1] = 80;

                        mapLayout[y][x + 1] = "1";
                        gridWindow.repaint();
                        }
                    }
                }
            }
        };
        count = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 11; j++) {
                tower3.get(count).addActionListener(towerThreeLocation);
                count++;
            }
        }
        Action addTowerThree = new AbstractAction("") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseClickCountThree++;
                towerOne.setEnabled(false);
                towerTwo.setEnabled(false);

                if (mouseClickCountThree == 1) {
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //    battleField.get(count).setBackground(Color.GREEN);
                                tower3.get(count).setEnabled(true);
                                tower3.get(count).setVisible(true);

                                count++;
                            }
                            else if (mapLayout[j][i].equals("1")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //     battleField.get(count).setBackground(Color.gray);
                                count++;
                            }
                            else if (mapLayout[j][i].equals("S")) {
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("F")) {
                                count++;
                            }
                        }
                    }
                    gridWindow.repaint();
                } 
                else {
                    mouseClickCountThree = 0;
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //   battleField.get(count).setBackground(Color.blue);
                                tower3.get(count).setEnabled(false);
                                tower3.get(count).setVisible(false);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("1")) {
                                //battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                //   battleField.get(count).setBackground(Color.gray);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("S")) {
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("F")) {
                                count++;
                            }
                        }
                    }
                    towerOne.setEnabled(true);
                    towerTwo.setEnabled(true);
                    gridWindow.repaint();
                }
            }
        };
        towerThree.addActionListener(addTowerThree);

        gridWindow.add(towerThree);
        gridWindow.repaint();
        /*____________________________________________________________*/
    }
     /********************************************************************************
     * createEndGameWindow
     * This function creates the screen the player sees after the game is over. It shows the
     * High Scores.
     *******************************************************************************/
    private static void createEndGameWindow(){
        gridWindow.dispose();
        Color lightGrey = new Color(238,238,238);
        //Frame
        endGameScreen.setLayout(null);
        endGameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endGameScreen.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 3, (screenSize.height / 4) - 80);
        Point newLocation = new Point(middle.x - (endGameScreen.getWidth() / 2+225), middle.y - (endGameScreen.getHeight() / 2));
        endGameScreen.setSize(1016,600);
        endGameScreen.setBackground(Color.black);
        endGameScreen.setLocation(newLocation);
        endGameScreen.setVisible(true);
        
        //Panel
        winOrLose.setSize(336,138);
        winOrLose.setLocation(315,20);
        winOrLose.setVisible(true);
        winOrLose.setBackground(Color.black);
        
        if(winCondition == 1){
            
            //Win Label
            winLabel.setIcon(new ImageIcon("./Images/EndGameScreen/win.jpg"));
            winLabel.setVisible(true);
            winOrLose.add(winLabel);
            endGameScreen.add(winOrLose);
        }
        else if(winCondition != 1){
            
            //Lose Label
            loseLabel.setIcon(new ImageIcon("./Images/EndGameScreen/lose.jpg"));
            loseLabel.setVisible(true);
            winOrLose.add(loseLabel);
            endGameScreen.add(winOrLose);
        }
        
        gameStatsBanner.setSize(471,80);
        gameStatsBanner.setLocation(80,75);
        gameStatsBanner.setIcon(new ImageIcon("./Images/EndGameScreen/Results.png"));
        gameStatsBanner.setVisible(true);
        endGameScreen.add(gameStatsBanner);
        
        
        //TextArea
        gameStats.setSize(300,300);
        gameStats.setLocation(50,150);
        gameStats.setBackground(lightGrey);
        gameStats.setText("Gold Earned: " + getTotalGold()
                +"\nLives Left: " + getLives()
                +"\nTotal Score: " + getScore());
        gameStats.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        endGameScreen.add(gameStats);
        
        
        //Button
        /*returnToMenu.setSize(250,50);
        returnToMenu.setLocation(350,360);
        Action returnToMain = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                createMainWindow();
            }
        };
        returnToMenu.addActionListener(returnToMain);
        endGameScreen.add(returnToMenu);
        */
        //Button
        quitButton.setSize(250,50);
        quitButton.setLocation(350,450);
        Action exitGame = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        quitButton.addActionListener(exitGame);
        endGameScreen.add(quitButton);
        endGameScreen.repaint();   
        
        getHighScores();
        compareScores();
    }
    /********************************************************************************
    * readMapIn
    * This function takes the provided .txt file input and puts it into an ArrayList
    * of coordinates that are used in the 'createInGameWindow' to draw the map.
    *******************************************************************************/
    private static void readMapIn() throws IOException{
        Scanner fileInput = null; 
        String input;
        
        String[][] grid = new String[11][26];
        int j=0;
        try{        
            fileInput =  new Scanner(new File(mapFilePath[mapPreviewCounter]));
             while (fileInput.hasNextLine()){
                input = fileInput.nextLine();
                String[] stringTokens = input.split("");
            
                for(int i = 0; i < stringTokens.length; i++){
                    mapLayout[j][i] = stringTokens[i];
                }
                j++;
            }
        }
        catch(FileNotFoundException ex){
            System.exit(0);
            //Maybe have popup stating error occured
        }
        finally{
            fileInput.close();
        }
    }
     /********************************************************************************
     * previewCycler
     * This is a helper function for the 'createGameOptionsWindow' which shows the images
     * of avaliable maps to the user.
     *******************************************************************************/
    private static void previewCycler(int cycleNumber){
        if (cycleNumber < 0){
            if (mapPreviewCounter == 0){
                mapPreviewCounter = 2;
            }
            else{
                mapPreviewCounter--;
            }
        }
        else if (cycleNumber > 0){
            if (mapPreviewCounter == 2){
                mapPreviewCounter = 0;
            }
            else{
                mapPreviewCounter++;
            }
        }
        
        mapPreview.remove(mapPreviewLabel);
        mapPreviewImage.setIcon(new ImageIcon(mapImagePath[mapPreviewCounter]));
        mapPreviewImage.setVisible(true);
        mapPreview.add(mapPreviewImage);
        
        // Linking map description to the map that is being previewed
        if (mapPreviewCounter == 0){
            mapDescription.setText("This map has a medium diffculty because it has a few turns and provides a slightly easier game than map two.");
        }
        else if (mapPreviewCounter == 1){
            mapDescription.setText("This map is the hardest map because it is a direct path from where the enemies spawn to where your base is.");
        }
        else if (mapPreviewCounter == 2){
            mapDescription.setText("This map is the easiest of the three maps due to having a longer path with more turns in it.");
        }
        else{
            mapDescription.setText("Error encountered.");
        }
    }
    /********************************************************************************
     * writeNewHighScores
     * This function writes a new High Score to a predefined .txt file when its called.
     *******************************************************************************/
    private static void writeNewHighScores(){
               
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("./Images/EndGameScreen/highScore.txt"));
            for(int i=0;i<highScoreList.size();i++){
                outputStream.println(highScoreList.get(i));
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error opening the file highScore.txt");
            System.exit(0);
        }
        outputStream.close();

    }
    /********************************************************************************
    * getHighScores
    * This function reads in High Scores from a predefined .txt file.
     *******************************************************************************/
    private static void getHighScores(){
        Scanner fileInput = null; 
        String input;
        
        //String[][] grid = new String[11][26];
        try{        
            int i=0;
            fileInput =  new Scanner(new File("./Images/EndGameScreen/highScore.txt"));
             while (fileInput.hasNextLine()){
                     input = fileInput.nextLine();
                     highScoreList.add(input);
                     i++;
            }
        }
        catch(FileNotFoundException ex){
            System.exit(0);
            //Maybe have popup stating error occured
        }
        finally{
            fileInput.close();
        }
    }
    /********************************************************************************
    * compareScores
    * This function compares all current High Scores to the new Score to see if it can
    * make on the list.
    *******************************************************************************/
    private static void compareScores(){
        endGameScreen.repaint();
        int check = 0;
        for(int i=0;i<highScoreList.size();i++){
            String[] info;
            String name;
            int score;
            
            info = highScoreList.get(i).split(" ");
            
            name = info[0];
            score = Integer.parseInt(info[1]);
            
            if(getScore() > score){
                getPlayerName();
                check = 1;
                break;
            }
        }
        if(check != 1){
            showHighScore();
        }
    }
    /********************************************************************************
    * showHighScores
    * This function displays the current High Scores on the screen
    *******************************************************************************/
    private static void showHighScore(){
        Color lightGrey = new Color(238,238,238);
        highScoreBanner.setSize(471,80);
        highScoreBanner.setLocation(700,75);
        highScoreBanner.setIcon(new ImageIcon("./Images/EndGameScreen/HighScores.png"));
        highScoreBanner.setVisible(true);
        endGameScreen.add(highScoreBanner);
        
        //Will go into the highscore compare function
        highScoreDisplay.setSize(200,300);
        highScoreDisplay.setLocation(700,150);
        highScoreDisplay.setBackground(lightGrey);
        highScoreDisplay.setText("1) " + highScoreList.get(0) 
                +"\n2) " + highScoreList.get(1)
                +"\n3) " + highScoreList.get(2)
                +"\n4) " + highScoreList.get(3)
                +"\n5) " + highScoreList.get(4));
        highScoreDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        endGameScreen.add(highScoreDisplay);
        
        endGameScreen.repaint();
    }
    /********************************************************************************
    * getPlayerName
    * This function prompts the user to enter his/her name if they got a High Score
    *******************************************************************************/
    private static void getPlayerName(){
        final JFrame playerNameWindow = new JFrame();
        playerNameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        playerNameWindow.pack();
        playerNameWindow.setLocation(555, 275);
        playerNameWindow.setSize(250, 250);
        playerNameWindow.setLayout(null);
        playerNameWindow.setVisible(true);
        
        
        JLabel enterNamePrompt = new JLabel("You got a high-score!");
        enterNamePrompt.setSize(250,50);
        enterNamePrompt.setLocation(60,10);
        enterNamePrompt.setVisible(true);
        playerNameWindow.add(enterNamePrompt);
        
        final JTextField nameField = new JTextField();
        nameField.setEditable(true);
        nameField.setText("Enter your name.");
        
        nameField.setSize(160,50);
        nameField.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e){
            nameField.setText("");
        }});
        
        nameField.setLocation(playerNameWindow.getWidth()/3-37, (playerNameWindow.getHeight() / 3) - 25);
        nameField.setVisible(true);
        playerNameWindow.add(nameField);
        
        JButton addName = new JButton("Accept");
        addName.setLocation((playerNameWindow.getWidth()/3)-10, (playerNameWindow.getHeight() / 3) + 50);
        addName.setSize(new Dimension(100, 25));
       
        Action setName = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText();
                String[] temp;
                for(int i=0;i<highScoreList.size();i++){
                    temp = highScoreList.get(i).split(" ");
                    if(getScore() > (Integer.parseInt(temp[1]))){
                        highScoreList.add(i,playerName + " " + getScore());
                        highScoreList.remove(highScoreList.size()-1);
                        writeNewHighScores();
                        break;
                    }
                }
                showHighScore();
                playerNameWindow.dispose();
            }
        };

        addName.addActionListener(setName);
        addName.setVisible(true);
        playerNameWindow.add(addName);
        
    }
    /********************************************************************************
    * rangeFinder
    * This function finds the range to the monster from all the towers and returns damage
    *******************************************************************************/
    public static int rangeFinder(){
       count=0;
       int enemy = enemyPath.get(tick);
       int ex=enemy%11;
       int ey=(enemy/11);
       int dx, dy, tx=0, ty=0, damage=0;
       while(Towers[0][count]!=-1){
           tx=Towers[0][count];
           ty=Towers[1][count];
           dx=0;
           dy=0;
           dx=(int)Math.floor((Math.abs(ex-tx)));
           dy=(int)Math.floor((Math.abs(ey-ty)));
           
           if((int)Math.sqrt((dx*dx)+(dy*dy))==1){
               damage = Towers[3][count];
           }
           count++;
       }
        return damage;
    }
    
    public static int getScore() {
        return score;
    }

    public static void setScore(int newscore) {
        score += newscore;
    }

    public static int getLives() {
        return lives;
    }

    public static void setLives(int newlives) {
        lives += newlives;
    }

    public static int getGold() {
        return gold;
    }

    public static void setGold(int newgold) {
        gold += newgold;
    }

    public static int getTotalGold() {
        return totalGold;
    }

    public static void setTotalGold(int newtotalGold) {
        totalGold = newtotalGold;
    }
    
    private static void updateStats(){
        currentCurrency.setText("Gold: " + getGold());
        livesLeft.setText("Lives: " + getLives());
        currentScore.setText("Score: " + getScore());   
    }
    /***********************Main Game Loop Functions Start Here***********************/
    /********************************************************************************
    * gameStart
    * This function created a new Thread for the game and overrides the 'run' method
    * from the Thread class so it launches this game's loop.
    *******************************************************************************/
    public static void gameStart() {

        Thread gameThread = new Thread() {
            // Override run() to provide the running behavior of this thread.
            @Override
            public void run() {
                gameLoop();
            }
        };
        gameThread.start();
    }
	/********************************************************************************
    * gameLoop
    * This function contains the infinite while loop which will iterate until the game
    * is lost or won.
    *******************************************************************************/
    public static void gameLoop() {
        
        boolean run = true;
        //This while loop breaks the game
        int enemyHP = enemyHealth[index];
        while (run) {
            if(getLives() != 0){
                gameUpdate();
                
                
                if((enemyHP - rangeFinder())> 0){
                    enemyHP = enemyHP - rangeFinder();
                    
                }
                else{
                    waveCount++;
                    System.out.println(waveCount);
                    if(waveCount == 11){
                        winCondition = 1;
                        createEndGameWindow();
                        break;
                    }
                    else{
                        int randNum=0;
                        Random randomGenerator = new Random();
                        randNum = randomGenerator.nextInt(15);
                        setGold(15*randNum);
                        setScore(21*randNum);
                        updateStats();
                        battleField.get(tick).removeAll();
                        tick = 0;
                        index = nextIndex;
                        nextIndex = randIndex();
                        gameUpdate();
                        enemyHP = enemyHealth[index];
                    }
                }
                
                
                if (index == 0)
                {
                    currentEnemiesText.setText("Now Facing: Mew");
                } 
                else if (index == 1)
                {
                    currentEnemiesText.setText("Now Facing: Eevee");
                }
                else if (index == 2)
                {
                    currentEnemiesText.setText("Now Facing: Mewtwo");
                }
                else if (index == 3)
                {
                    currentEnemiesText.setText("Now Facing: Gyrados");
                }
                else if (index == 4)
                {
                    currentEnemiesText.setText("Now Facing: Rattata");
                }
            
                if (nextIndex == 0)
                {
                    nextWaveText.setText("Next Up: Mew");
                } 
                else if (nextIndex == 1)
                {
                    nextWaveText.setText("Next Up: Eevee");
                }
                else if (nextIndex == 2)
                {
                    nextWaveText.setText("Next Up: Mewtwo");
                }
                else if (nextIndex == 3)
                {
                    nextWaveText.setText("Next Up: Gyrados");
                }
                else if (nextIndex == 4)
                {
                    nextWaveText.setText("Next Up: Rattata");
                }
            
                try {
                    Thread.sleep(1000);
                } 
                catch (InterruptedException ex) {
                }
            }
            else{
                run = false;
                createEndGameWindow();
            }
            tick++;
        }
    }
	/********************************************************************************
     * gameUpdate
     * This function is dealing with calling pathing, range detection, damage dealing and updating
     * the objects on the screen.
     *******************************************************************************/
    public static void gameUpdate() {
        int MAX_TICK=0;
        if(mapPreviewCounter == 0){
            MAX_TICK = 31;
            if(tick >= MAX_TICK){
                index = nextIndex;
                nextIndex = randIndex();
                tick = 0;
                moveEnemy(index);
            }
            else{
                moveEnemy(index);
            }
        }
        else if(mapPreviewCounter == 1){
            MAX_TICK = 25;
            if(tick >= MAX_TICK){
                index = nextIndex;
                nextIndex = randIndex();
                tick = 0;
                moveEnemy(index);
            }
            else{
                moveEnemy(index);
            }
        }
        else if(mapPreviewCounter == 2){
            MAX_TICK = 33;
            if(tick >= MAX_TICK){
                index = nextIndex;
                nextIndex = randIndex();
                tick = 0;
                moveEnemy(index);
            }
            else{
                moveEnemy(index);
            }
        }
    }
    /********************************************************************************
    * moveEnemy
    * This function is dealing with enemy pathing.
    *******************************************************************************/
    public static void moveEnemy(int index){
        
        
        if(enemyPath.get(tick).equals(enemyPath.get(enemyPath.size()-1))){
            setLives(-1);
            updateStats();
        }
        
        
        String[] enemies = {"./Images/Enemy/mew.png", "./Images/Enemy/eevee.png", "./Images/Enemy/mewtwo.png", "./Images/Enemy/garydos.png", "./Images/Enemy/rattata.png"};
        image.setVisible(false);
        battleField.get(enemyPath.get(tick)).add(image);
        image.setIcon(new ImageIcon(enemies[index]));
        image.setVisible(true);
    }
     /********************************************************************************
     * randIndex
     * This function is generating a number between 0 and 4 and returns it
     *******************************************************************************/
    public static int randIndex(){  
        int randNum=0;
        Random randomGenerator = new Random();
        randNum = randomGenerator.nextInt(12345);
        return (randNum%5);
    }
}
/***********************Main Game Loop Functions End Here***********************/
