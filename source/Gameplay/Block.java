/** Description: This is the block class that organizes the tilesets and where they are placed within a grid of the 
  * mapping system in Leukocytic Defense.
 * @author Peter Huang and Kareem Golaub
 * @version 1.4 June 10 2014
 */

package Gameplay;
import java.awt.*;
import GameState.StageOneState;
public class Block extends Rectangle
{
  /** groundID - int variable storing the ground tile's ID.
   */
  int groundID;
  /** airID - int variable storing the air tile's ID.
   */
  int airID;
  /** tower - Tower reference variable preset to null as there are no towers that begin on the screen!
   */
  Tower tower = null;
  
  /** Constructor for this class that passes in a rectangle of an existing block's sizes and dimensions and ID.
    * @param x int position of where the block is.
    * @param y int position of where the block is.
    * @param width int magnitude of the block's width.
    * @param height int magnitude of the block's height.
    * @param groundID int storing what type of ground tile is placed in this specific block.
    * @param airID  int storing what type of air tile (stuff placed on top of ground tiles) is placed in this specific block.
   */
  public Block (int x, int y, int width, int height, int groundId, int airId) {
    setBounds (x, y, width, height);
    this.groundID = groundID;
    this.airID = airID;
  }
  
  /** This method draws the tiles (with colour) depending on what was sent in and corresponding to what it was told to draw.
    * @param g Graphics reference variable
   */
  public void draw(Graphics g) {
    g.drawRect(x, y, width, height);
    if (groundID == Value.groundArteryTwo)
      g.drawImage(StageOneState.tileset_ground[Value.groundArtery], x, y, width, height, null);
    else
      g.drawImage(StageOneState.tileset_ground[groundID], x, y, width, height, null);
    
    if(tower != null)
      tower.draw(g);
  }
  
  /**
   * This method is the physics of the game's block component which checks for an empty spot and allows placement of a 
   * new tower on the unoccupied space.
   */
  public void process () {
    if (tower != null)
      tower.process();
    if (airID != Value.airAir && tower == null)
      tower = new Tower(x, y, width, height, airID);
  }
  
  /** This method is the fight physics of the game, against the white blood cell tower.
    * @param g Graphics reference variable.
   */
  public void fight(Graphics g) {
    if (tower != null)
      tower.fight(g);
  }
}