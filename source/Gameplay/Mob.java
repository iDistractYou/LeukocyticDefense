/** Description: This is the mob class that contains all the game play materials for the enemy pathogens. It includes its
  * speed, walk mechanics within the four direction travelling blood stream, its health, and the mechanics of coming in 
  * clusters.
  * @author Peter Huang and Kareem Golaub
  * @version 1.5 June 12 2014
  */

package Gameplay;
import java.awt.*;
import GameState.StageOneState;

public class Mob extends Rectangle
{
  /** mobSize - int variable of the mob's size (length+width).
   */
  public int mobSize = 52;
  /** mobID - int variable storing the mob's ID to distinguish which mob is being referred to.
   */
  public int mobID;
  /** inGame - boolean variable preset to false to check if a mob has spawned in the game screen or not.
   */
  public boolean inGame = false;
  /** mobWalk - int variable storing how long the mob has walked.
   */
  public int mobWalk = 0; 
  /** UPWARD - final int that keeps track of the upward direction that a mob can move towards and into.
   */
  public final int UPWARD = 0;
  /** DOWNWARD - final int that keeps track of the downward direction that a mob can move towards and into.
   */
  public final int DOWNWARD = 1;
  /** RIGHT - final int that keeps track of the right direction that a mob can move towards and into.
   */
  public final int RIGHT = 2;
  /** LEFT - final int that keeps track of the left direction that a mob can move towards and into.
   */
  public final int LEFT = 3;
  /** maxHealth - int storing the max percentile health of a mob.
   */
  public int maxHealth = 100;
  
  //boolean variables to check if the mob has moved ____ position
  /** hasUpward - boolean variable that keeps track if a mob has moved upwards. It basically tracks where its previous block was so it doesnt move back and forth going nowhere.
   */
  public boolean hasUpward = false;
  /** hasUpward - boolean variable that keeps track if a mob has moved downwards. It basically tracks where its previous block was so it doesnt move back and forth going nowhere.
   */
  public boolean hasDownward = false;  
  /** hasUpward - boolean variable that keeps track if a mob has moved right. It basically tracks where its previous block was so it doesnt move back and forth going nowhere.
   */
  public boolean hasRight = false;
  /** hasUpward - boolean variable that keeps track if a mob has moved left. It basically tracks where its previous block was so it doesnt move back and forth going nowhere.
   */
  public boolean hasLeft = false;
  /** direction - int variable storing the default direction of that the mob travels in, which is travelling right, into the screen.
   */
  public int direction = RIGHT;
  /** xCoord - int variable tracking down the x coordinate location of the mob.
   */
  public int xCoord;
  /** yCoord - int variable tracking down the y coordinate location of the mob.
   */
  public int yCoord;
  /** health - int variable storing the mob's health points.
   */
  public int health;
  /** health - int variable storing the mob's health gap, used for visual and aesthetic purposes.
   */
  public int healthGap = 3;
  /** moveFrame - int variable acting as a delay for the mob so the mob has a frame and a speed.
   */
  public int moveFrame = 0;
    /** moveFrame - static int variable of the mob's speed.
   */
  public static int mobSpeed;
  /** position - int storing the position of the mob.
   */
  int position;
  /** pathValue - int storing the path value such as if it is able for the mob to walk on or not, eg muscle cells 
    * cannot be walked on by mobs but blood vessel tiles can!
    */
  int pathValue;
  
  /** Constructor for the mob class taking in the path and mobID parameters. This distinguishes what kind of mob will
    * be travelling though in this level and the path they will be taking. Mobs will have different healths according
    * to what type the mob is and the difficulty the user selected.
    * @param path int for the upper or lower path the mob will take.
    * @param mobID int for the type of mob spawning in this level.
   */
  public Mob(int path, int mobid) {
    this.pathValue = path;
    this.mobID = mobid;
    if (mobID == Value.bacteria) {
      if (StageOneState.difficulty == 1)
        health = 150;
      else if (StageOneState.difficulty == 2)
        health = 300;
      else
        health = 390;
    }
    else if (mobID == Value.parasite) {
      if (StageOneState.difficulty == 1)
        health = 200;
      else if (StageOneState.difficulty == 2)
        health = 390;
      else
        health = 470;
    }
    else {
      if (StageOneState.difficulty == 1)
        health = 300;
      else if (StageOneState.difficulty == 2)
        health = 400;
      else
        health = 530;
    }
    if (StageOneState.difficulty == 1)
      mobSpeed = 20;
    else if (StageOneState.difficulty == 2)
      mobSpeed = 15;
    else
      mobSpeed = 10;
    maxHealth = this.health;
  }
  
  /** This method initializes the mob to move, and how long it takes for a mob to first appear on the game play screen.
    * @param y int for loop counter
   */
  public void initMob() {
    StageOneState.spawnTime = 900; //so after one enemy there won't be a huge spawnTime
    //StageOneState.spawnTime = 9001 - ((StageOneState.level-1) * 333); //so after one enemy there won't be a huge spawnTime
    
    for (int y = 0; y < StageOneState.map.block.length; y++)
    {
      if (StageOneState.map.block[y][0].groundID == pathValue)
      {
        setBounds(StageOneState.map.block[y][0].x, StageOneState.map.block[y][0].y, mobSize, mobSize);
        xCoord = 0;
        yCoord = y;
        break;
      }
    }
    //setBounds(10,10,100,100); //test to see if mob spawns    
    inGame = true;
    //System.out.println(this.health);
  }
  
