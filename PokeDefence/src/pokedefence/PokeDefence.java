/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedefence;


import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;

/**
 *
 * @author Justin
 */
public class PokeDefence {

    /********************Variables***********************/
    private static int score=0;
    private static int lives = 10;
    private static int gold = 0;
    private static int mouseClickCount=0;
    private static String highScoreList[];
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
    private static JPanel mainTitle = new JPanel();
    private static JPanel towerImage = new JPanel();
    private static JLabel towerImageIcon = new JLabel();
    private static JPanel enemyImage = new JPanel();
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
    
    /********End of Info Window Items*********/
    
    
    /************End-Game Window Items******************/
    private static JFrame endGameScreen = new JFrame();
    private static JPanel winOrLose = new JPanel();
    private static JLabel winLabel = new JLabel();
    private static JLabel loseLabel = new JLabel();
    private static JTextArea gameStats = new JTextArea();
    private static JTextArea highScores = new JTextArea();
    private static JButton returnToMenu = new JButton("Return to Menu");
    //Use quit button predefined above in this window.
    
    /**********End of End-Game Window Items*************/
    public static void  main(String[] args){
        createMainWindow();
        //createGameOptionsWindow();
        //createInGameWindow();
        //createEndGameWindow();
        //readMapIn();
        
    }
    
    private static void createMainWindow(){
        endGameScreen.dispose();
        gameOptionsWindow.dispose();
        
        
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
        mainTitle.setLocation(300,20);
        mainTitle.setVisible(true);
        mainTitle.setBackground(Color.black);
        mainTitle.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
        mainWindow.add(mainTitle);
        
        //Panel
        towerImage.setSize(200,400);
        towerImage.setLocation(40,75);
        //towerImageIcon.setIcon(new ImageIcon("./Images/MainMenu/tower.jpg"));
        //towerImage.add(towerImageIcon);
        towerImage.setBackground(Color.blue);
        mainWindow.add(towerImage);
        
        //Panel
        enemyImage.setSize(200,400);
        enemyImage.setLocation(740,75);
        //enemyImageIcon.setIcon(new ImageIcon("./Images/MainMenu/enemy.jpg"));
        //enemyImage.add(enemyImageIcon);
        enemyImage.setBackground(Color.red);
        mainWindow.add(enemyImage);
        
        //Button
        playButton.setSize(100,40);
        playButton.setLocation(450,250);
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
        infoButton.setLocation(450,300);
        infoButton.setVisible(true);
        mainWindow.add(infoButton);
        
        //Button
        quitButton.setSize(100,40);
        quitButton.setLocation(450,350);
        quitButton.setVisible(true);
        Action closeProgram = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        quitButton.addActionListener(closeProgram);
        mainWindow.add(quitButton);
        
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
        towerOne.setSize(200,72);
        towerOne.setLocation(400,490);
        Action addTowerOne = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                mouseClickCount++;
                
                if(mouseClickCount == 1){
                    count = 0;
                    for(int i=0;i<26;i++){
                        for(int j=0;j<11;j++){
                            if(mapLayout[j][i].equals("0")){
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black,1));
                                battleField.get(count).setBackground(Color.GREEN);
                                count++;
                            }
                            else if(mapLayout[j][i].equals("1")){
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black,1));
                                battleField.get(count).setBackground(Color.gray);
                                count++;
                            }
                        }    
                    }
                    gridWindow.repaint();
                }
                else{
                    mouseClickCount = 0;
                    count = 0;
                    for(int i=0;i<26;i++){
                        for(int j=0;j<11;j++){
                            if(mapLayout[j][i].equals("0")){
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black,1));
                                battleField.get(count).setBackground(Color.blue);
                                count++;
                            }
                            else if(mapLayout[j][i].equals("1")){
                                battleField.get(count).setBorder(BorderFactory.createLineBorder(Color.black,1));
                                battleField.get(count).setBackground(Color.gray);
                                count++;
                            }
                        }    
                    }
                    gridWindow.repaint();
                }
            }
        };
        towerOne.addActionListener(addTowerOne);
        gridWindow.add(towerOne);
        
        //Button
        towerTwo.setSize(200,72);
        towerTwo.setLocation(600,490);
        gridWindow.add(towerTwo);
        //Button
        towerThree.setSize(200,72);
        towerThree.setLocation(800,490);
        gridWindow.add(towerThree);
        gridWindow.repaint();
    }
    
    private static void createEndGameWindow(){
        gridWindow.dispose();
        
        //Frame
        endGameScreen.setLayout(null);
        endGameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endGameScreen.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 3, (screenSize.height / 4) - 80);
        Point newLocation = new Point(middle.x - (endGameScreen.getWidth() / 2+225), middle.y - (endGameScreen.getHeight() / 2));
        endGameScreen.setSize(1016,600);
        endGameScreen.setLocation(newLocation);
        endGameScreen.setVisible(true);
        
        //Panel
        winOrLose.setSize(336,168);
        winOrLose.setLocation(335,20);
        winOrLose.setVisible(true);
        
        if(winCondition == 1){
            //Win Label
            winLabel.setIcon(new ImageIcon("./Images/EndGameScreen/win.png"));
            winLabel.setVisible(true);
            winOrLose.add(winLabel);
        }
        else if(winCondition != 1){
            //Lose Label
            loseLabel.setIcon(new ImageIcon("./Images/EndGameScreen/lose.png"));
            loseLabel.setVisible(true);
            winOrLose.add(loseLabel);
        }
        endGameScreen.add(winOrLose);
        
        
        //TextArea
        gameStats.setSize(200,400);
        gameStats.setLocation(50,100);
        gameStats.setBackground(Color.blue);
        endGameScreen.add(gameStats);
        
        //TextArea
        highScores.setSize(200,400);
        highScores.setLocation(750,100);
        highScores.setLineWrap(true);
        highScores.setWrapStyleWord(true);
        highScores.setText(getHighScores());
        highScores.setBackground(Color.red);
        endGameScreen.add(highScores);
        
        //Button
        returnToMenu.setSize(250,50);
        returnToMenu.setLocation(370,360);
        Action returnToMain = new AbstractAction("") {
            public void actionPerformed(ActionEvent e) {
                createMainWindow();
            }
        };
        returnToMenu.addActionListener(returnToMain);
        endGameScreen.add(returnToMenu);
        
        //Button
        quitButton.setSize(250,50);
        quitButton.setLocation(370,450);
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
    private static String getHighScores(){
        //Read and parse in the highscore file
        return "";
    }

    public static int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public static int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives += lives;
    }

    public static int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }
    
    private static void updateStats(){
        currentCurrency.setText("Gold: " + getGold());
        livesLeft.setText("Lives: " + getLives());
        currentScore.setText("Score: " + getScore());   
    }
}
