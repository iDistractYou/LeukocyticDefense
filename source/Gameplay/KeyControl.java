/** This class implements the MouseMotionListener and MouseListener interfaces. This class allows mouse handling to be 
  * used. This includes mouses hovering over a button, dragging, and clicking icons.
  * @author Peter Huang
  * @version 1.0 May 16th, 2014
  */
package Gameplay;
import Main.LeukocyticDefense;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import GameState.StageOneState;
public class KeyControl implements MouseMotionListener, MouseListener
{
  /**
   *frame - Reference variable for JFrame*/
  JFrame frame;
  /**
   *pane - Reference variable for JPanel*/
  JPanel pane;
  
  /**
   * This is the constructor which sets up the pane and frame variables.
   * The variables are set to the corresponding variables passed in.
   * @param pane JPanel reference variable */
  public KeyControl(JPanel pane)
  {
    this.pane = pane;
    this.frame = (JFrame) SwingUtilities.getWindowAncestor(pane);
  }
  
  /**
   * This method is overriden from the MouseListener interface. It is not used in this program.
   * @param e MouseEvent reference variable. */
  public void mouseClicked(MouseEvent e) {}
  
  /**
   * This method is overriden from the MouseListener interface. It is not used in this program.
   * @param e MouseEvent reference variable. */
  public void mouseEntered(MouseEvent e) {}
  
  /**
   * This method is overriden from the MouseListener interface. It is not used in this program.
   * @param e MouseEvent reference variable. */
  public void mouseExited(MouseEvent e) {}
  
    /**
   * This method is overriden from the MouseListener interface. It stores what was clicked.
   * @param e MouseEvent reference variable. */
  public void mousePressed(MouseEvent e) 
  {
    StageOneState.store.click();
  }
  
  /**
   * This method is overriden from the MouseListener interface. It is not used in this program.
   * @param e MouseEvent reference variable. */
  public void mouseReleased(MouseEvent e){}
  
  /**
   * This method is overriden from the MouseMotionListener interface. This method is involved with the drag and drop process.
   * @param e MouseEvent reference variable. */
  public void mouseDragged(MouseEvent e)
  {
    StageOneState.mousePoint = new Point (e.getX() + ((frame.getWidth() - pane.getWidth())/2) , (e.getY() - (frame.getHeight() - pane.getHeight())-(frame.getWidth() - pane.getWidth())/2));
  }
  
  /**
   * This method is overriden from the MouseMotionListener interface. This method is involved with the drag and drop process.
   * @param e MouseEvent reference variable. */
  public void mouseMoved(MouseEvent e)
  {
    StageOneState.mousePoint = new Point (e.getX() + ((frame.getWidth() - pane.getWidth())/2) , (e.getY() - (frame.getHeight() - pane.getHeight())-(frame.getWidth() - pane.getWidth())/2));
  }
}