  /** This method deletes the mob and resets the default direction for the next mob spawning, to move in. Such as when
    * mobs are quarantined by towers or just gone to the finish line and taken away one of the player's health.
   */
  public void deleteMob() {
    inGame = false;
    direction = RIGHT; //so the next mob won't move weirdly
    mobWalk = 0;
  }
  
  /** This method allows the YOU to lose health points from the mobs reaching the finish line!
   */
  public void loseHealth() {
    StageOneState.lives--;
  }  
  
  /**This method is responsible for the mob's movement mechanics. It makes sure it walks continuously to the finish line
    * and not back and forth, as it actually knows how to determine a path of travel!
    * @ e Exception reference variable
    */
  public void process() {
    if (moveFrame >= mobSpeed) {
      if (direction == RIGHT)
      {
        x++;
      }
      else if (direction == UPWARD)
      {
        y--;
      }
      else if (direction == DOWNWARD)
      {
        y++;
      }
      else
      {
        x--;
      }
      
      mobWalk++;
      //does another check, _____ coordinate is incremented by one, so it keeps track of the mob's location
      if (mobWalk == StageOneState.map.blockSize) 
      {
        if (direction == RIGHT) {
          xCoord++;
          hasRight = true;
        }
        else if (direction == UPWARD) {
          yCoord--;
          hasUpward = true;
        }
        else if (direction == DOWNWARD) {
          yCoord++;
          hasDownward = true;
        }
        else {
          if (direction == LEFT) {
            xCoord--;
            hasLeft = true;
          }
        }
        
        //prints out coordinates to double check location
        //System.out.println(yCoord+ " : " + "[Y], ");
        
        //try and catch for each to make sure the mob moves within the specified path
        
        if (!hasUpward)
        {
          try{
            if (StageOneState.map.block[yCoord+1][xCoord].groundID == pathValue)
            {
              direction = DOWNWARD;
            }
          } catch (Exception e) {e.printStackTrace();}
        }     
        
        if (!hasDownward)
        {
          try
          {
            if (StageOneState.map.block[yCoord-1][xCoord].groundID == pathValue)
            {
              direction = UPWARD;
            }
          } catch (Exception e) {e.printStackTrace();}
        } 
        
        if (!hasLeft)
        {
          try
          {
            if (StageOneState.map.block[yCoord][xCoord+1].groundID == pathValue)
            {
              direction = RIGHT;
            }
          } catch (Exception e) {e.printStackTrace();}
        }
        
        if (!hasRight)
        {
          try
          {
            if (StageOneState.map.block[yCoord][xCoord-1].groundID == pathValue)
            {
              direction = LEFT;
            }
          } catch (Exception e) {e.printStackTrace();}
        }
        
        //check if the mob is at the finish
        if (StageOneState.map.block[yCoord][xCoord].airID == Value.airFinishHeart)
        {
          deleteMob();
          loseHealth();
        }
        
        //reset
        hasUpward = false;
        hasDownward = false;
        hasRight = false;
        hasLeft = false;
        mobWalk = 0;
      }
      moveFrame = 0;
    }
    else
      moveFrame ++;
  }
  
  /** This method subtracts from the mob's health every consecutive millisecond it is zapped or striken by a surrounding tower.
    * This method also invokes the checkDeath method to see if the mob has died or not, so it can disappear.
    * @param amount int variable of the health the mob will lose.
   */
  public void loseHealth(int amount) {
    this.health -= amount;
    checkDeath();
  }
  
  /** This method checks if the mob ran out of health, and if so, the bone marrow currency and score has been increased.
   *
   */
  public void checkDeath() {
    if (health <= 0)
    {
      StageOneState.killed++;
      getMoney(mobID);
      getScore(mobID);
      //System.out.println ("" + StageOneState.killed);
      deleteMob();
      StageOneState.hasWon();
    }
  }
  
  /** This method returns if a mob is dead or not by checking if it is still in the game or not.
   */
  public boolean isDead() {
    if (inGame)
      return false;
    return true;
  }
  
  /** This method is responsible for adding on to the players bank of bone marrow when a mob is quarantined.
    * @param mobID int storing which mob it is that was killed and required the money information from.
   */
  public void getMoney(int mobID) {
    StageOneState.bank += Value.deathReward[mobID];
  }
  
  /** This method is responsible for adding on to the player's score when a mob is quarantined.
    * @param mobID int storing which mob it is that was killed and required the score it gives out.
   */
  public void getScore(int mobID) {
    if (mobID == Value.bacteria)
      StageOneState.score += 20;
    else if (mobID == Value.parasite)
      StageOneState.score += 30;
    else
      StageOneState.score += 50;
  }
  
  /** This method draws the health bar above the mob.
    * @param g Graphics reference variable.
   */
  public void draw(Graphics g) {
    double widthDraw = width;
    double maxHealthDraw = maxHealth;
    double healthDraw = this.health;
    g.drawImage(StageOneState.tileset_mob[mobID], x, y, width, height, null);
    
    //missing health
    g.setColor (new Color(233, 14, 15));
    g.fillRect(x, y-2*healthGap, width, healthGap*2);
    
    //health
    g.setColor (new Color(50, 180, 50));
    g.fillRect(x, y-2*healthGap, (int)((widthDraw/maxHealthDraw)*healthDraw), healthGap*2);
    
    //outline
    g.setColor (Color.BLACK);
    g.drawRect(x, y-2*healthGap, width, healthGap*2);
  }
}