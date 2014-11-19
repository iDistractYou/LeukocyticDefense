package GameState;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import Main.GameScreen;

/** Description: This class contains the various states of the game. States are changed through this class.
  * @author Kareem Golaub
  * @version 3.0 June 12 2014
 */
public class GameStateManager
{
  /** Holds the game states.
   */
  public ArrayList <GameState> gameStates;
  /** Holds the current state.
   */
  public int currentState;
  /** Holds the pane of the game.
   */
  GameScreen pane;
  
  /** Holds the layout of the pane.
   */
  SpringLayout layout;
  
  /** Holds the value for splashstate.
   */
  public final int SPLASHSTATE = 0;
  /** Holds the value for menustate.
   */
  public final int MENUSTATE = 1;
  /** Holds the value for stageonestate.
   */
  public final int STAGEONESTATE = 2;
  /** Holds the value for inputscorestate.
   */
  public final int INPUTSCORESTATE = 3;
  /** Holds the value for difficultystate.
   */
  public final int DIFFICULTYSTATE = 4;
  
  /** Creates the gamestatemanager while sending in the game screen for the game and its layout.
    * @param g GameScreen contains the screen of the game
    * @param s SpringLayout contains the layout of the game
   */
  public GameStateManager(GameScreen g, SpringLayout s) { 
    gameStates = new ArrayList<GameState>();
    this.pane = g;
    this.layout = s;
    gameStates.add(new SplashState(this));
    gameStates.add(new MenuState(this));
    gameStates.add(new StageOneState(this));
    gameStates.add(new InputScoreState(this));
    gameStates.add(new DifficultyState(this));
    setState(SPLASHSTATE);
  }
  
  /** Changes the state of the game.
    * @param state int takes in the state to be changed to
   */
  public void setState(int state) {
    pane.removeAll();
    currentState = state;
    gameStates.get(currentState).init();
  }

  /** Calls the states update method.
   */
  public void update() {
    gameStates.get(currentState).update();
  }
  
  /** Calls the states draw method.
   */
  public void draw(java.awt.Graphics2D g) {
    gameStates.get(currentState).draw(g);
  }
  
  /** Calls the states paintComponent method.
   */
  public void paintComponent(Graphics g) {
    gameStates.get(currentState).paintComponent(g);
  }
}









