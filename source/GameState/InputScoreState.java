package GameState;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/** This class is the input score screen state, and allows the user to see their end score and submit it to the scores file.
  * @author Kareem Golaub modified by Peter Huang
  * @version 3.0 June 12 2014
 */
public class InputScoreState extends GameState implements ActionListener 
{
  /** holds the image for the title
   */
  JLabel titleInput = new JLabel();
  /** holds the image for the game over image
   */
  JLabel gameOver = new JLabel();
  /** holds the image for the score text sentence
   */
  JLabel yourScoreWas = new JLabel();
  /** holds the image for the score
   */
  JLabel scoreLab = new JLabel();
  /** holds the image for the insert name text
   */
  JLabel insertName = new JLabel();
  /** is the button to submit your score
   */
  JButton submit = new JButton();
  /** is the button to go directly back to main menu
   */
  JButton cancel = new JButton();
  /** contains the score
   */
  int score;
  /** contains the difficulty
   */
  int difficulty;
  /** contains the name of the player
   */
  String name = "Anonymous";
  /** takes in the name of the player
   */
  JTextField nameInput = new JTextField(15);
  
  /** Constructs InputScoreState with a sent in GameStateManager.
    * @param gsm GameStateManager used to control the state of the parent window
   */
  public InputScoreState(GameStateManager gsm) {
    this.gsm = gsm;
  }
  
  /** Sets the size of the pane and frame, and calls the methods that instantiates, positions and adds the window components.
   */
  public void init() {
    gsm.pane.setSize(400, 400);
    SwingUtilities.getWindowAncestor(gsm.pane).pack();
    SwingUtilities.getWindowAncestor(gsm.pane).setLocationRelativeTo(null);
    initLabels();
    setStaticConstraints();
    addComponents();
  }
  
  /** Initializes the components with their pictures, and gives them their attributes.
    * @param font used to hold first font
    * @param font2 used to hold second font
    * @param font3 used to hold the third font
    * @param titleImg ImageIcon used to hold the title image
    * @param cancelImg ImageIcon used to hold the cancel image
    * @param cancelRollImg ImageIcon used to hold the cancel roll image
    * @param submitImg ImageIcon used to hold the submit image
    * @param submitRollImg ImageIcon used to hold the submit roll image
   */
  public void initLabels() {
    Font font = null;
    Font font2 = null;
    Font font3 = null;
    ImageIcon titleImg = new ImageIcon();
    ImageIcon cancelImg = new ImageIcon();
    ImageIcon cancelRollImg = new ImageIcon();
    ImageIcon submitImg = new ImageIcon();
    ImageIcon submitRollImg = new ImageIcon();
    
    try {
      font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/source/resources/fonts/ARBERKLEY.ttf"));
      font2 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/source/resources/fonts/ARBERKLEY.ttf"));
      font3 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/source/resources/fonts/ARBERKLEY.ttf"));
      titleImg = new ImageIcon(getClass().getResource("/source/resources/title2.png"));
      cancelImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Cancel.png"));
      cancelRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Cancel2.png"));
      submitImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Submit.png"));
      submitRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Submit2.png"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    font = font.deriveFont(64f);
    gameOver = new JLabel("Game Over!");  
    gameOver.setFont(font);
    
    font2 = font2.deriveFont(35f);
    yourScoreWas = new JLabel("Your score was:");
    yourScoreWas.setFont(font2);
    
    font3 = font3.deriveFont(64f);
    scoreLab = new JLabel("" + score);
    scoreLab.setFont(font3);
    
    titleInput = new JLabel(titleImg);
    insertName = new JLabel("Enter your name:");
    cancel = new JButton(cancelImg);
    cancel.setRolloverIcon(cancelRollImg);
    cancel.setBorderPainted(false);
    cancel.setContentAreaFilled(false);
    cancel.setFocusPainted(false);
    cancel.setMnemonic(KeyEvent.VK_C);
    
    submit = new JButton(submitImg);
    submit.setRolloverIcon(submitRollImg);
    submit.setBorderPainted(false);
    submit.setContentAreaFilled(false);
    submit.setFocusPainted(false);
    submit.setMnemonic(KeyEvent.VK_S);
    
    cancel.setActionCommand("Cancel");
    submit.setActionCommand("Submit");
    cancel.addActionListener(this);
    submit.addActionListener(this);
  }
  
  /** Sets the constraints for the components in SpringLayout.
   */
  public void setStaticConstraints() {
    gsm.layout.putConstraint(SpringLayout.NORTH, titleInput, 30, SpringLayout.NORTH, gsm.pane);
    gsm.layout.putConstraint(SpringLayout.WEST, titleInput, 60, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, gameOver, 40, SpringLayout.SOUTH, titleInput);
    gsm.layout.putConstraint(SpringLayout.WEST, gameOver, 140, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, submit, 400, SpringLayout.SOUTH, titleInput);
    gsm.layout.putConstraint(SpringLayout.WEST, submit, 90, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, cancel, 400, SpringLayout.SOUTH, titleInput);
    gsm.layout.putConstraint(SpringLayout.WEST, cancel, 30, SpringLayout.EAST, submit);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, yourScoreWas, 10, SpringLayout.SOUTH, gameOver);
    gsm.layout.putConstraint(SpringLayout.WEST, yourScoreWas, 190, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, scoreLab, 5, SpringLayout.SOUTH, yourScoreWas);
    gsm.layout.putConstraint(SpringLayout.WEST, scoreLab, (int)((gsm.pane.getWidth() - scoreLab.getPreferredSize().getWidth())/2), SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, nameInput, 60, SpringLayout.SOUTH, scoreLab);
    gsm.layout.putConstraint(SpringLayout.WEST, nameInput, 270, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, insertName, 62, SpringLayout.SOUTH, scoreLab);
    gsm.layout.putConstraint(SpringLayout.WEST, insertName, 155, SpringLayout.WEST, gsm.pane);
  }
  
