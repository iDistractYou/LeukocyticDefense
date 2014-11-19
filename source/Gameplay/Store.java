/** Description: This is the store class that items that are sellable such as towers, while also a garbage bin icon
  * which allows deselection. The price of each buyable item is also written from here.
  * @author Kareem Golaub and Peter Huang
  * @version 1.4 June 12 2014
  */

package Gameplay;
import java.awt.*;
import GameState.StageOneState;
import javax.swing.*;
public class Store
{
  /** button - Rectangle array with the size of shopSize (amount of items on the shop menu).
   */
  public Rectangle [] button = new Rectangle [shopSize];
  /** shopSize - static int of the amount of items on the shop menu. These are the shop buttons
   */
  public static int shopSize = 8; 
  /** buttonSize - static int of the size of each shop button (length+width).
   */
  public static int buttonSize = 52;  
  /** cellSpace - static int of the space separating between two adjacent shop buttons.
   */
  public static int cellSpace = 2;
  /** awayFromRoom - static int of the space separating between the game tile play board and the shop buttons.
   */
  public static int awayFromRoom = 25;
  /** iconSize - static int of the size of the heart and bone marrow icons.
   */
  public static int iconSize = 20;
  /** iconSpace - static int of the space separating the icons.
   */
  public static int iconSpace = 6;
  /** itemIn - static int used for additional shop button dimensions, a formatting helper for the relative positioning.
   */
  public static int itemIn = 4;
  /** buttonID - static int array of the image icon (of the corresponding cell) placed on the shop's menu.
   */
  public static int [] buttonID = {Value.airNeutrophil, Value.airEosinophil, Value.airBasophil, Value.airNaturalK, Value.airBCell, Value.airTCell,  Value.airAir, Value.airGarbageBin};
  /** held - static int variable representing if an item is held by cursor or not.
   */
  public static int heldID = -1;
  
  /** buttonHP - Rectangle reference variable for the your health's heart icon on the bottom left of the screen.
   */
  public Rectangle buttonHP;
  /** buttonCoins - Rectangle reference variable for the your bone marrow currency icon on the bottom left of the screen.
   */
  public Rectangle buttonCoins;
  /** coins - Rectangle reference variables to your coins/currency.
   */
  public Rectangle coins;
  /** buttonScore - Rectangle reference variable for the button's score.
   */
  public Rectangle buttonScore;
  /** holdsItem - boolean variable stating if a tower/white blood cell attacker is held at the moment or not.
   */
  public boolean holdsItem = false;

  /** buttonPrice - static int array which are the prices of each corresponding air.ID, the price for purchase of the white blood cell towers.
   */
  public static int [] buttonPrice = {15, 20, 20, 40, 80, 55, 0, 0};
  /** realID - static int used for holding tower purposes.
   */
  public static int realID = -1;
  /** pane - reference variable for JPanel
   */
  JPanel pane;
  
  /** This constructor initializes this class's init method while also passes in the pane to this class's pane.
    * @param pane JPanel reference variable for the pane passed in.
   */
  public Store (JPanel pane)
  {
    this.pane = pane;
    init();
  }
  
  /** This method is responsible for the clicking and holding a tower mechanics.
    * @param i int loop counter
   */
  public void click()
  {
      for (int i = 0; i < button.length; i++)
      {
        if (button[i].contains(StageOneState.mousePoint) && buttonID [i] != Value.airAir)
        {
          if (buttonID[i] == Value.airGarbageBin) //delete item
          {
            holdsItem = false;
            //heldID = Value.airAir;            
          }
          else
          {
            heldID = buttonID[i];
            realID = i;
            holdsItem = true;
          }
        }
      }
      if (holdsItem) //the item must be held by the mouse before clicking to settle down or place the tower!
      {
        if (StageOneState.bank >= buttonPrice[realID]) //heldID is the index of the shop item!
        {
          for (int y = 0; y < StageOneState.map.block.length; y++)
          {
            for (int x = 0; x<StageOneState.map.block[0].length; x++)
            {
              if (StageOneState.map.block[y][x].contains(StageOneState.mousePoint))
              {
                if (StageOneState.map.block[y][x].groundID != Value.groundArtery && StageOneState.map.block[y][x].groundID != Value.groundArteryTwo && StageOneState.map.block[y][x].airID == Value.airAir)
                {
                  StageOneState.map.block[y][x].airID = heldID;
                  StageOneState.bank -= buttonPrice[realID];
                }
              }
            }
          }
        }
      }
  }
  
