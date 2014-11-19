package GameState;
import Gameplay.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import Gameplay.Map;
import Main.GameScreen;
import GameState.MenuState;
import GameState.InputScoreState;
import Gameplay.QuizScreen;
import java.util.*;

/** This class is the state in which Leukocytic Defense is played, and draws the game accordingly.
  * @author Kareem Golaub
  * @version 4.0 June 12, 2014
  */
public class StageOneState extends GameState
{
  /** variable counting the frames, similar to a delay function.
    */
  int frameCount = 0;
  /** variable that defines if it is the first start of the game.
    */
  public static boolean isFirst = true;
  /**  variable that defines the width of the game screen.
    */
  public static int myWidth;
  /** variable that defines the height of the game screen.
    */
  public static int myHeight;
  /** reference variable that allows access to the Map class.
    */
  public static Map map;
  /** variable that stores the difficulty of the game played.
    */
  public static int difficulty;
  /** variable that stores the amount of mobs in game.
    */
  public int mobCount = 0;
  /** variable that allows access to the LevelScanner class, scans the map of the level.
    */
  public static LevelScanner save;
  /** variable that stores the cursor's location.
    */
  public static Point mousePoint = new Point (0, 0);
  /** variable that allows access to the Store class, which allows the shop at the bottom of the game screen.
    */
  public static Store store;
  /** that stores the amount of bone marrow the player has.
    */
  public static int bank = 100;
  /** that stores the amount of lives the player has.
    */
  public static int lives = 10;
  /** that stores the score the player has accumulated.
    */
  public static int score = 0;
  
  /** that stores the amount mobs the player has killed.
    */
  public static int killed = 0;
  /** stores the amount mobs to beat the stage.
    */
  public static int killsToWin = 75;
  /** stores the level of the game.
    */
  public static int level;
  /** that stores the total amount of levels each difficulty.
    */
  public static int maxLevel = 3;
  /** variable that determines if the player won or not.
    */
  public static boolean isWin = false;
  
  /** variable that allows access to the Quiz class.
    */
  public QuizScreen quiz;
  /** stores the length of the delay in the winning/finishing screen.
    */
  public static int winTime = 3600;
    /** stores the length of the delay in the winning/finishing screen. Initial value of the delay loop.
      */
    public static int winFrame = 0;
  /** Image array of 100 that stores the strips of different image icons used as ground tiles.
    */
  public static Image[] tileset_ground = new Image [100];
  /** Image array of 100 that stores the strips of different image icons used as air tiles.
   */
  public static Image[] tileset_air = new Image [100];
  /** Image array of 100 that stores the strips of different image icons used as resource tiles.
   */
  public static Image[] tileset_res = new Image [100];
  /** Image array of 100 that stores the strips of different image icons used as mob tiles.
   */
  public static Image[] tileset_mob = new Image [100];
  /** Image reference variable for the defeat screen
   */
  public Image defeat;
  /** Image reference variable for the success screen
   */
  public Image success;
  /** Image reference variable for the clear screen
   */
  public Image clear;
  /** Mob array of 120, the max mobs that can ever be on the screen is 120.
   */
  public static Mob[] mobs = new Mob [120];
  
  /** variable that is used for debugging mode. Manually change it to true or false to activate the mode or not.
   */
  public static boolean isDebug = false;
  /** that represents the time it takes for the monster to spawn.
   */
  public static int spawnTime = 900;
  /** that represents the time it takes for the monster to spawn. This initializes the spawn loop of the mob spawn.
   */
  int spawnFrame = 0;
  /** holds the parent jframe
   */
  public JFrame gameFrame;
  
  /** This constructor sets the game state manager in the stage one state.
   * @param gsm GameStateManager reference variable.
   */
  public StageOneState(GameStateManager gsm) {
    this.gsm = gsm;
  }
  
  public void init() {  
    gsm.pane.removeAll();
    gsm.pane.repaint();
    gameFrame = (JFrame)SwingUtilities.getWindowAncestor(gsm.pane);
    KeyControl control = new KeyControl(gsm.pane);
    gameFrame.setVisible(false);
    gsm.pane.setSize(1100, 710);
    SwingUtilities.getWindowAncestor(gsm.pane).setSize(1100, 710);
    gameFrame.setLocationRelativeTo(null);
    gameFrame.setVisible(true);
    gameFrame.addMouseListener (control);
    gameFrame.addMouseMotionListener (control);
    GameScreen.FPS = 0;
    winTime = 3600;
    killed = 0;
    bank = 100;
    lives = 10;
    level = 1;
    isFirst = true;
    mobCount = 0;
    spawnTime = 900;
    spawnFrame = 0;
    isWin = false;
    score = 0;
  }
  
