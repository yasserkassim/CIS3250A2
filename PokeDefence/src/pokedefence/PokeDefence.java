/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private static int score=0;
    private static int lives = 10;
    private static int gold = 0;
    private static int totalGold=0;
    private static int mouseClickCountOne=0;
    private static int mouseClickCountTwo=0;
    private static int mouseClickCountThree=0;
    private static String[][] mapLayout = new String[11][26];
    private static int count=0;
    private static ArrayList<JPanel> battleField = new ArrayList<>();
    private static int winCondition=0;
    private static int mapPreviewCounter=0;
    private static JLabel mapPreviewImage = new JLabel();
    private static String[] mapImagePath = {"./Images/Maps/map1.png", "./Images/Maps/map2.png", "./Images/Maps/map3.png"};
    private static String[] mapFilePath = {"./Images/Maps/map1.txt", "./Images/Maps/map2.txt", "./Images/Maps/map3.txt"};
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
    private static JPanel mapSelectionTitle = new JPanel();
    private static JPanel mapPreview = new JPanel();
    private static JLabel mapPreviewLabel = new JLabel();
    private static JButton mapPreviewNext = new JButton();
    private static JButton mapPreviewPrevious = new JButton();
    private static JTextArea mapDescription = new JTextArea();
    private static JButton backButton = new JButton("Back");
    private static JButton beginGameButton = new JButton("Begin Game");
    
    /***********End Game Options Window Items*****************/
    
    /*************In-Game Window Items******************/
    private static JFrame gridWindow = new JFrame(); 
    private static JLabel currentCurrency = new JLabel();
    private static JLabel livesLeft = new JLabel();
    private static JLabel currentScore = new JLabel();
    //private static JButton pauseGame = new JButton("Pause Game");
    private static JPanel currentEnemies = new JPanel();
    private static JPanel nextWaveEnemies = new JPanel();
    private static JButton towerOne = new JButton("Tower One");
    private static JButton towerTwo = new JButton("Tower Two");
    private static JButton towerThree = new JButton("Tower Three");
    private static JPanel topBorder = new JPanel();
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
    public static void  main(String[] args) throws IOException{
        createMainWindow();
        //createInfoWindow();
        //createGameOptionsWindow();
        //createInGameWindow();
        //createEndGameWindow();
        //readMapIn();
        //getHighScores();
        
    }
    
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
        mapSelectionTitle.setBackground(Color.BLACK);
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
        
        mapDescription.setSize(400,150);
        mapDescription.setLocation(500,250);
        mapDescription.setEditable(false);
        mapDescription.setLineWrap(true);
        mapDescription.setWrapStyleWord(true);
        mapDescription.setText("This is a test sting which is used in the text area to provide te details about the map that will be displayed"
                + "during the game selection process.");
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
                } catch (IOException ex) {
                    System.exit(0);
                }
            }
        };
        beginGameButton.addActionListener(startGame);
        gameOptionsWindow.add(beginGameButton);
    }
    
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
                + "Description: \n      This is a Tower Defense game in which the enemy Pokemon are are trying to attack the player's base."
                + " They follow the set path and are being attacked by towers placed by the user trying to defend their base.\n\n"
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
                
                if (mapLayout[j][i].equals("0")) {
                    JLabel number = new JLabel();
                    battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    battleField.get(count).setBackground(Color.blue);
                    battleField.get(count).add(number);
                    count++;
                } 
                else if (mapLayout[j][i].equals("1")) {
                    battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    battleField.get(count).setBackground(Color.gray);
                    count++;
                }
            }
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
        currentEnemies.setBackground(Color.black);
        gridWindow.add(currentEnemies);
        
        //Panel
        nextWaveEnemies.setSize(200,72);
        nextWaveEnemies.setLocation(200,490);
        nextWaveEnemies.setVisible(true);
        nextWaveEnemies.setBackground(Color.red);
        gridWindow.add(nextWaveEnemies);
        
        //Button
        towerOne.setSize(200, 72);
        towerOne.setLocation(400, 490);
        Action addTowerOne = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                mouseClickCountOne++;
                towerTwo.setEnabled(false);
                towerThree.setEnabled(false);
                if (mouseClickCountOne == 1) {
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.GREEN);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("1")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.gray);
                                count++;
                            }
                        }
                    }
                    gridWindow.repaint();
                }
                else {
                    mouseClickCountOne = 0;
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.blue);
                                count++;
                            }
                            else if (mapLayout[j][i].equals("1")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.gray);
                                count++;
                            }
                        }
                    }
                    towerTwo.setEnabled(true);
                    towerThree.setEnabled(true);
                    gridWindow.repaint();
                }
            }
        };
        towerOne.addActionListener(addTowerOne);
        gridWindow.add(towerOne);

        //Button
        towerTwo.setSize(200, 72);
        towerTwo.setLocation(600, 490);
        Action addTowerTwo = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                mouseClickCountTwo++;
                towerOne.setEnabled(false);
                towerThree.setEnabled(false);
                
                if (mouseClickCountTwo == 1) {
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.GREEN);
                                JLabel number = new JLabel();
                                number.setText(Integer.toString(count));
                                number.setVisible(true);
                                battleField.get(count).add(number);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("1")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.gray);
                                count++;
                            }
                        }
                    }
                    gridWindow.repaint();
                } 
                else {
                    mouseClickCountTwo = 0;
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                battleField.get(count).setBackground(Color.blue);
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                JLabel number = new JLabel();
                                number.setText(Integer.toString(count));
                                number.setVisible(true);
                                battleField.get(count).add(number);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("1")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.gray);
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

        //Button
        towerThree.setSize(200, 72);
        towerThree.setLocation(800, 490);
        Action addTowerThree = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                mouseClickCountThree++;
                towerOne.setEnabled(false);
                towerTwo.setEnabled(false);

                if (mouseClickCountThree == 1) {
                    count = 0;
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (mapLayout[j][i].equals("0")) {
                                JLabel number = new JLabel();
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.GREEN);
                                number.setText(Integer.toString(count));
                                number.setVisible(true);
                                battleField.get(count).add(number);
                                count++;
                            }
                            else if (mapLayout[j][i].equals("1")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.gray);
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
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.blue);
                                count++;
                            } 
                            else if (mapLayout[j][i].equals("1")) {
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black, 1));
                                battleField.get(count).setBackground(Color.gray);
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
    }
    
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
        System.out.println(winCondition);
        if(winCondition == 1){
            System.out.println("You Won");
            //Win Label
            winLabel.setIcon(new ImageIcon("./Images/EndGameScreen/win.jpg"));
            winLabel.setVisible(true);
            winOrLose.add(winLabel);
            endGameScreen.add(winOrLose);
        }
        else if(winCondition != 1){
            System.out.println("You lost");
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
        
        getHighScores();
        //Need a compare highscore function to be called here
        //Compares to see if value is between highscore above and highscore below, and moves the list down, removing the last element
        //and then adding the new value in the index above the highscore below
        //Make a pop-up if they have a highscore
        
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
        
        //Button
        returnToMenu.setSize(250,50);
        returnToMenu.setLocation(350,360);
        Action returnToMain = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                createMainWindow();
            }
        };
        returnToMenu.addActionListener(returnToMain);
        endGameScreen.add(returnToMenu);
        
        //Button
        quitButton.setSize(250,50);
        quitButton.setLocation(350,450);
        Action exitGame = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                writeNewHighScores();
                System.exit(0);
            }
        };
        quitButton.addActionListener(exitGame);
        endGameScreen.add(quitButton);
        endGameScreen.repaint();
    }
    
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
            mapDescription.setText("This is the first map.");
        }
        else if (mapPreviewCounter == 1){
            mapDescription.setText("This is the second map.");
        }
        else if (mapPreviewCounter == 2){
            mapDescription.setText("This is the third map.");
        }
        else{
            mapDescription.setText("Error encountered.");
        }
    }
    
    private static void writeNewHighScores(){
        //Write scores from array into text file, save in location ./Highscores/highscores.txt
        
    }
    
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

    public static int getScore() {
        return score;
    }

    public void setScore(int newscore) {
        score += newscore;
    }

    public static int getLives() {
        return lives;
    }

    public void setLives(int newlives) {
        lives += newlives;
    }

    public static int getGold() {
        return gold;
    }

    public void setGold(int newgold) {
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
}