  /** This is the init method which creates the 8 cells of the shop at the bottom. 
   */
  public void init ()
  {
    //creates the cells of the shop
    for (int i = 0; i < button.length; i++)
      button[i] = new Rectangle(300 + ((buttonSize+cellSpace)*i), (StageOneState.map.block[StageOneState.map.worldHeight-1][0].y) + StageOneState.map.blockSize + awayFromRoom, buttonSize, buttonSize);
    buttonHP = new Rectangle(StageOneState.map.block[0][0].x-1, button[0].y-11, iconSize, iconSize);
    buttonCoins = new Rectangle(StageOneState.map.block[0][0].x-1, button[0].y + button[0].height-iconSize-11, iconSize, iconSize);
    buttonScore = new Rectangle(StageOneState.map.block[0][0].x+735, button[0].y-11, iconSize, iconSize);
  }
  
  /** This is the draw method that does the glowing up of the shop buttons when hovered on. The image for towers is also
    * drawn.
   * @param g Reference variable for Graphics
   * @param i int loop counter
   */
  public void draw(Graphics g)
  {
    for (int i = 0; i<button.length; i++)
    {
      //light up store buttons
      if (button[i].contains(StageOneState.mousePoint)) //if the screen contains the mouse
      {
        g.setColor (new Color(255,255,255,150)); //fourth parameter is transparency
        g.fillRect (button[i].x, button[i].y, button[i].width, button[i].height);
      }
      g.drawImage (StageOneState.tileset_res[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
      if (buttonID[i] != Value.airAir)
      {
        g.drawImage (StageOneState.tileset_air[buttonID[i]], button[i].x + itemIn, button[i].y + itemIn, button[i].width - 2*itemIn, button[i].height - 2*itemIn, null);
      }
      if (buttonPrice [i] > 0)
      {
        g.setColor (Color.WHITE);
        g.setFont (new Font("Courier New", Font.BOLD, 12));
        g.drawString("$" + buttonPrice[i], button[i].x + itemIn, button[i].y + itemIn - 11);
      }
    }
    
    g.drawImage(StageOneState.tileset_res[1],buttonHP.x, buttonHP.y, buttonHP.width, buttonHP.height, null);
    g.drawImage(StageOneState.tileset_res[2],buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height, null);
    g.setFont(new Font("Courier New", Font.BOLD, 14));
    g.setColor (Color.BLACK);
    if (StageOneState.lives < 0)
    g.drawString ("0", buttonHP.x+buttonHP.width+iconSpace, buttonHP.y + buttonHP.height);
    else
      g.drawString ("" + StageOneState.lives, buttonHP.x+buttonHP.width+iconSpace, buttonHP.y + buttonHP.height);
    g.drawString ("" + StageOneState.bank, buttonCoins.x+buttonCoins.width+iconSpace, buttonCoins.y + buttonCoins.height);
    g.drawString("Score: " + StageOneState.score, buttonScore.x + buttonHP.width+iconSpace+20, buttonCoins.y + buttonHP.height - 11);
    g.drawString("" + 1000, 10, 10);
    
    if (holdsItem)
    {
      g.drawImage(StageOneState.tileset_air[heldID], StageOneState.mousePoint.x - ((button[0].width - 2*itemIn)/2) + itemIn, StageOneState.mousePoint.y - ((button[0].height - 2*itemIn)/2) + itemIn, button[0].width - 2*itemIn, button[0].height - 2*itemIn, null);
    }
  }
}