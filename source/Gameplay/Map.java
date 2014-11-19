/** This class creates the map of each level by using values stored from a .psq level file. It creates and paints the 
  * each block in the grid.
  * @author Peter Huang, modified by Kareem Golaub
  * @version 3.0 June 7th, 2014
  */

package Gameplay;
import java.awt.*; //use graphics
import javax.swing.*;
public class Map
{
  /**
   * worldWidth int - width of the game play screen in 26x26 blocks*/
  public int worldWidth = 20;
  /**
   * worldHeight int - height of the game play screen in 26x26 blocks*/
  public int worldHeight = 11;
  /**
   * blockSize int - size of the each block (length+width)*/
  public int blockSize = 52;
  /**
   * block Block - Two dimensional array storing the ground and air value of each block. */
  public Block [][] block;
  /**
   * pane JPanel - Reference variable for JPanel.*/
  JPanel pane;
  
  /**
   * This constructor sets up the pane (of this class) variable; it is passed in by a pane parameter.
   * This method also calls the init method which lays out each block according to its specific ground and air values.
   * @param pane JPanel reference variable.*/
  public Map (JPanel pane) {
    init();
    this.pane = pane;
  }
  
  /**
   * This method lays out each block according to its specific ground and air values.
   */
  public void init() {
    block = new Block[worldHeight][worldWidth];
    for (int y = 0; y < block.length; y++)
    {
      for (int x = 0; x < block [0].length; x++)
        block[y][x]= new Block((1100/2) - (worldWidth*blockSize/2) + x*blockSize, y*blockSize, blockSize, blockSize, Value.groundTissue, Value.airAir);      
    }
  }
  
  /**
   * This method draws the graphics of each corresponding tile onto the corresponding location on the game play tile map.
   * It draws the ground tiles and the air tiles (which are the white blood cells and pathogens).
   * @param g Graphics reference variable
   */
  public void draw (Graphics g)
  {
    for (int y = 0; y < block.length; y++)
      for (int x = 0; x < block [0].length; x++)
      block[y][x].draw(g);
    
    for (int y = 0; y < block.length; y++)
      for (int x = 0; x < block [0].length; x++)
      block[y][x].fight(g);
  }
  
  /**
   * This method is the physics of the program, it processes each block depending on what action has been taken upon it.
   */
  public void process()
  {
    for (int y=0; y < block.length; y++)
      for (int x=0; x<block[0].length; x++)
      block[y][x].process();
  }
}