  /** Adds in the components to the pane.
   */
  public void addComponents() {
    gsm.pane.add(titleInput);
    gsm.pane.add(submit);
    gsm.pane.add(cancel);
    gsm.pane.add(gameOver);
    gsm.pane.add(yourScoreWas);
    gsm.pane.add(scoreLab);
    gsm.pane.add(nameInput);
    gsm.pane.add(insertName);
  }
  
  /** Takes in the score from the game just played.
    * @param score int holds the score value
   */
  public void setScore(int score) {
    this.score = score;
  }
  
  /** Takes in the difficulty from the game just played.
    * @param diff int holds the value of the difficulty
   */
  public void setDifficulty(int diff) {
    this.difficulty = diff;
  }
  
  /** Overrides paintComponent method from gamestate.
   */
  public void paintComponent(Graphics g) {}
  
  /** Overrides draw method from gamestate.
   */
  public void draw(Graphics2D g) {}
  
  /** Overrides update method from gamestate.
   */
  public void update() {}
  
  /** This method checks if the current score is larger than any of the scores in the scores.txt file, and rewrites accordingly.
    * @param scores int[] holds the scores from the file
    * @param names String[] holds the names from the file
    * @param difficulties String[] holds the difficulties from the file
    */
  public void writeToFile() {
    int[]scores = new int[5];
    String[]names = new String[5];
    String[]difficulties = new String[5];
    String diffString;
    if (difficulty == 0)
      diffString = "Easy";
    else if (difficulty == 1)
      diffString = "Medium";
    else
      diffString = "Hard";
    
    try {
      Scanner scan = new Scanner(new File("scores.txt"));
      for (int x = 0; x < 5; x++) {
        names[x] = scan.next();
        difficulties[x] = scan.next();
        scores[x] = scan.nextInt();
      }
      for (int x = 0; x < 5; x++) {
        if (scores[x] < score) {
          for (int y = x; y < 3; y++) {
            names[x+1] = names[x];
            difficulties[x+1] = difficulties[x];
            scores[x+1] = scores[x];
          }
          scores[x] = score;
          difficulties[x] = diffString;
          names[x] = name;
          break;
        }
      }
      PrintWriter print = new PrintWriter(new FileWriter("scores.txt"));
      for (int x = 0; x < 5; x++)
        print.println(names[x] + " " + difficulties[x] + " " + scores[x]);
      print.close();
    }
    catch(Exception e){
    }
  }
  
  /** This method overrides the abstract method actionPerformed and executes instructions according to what menu item is pressed.
    * @param ae ActionEvent takes in an action event from jbuttons
    */
  public void actionPerformed(ActionEvent ae) {
    if (ae.getActionCommand().equals("Submit")){
      if (!nameInput.getText().equals(""))
        name = nameInput.getText();
      else if (nameInput.getText().length() > 15) {
        name = nameInput.getText();
        name = name.substring(0,16);
      }
      else
        name = "Anonymous";
      Scanner nameScan = new Scanner(name);
      name = "";
      while (nameScan.hasNext())
        name += nameScan.next();
      writeToFile();
      gsm.pane.stateCheck = true;
      gsm.pane.state = gsm.MENUSTATE;
    }
    else {
      gsm.pane.stateCheck = true;
      gsm.pane.state = gsm.MENUSTATE;
    }
    nameInput.setText("");
  }
}