  /**
   * This method updates every change in the game state such as mobs spawning, mobs being shot, and mobs being quarantined.
   * It also sets the win screen and adds bone marrow and score and level accordingly. Quiz is also shown!
   */
  public void update() {
    if (!isFirst && lives > 0 && !isWin) {
      mobSpawner();
      map.process();
      for (int i = 0; i < mobs.length; i++)
        if (mobs[i].inGame)
        mobs[i].process();
    }
    
    if (isWin) {
      if (winFrame >= winTime) {
        if (level == maxLevel)
        {
          inputScore();
          gsm.setState(gsm.MENUSTATE);
        }
        else {
          store.holdsItem = false;
          store.heldID = -1;
          store.realID = -1;
          isWin = false;
          level += 1;
          score += 2*bank + 20*lives;
          bank = 0;
          showQuiz();
        }
        winFrame = 0;       
      }
      else
        winFrame++;
    }
  }
  
  /** This method loads and shows the quiz between each stage. It adds bone marrow (coins) if the quiz is answered correctly.
   */
  public void showQuiz() {
    gameFrame.setVisible(false);
    quiz = new QuizScreen();
    while(!quiz.isDone()) {
      try {
        Thread.sleep(1);
      }
      catch(Exception e){
      }
    }
    gameFrame.setVisible(true);
    mobCount = 0;
    bank += 150;
    saveLoad();
    setMobs();
  }
  
  /** This method overrides the abstract method draw from gamestate.
   */
  public void draw(java.awt.Graphics2D g){};
  /** This method overrides the abstract method keyPressed from gamestate.
   */
  public void keyPressed(int k){};
  /** This method overrides the abstract method keyReleased from gamestate.
   */
  public void keyReleased(int k){};
  
  /** This method overrides the abstract method paintComponent from gamestate.
    * It draws what needs to be currently displayed on the screen.
   */
  public void paintComponent(Graphics g) {
    
    if (isFirst) {
      initial();
      isFirst = false;
    }
    if (isWin)
    {
      completeMessage(g);
      if (frameCount > 3000)
      {
        frameCount = 0;
        inputScore();
      }
      else
        frameCount++;
    }
    else if (lives <= 0)
    {
      SwingUtilities.getWindowAncestor(gsm.pane).setSize(600, 600);
      SwingUtilities.getWindowAncestor(gsm.pane).setLocationRelativeTo(null);
      gsm.pane.setSize(600,600);
      lostMessage(g);
      if (frameCount > 3000) {
        frameCount = 0;
        inputScore();
        lives = 10;
      }
      else
        frameCount++;
    }
    else {
      g.setColor (new Color(38, 51, 43));   
      g.fillRect (0, 0, gsm.pane.getWidth(), gsm.pane.getHeight());
      g.setColor (Color.BLACK);
      g.drawLine (map.block[0][0].x-1, 0, map.block[0][0].x-1, map.block[map.worldHeight-1][0].y + map.blockSize); //left line
      g.drawLine (map.block[0][map.worldWidth-1].x + map.blockSize, 0, map.block[0][map.worldWidth-1].x + map.blockSize, map.block[map.worldHeight-1][0].y + map.blockSize); //right line
      g.drawLine (map.block[0][0].x, map.block[map.worldHeight-1][0].y + map.blockSize, map.block[0][map.worldWidth-1].x + map.blockSize, map.block[map.worldHeight-1][0].y + map.blockSize); //bottom line
      map.draw(g);
      for (int i = 0; i < mobs.length; i++) {
        if (mobs[i].inGame)
          mobs[i].draw(g);
      }
      store.draw(g);
    }
  }
  
  /** This method sends in mobs to the game screen.
   */
  public void mobSpawner() {
    if (spawnFrame >= spawnTime) {
      if (mobCount < 120) {
        mobs[mobCount].initMob();
        mobs[mobCount+1].initMob();
        mobCount += 2;
      }
      spawnFrame = 0;
    }
    else
      spawnFrame++;
  }
  
  /** This method checks if the player has won the level.
   */
  public static void hasWon() {
    if (killed >= killsToWin) {
      isWin = true;
      killed = 0;
    }
  }
  
