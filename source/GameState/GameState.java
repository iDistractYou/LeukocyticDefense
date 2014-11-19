package GameState;

/** This class accounts for the animations at the start of the program.
  * @author Kareem Golaub
  * @version 1.0 May 15th, 2014  
  * The GameState interface is used for the different types of "Game States" across this game such as the splash screen, menu screens, input screens
  * and game screens
  */
public abstract class GameState 
{
  /**
   * gsm - Reference variable for GameStateManager.*/
  public GameStateManager gsm;  
  /**
   * Abstract method, used for initialization in other classes that implements this abstract class. */
  public abstract void init();
  /**
   * Abstract method, used for initialization in other classes that implements this abstract class. */
  public abstract void update();
  /**
   * Abstract method, used for initialization in other classes that implements this abstract class. 
   * @param g java.awt.Graphics2D reference variable. */
  public abstract void draw(java.awt.Graphics2D g);
  /**
   * Abstract method, used for initialization in other classes that implements this abstract class.
   * @param g java.awt.Graphics reference variable. */
  public abstract void paintComponent(java.awt.Graphics g);
}
