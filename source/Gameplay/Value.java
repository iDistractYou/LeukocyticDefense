/** Description: This class stores the values of all the ground and air ID's to allow tiling the floor and also keeping
  * track of towers in the store, in the game, and keeping track of pathogens, all easier.
  * @author Peter Huang and Kareem Golaub
  * @version 1.3 June 12 2014
  */

package Gameplay;
public class Value
{
  /** groundGrass - static int - Original grass colour tile used in pre-beta testing versions of the game. Holds towers.
    */
  public static int groundGrass = 0;
  /** groundRoad - static int - Original enemy path tile used in pre-beta testing versions of the game. Holds mobs.
    */
  public static int groundRoad = 1;
  /** groundArtery - static int - Red artery ground tile used to represent a path for pathogens.
    */
  public static int groundArtery = 2;
  /** groundArteryTwo - static int - Red artery ground tile used to represent a second path for pathogens.
    */
  public static int groundArteryTwo = 9;
  /** groundTissue - static int - Peach to beige coloured tissue tile that the white blood cells can be placed on.
    */
  public static int groundTissue = 3;
  /** airAir - static int - A clear air tile that means nothing is stored on that tile other than the ground tile.
    */
  public static int airAir = -1;
  /** airFinish - static int - A finish rainbow-like air tile that the enemy disappears on but also takes away your health points.
    */
  public static int airFinish = 0;
  /** airGarbageBin - static int - A garbage bin icon in the store that is used for deselecting purposes.
    */
  public static int airGarbageBin = 1;
  
  /** The purple tower air tile ID that is a basic tower shooting one laser at enemies dealing moderate damage. This tower is used in the pre-beta testing stages of the game.
    */
  public static int airLaserTower = 2;
  /** The is the neutrophil white blood cell tower air tile ID.
    */
  public static int airNeutrophil = 3;
  /** The is the eosinophil white blood cell tower air tile ID.
    */
  public static int airEosinophil = 4;
  /** The is the natural killer white blood cell tower air tile ID.
    */
  public static int airNaturalK = 5;
  /** The is the air tile TD heart where pathogens disappear but decrease one of your health points.
    */
  public static int airFinishHeart = 6;
  /** The is the B Cell white blood cell tower air tile ID.
    */
  public static int airBCell = 7;
  /** The is the T Cell white blood cell tower air tile ID.
    */
  public static int airTCell = 8;
  /** The is the basophil white blood cell tower air tile ID.
    */
  public static int airBasophil = 9;
  /** The is the air tile ID for nothing.
    */
  public static int mobAir = -1;
  /** The is the bacteria pathogen air tile ID.
    */
  public static int bacteria = 0;
  /** The is the parasite pathogen air tile ID.
    */
  public static int parasite = 1;
  /** The is the virus pathogen air tile ID.
    */  
  public static int virus = 2;  
  /** The is the int array storing the bone marrow received for killing a pathogen. Respectively bacteria, virus, and parasite.
    */
  public static int[] deathReward = {2, 4, 6};
}