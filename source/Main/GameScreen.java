package Main;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import GameState.GameStateManager;

/** This class is the content pane of Leukocytic Defense, and contains the main game thread.
  * @author Kareem Golaub
  * @version 2.0 May 23rd, 2014
  */
public class GameScreen extends JPanel implements Runnable
{
  /** This integer holds the value of the state to be changed to.
    */
  public int state = 0;
  /** This boolean holds whether or not a request to pause has been sent.
    */
  public boolean check = false;
  /** This boolean holds whether or not a class has sent a signal to change state.
    */
  public boolean stateCheck = false;
  
  /** This integer holds the value of the framerate of the game.
    */
  public static int FPS = 20;
  
  /** This layout is the layout manager for the JPanel.
    */
  SpringLayout layout = new SpringLayout();
  
  /** This boolean holds the value of whether or not the game thread is running.
    */
  public boolean running;
  
  /** This points to the graphics object of the background, allowing for direct manipulation of its contents.
    */
  Graphics2D g;
  
  /** This is the manager for the different gamestates, and gives them all common ground on access to the GameScreen's pane, frame and layout.s
    */
  public GameStateManager gsm;
  
  /** This constructor sets running to true, sets the properties of the JPanel and starts the game thread.
    */
  public GameScreen()
  {
    init();
    setLayout(layout);
    setPreferredSize(new Dimension(600, 600));
    setFocusable(true);
    setBackground(Color.WHITE);
    new Thread(this).start();
  }
  
  /** Overrides the paintComponent class in JPanel. It calls the superclass' paintComponent and calls the gsm's paintComponent right after.
    * @param g Takes in the graphics object of the JPanel.
    */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    gsm.paintComponent(g);
  }
  
  /** This method instantiates the boolean running and GameStateManager.
    */
  public void init() {
    running = true;
    gsm = new GameStateManager(this, layout);
  }
  
  /** This method updates the pane with the game state's desired processing.
    */
  public void update() {
    gsm.update();
  }
  
  /** This method updates the pane with the game state's desired graphics/components.
    */
  public void draw() {
    gsm.draw(g);
  }
  
  /** Is the method containing the game loop and changes the values of the constraints.
    * <p><b>Variable Dictionary: Reference, Type, Purpose</b>
    * <ul>
    * <li>start, long, holds the time at the start of the loop
    * <li>elapsed, long, holds the time passed after setting constraints
    * <li>delay, long, holds the millisecond value of how long the thread should sleep for.
    * <li>wait, int, holds the current frame count
    * <li>e, InterruptedException, is the exception for if the thread is interrupted
    * </ul>
    * <p><b>Loops: Condition, Purpose</b>
    * <ul>
    * <li> while(running), continously updates the screen while the game is running
    * </ul>
    */
  public void run()
  {
    long start;
    long elapsed;
    int wait = 0;
    long delay = 1;
    while(true) {
      if (running) {
        if (wait >= FPS) {
          draw();
          update();
          validate();
          repaint();
          wait = 0;
        }
        else
          wait++;
      }
      checkRun();
      checkChange();
      
      start = System.nanoTime();
      elapsed = System.nanoTime() - start;
      
      if (1-elapsed/1000000 < 0)
        delay = 1;
      else
        delay = 1-elapsed/1000000;
      try {
        Thread.sleep(delay);
      }
      catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  /** This method checks if the loop needs to be paused/unpause, and then pauses/unpauses it.
   */
  public void checkRun() {
    if (check && gsm.currentState == gsm.STAGEONESTATE)
      running = !running;
    check = false;
  }
  
  /** This method checks if the game state needs to be changed, and then changes it.
   */
  public void checkChange() {
    if (stateCheck)
      gsm.setState(state);
    stateCheck = false;
  }
}