  /** This method initializes the resources that the state needs to draw its display, and then loads the game.
   */
  public void initial() {
    map = new Map (gsm.pane);
    save = new LevelScanner ();
    store = new Store (gsm.pane);
    
    bank += ((level-1)*100);
    spawnTime = 800;
    Mob.mobSpeed = (int)(Mob.mobSpeed/2);
    
    for (int i = 0; i < tileset_ground.length; i++) {
      tileset_ground[i] = new ImageIcon (getClass().getResource("/source/resources/tileset_ground.png")).getImage();
      tileset_ground[i] = gsm.pane.createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 26*i, 26, 26))); //second last are width and height
    }
    for (int i = 0; i < tileset_air.length; i++) {
      tileset_air[i] = new ImageIcon (getClass().getResource("/source/resources/tileset_air.png")).getImage();
      tileset_air[i] = gsm.pane.createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 26*i, 26, 26))); //second last are width and height
    }
    
    tileset_res[0] = new ImageIcon (getClass().getResource("/source/resources/cell.png")).getImage();
    tileset_res[1] = new ImageIcon (getClass().getResource("/source/resources/heart.png")).getImage();
    tileset_res[2] = new ImageIcon (getClass().getResource("/source/resources/coin.png")).getImage();
    
    tileset_mob[0] = new ImageIcon (getClass().getResource("/source/resources/Mob Images/Bacteria.png")).getImage();
    tileset_mob[2] = new ImageIcon (getClass().getResource("/source/resources/Mob Images/Virus.png")).getImage();
    tileset_mob[1] = new ImageIcon (getClass().getResource("/source/resources/Mob Images/Parasite.png")).getImage();
    
    defeat = new ImageIcon(getClass().getResource("/source/resources/Defeat.png")).getImage();
    success = new ImageIcon(getClass().getResource("/source/resources/Victory.png")).getImage();
    clear = new ImageIcon(getClass().getResource("/source/resources/Stage Cleared.png")).getImage();
    
    saveLoad();
    setMobs();
  }
  
  /** This method reads in the level from the text files.
   */
  public void saveLoad() {
    save.loadSave("/source/Gameplay/levels/stage" + level + ".psq");
  }
  
  /** This method instantiates the Mob array.
   */
  public void setMobs() {
    
    for (int i = 0; i < 20; i+=2) {
      mobs[i] = new Mob(Value.groundArtery, Value.bacteria);
      mobs[i+1] = new Mob(Value.groundArteryTwo, Value.bacteria);
    }
    for (int i = 20; i < 40; i+=2) {
      mobs[i] = new Mob(Value.groundArtery, Value.bacteria);
      mobs[i+1] = new Mob(Value.groundArteryTwo, Value.parasite);
    }
    for (int i = 40; i < mobs.length; i+=2) {
      mobs[i] = new Mob(Value.groundArtery, Value.parasite);
      mobs[i+1] = new Mob(Value.groundArteryTwo, Value.virus);
    }
  }
  
  /** This method displays a message that comes up when you defeat a level.
    * @param g Graphics takes in the JPanels graphics object to draw with
    * @param font Font holds the font to draw text with
   */
  public void completeMessage(Graphics g) {
    Font font = null;
    if (level == maxLevel)
    {
      SwingUtilities.getWindowAncestor(gsm.pane).setSize(600, 600);
      SwingUtilities.getWindowAncestor(gsm.pane).setLocationRelativeTo(null);
      gsm.pane.setSize(600,600);
      g.drawImage (success, 0, 0, gsm.pane.getWidth(), gsm.pane.getHeight(), null);
    }
    else
    {
      g.drawImage (clear, 0, 0, gsm.pane.getWidth(), gsm.pane.getHeight(), null);
      g.setColor(Color.BLACK);
      try {
        font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/source/resources/fonts/ARBERKLEY.ttf"));
      }
      catch (Exception e) {
        e.printStackTrace();
      }
      font = font.deriveFont(50f);
      g.setFont(font);
      g.drawString("Bonus Bone Marrow: " + (150), 400, 480);
    }
    //("You have completed Stage " + level + ". Bonus coins: " + (150) + " Please wait for the next level...", 15, 25);
  }
  
  /** This method displays a message when you lose the game.
   */
  public void lostMessage(Graphics g) {
    g.drawImage (defeat, 0, 0, gsm.pane.getWidth(), gsm.pane.getHeight(), null);
  }
  
  /** This method sends in the score to the input score state and sets the state to input score state.
   */
  public void inputScore() {
    ((InputScoreState)gsm.gameStates.get(gsm.INPUTSCORESTATE)).setScore(score);
    ((InputScoreState)gsm.gameStates.get(gsm.INPUTSCORESTATE)).setDifficulty(difficulty);
    gsm.pane.stateCheck = true;
    gsm.pane.state = gsm.INPUTSCORESTATE;
    score = 0;
  }
}