/** Description: This class controls the different screens for the different game difficulties. There is an easy,
  * medium, and hard difficulty.
  * @author Kareem Golaub modified by Peter Huang
  * @version 2.0 June 11 2014 * 
  */

package GameState;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class DifficultyState extends GameState implements ActionListener
{
  /** titleDiff - JLabel reference variable
    */
  JLabel titleDiff = new JLabel();
  /** statement - JLabel reference variable
    */
  JLabel statement = new JLabel();
  /** diffSplash - JLabel reference variable
    */
  JLabel diffSplash = new JLabel();
  /** easy - JButton reference variable to select easy mode.
    */
  JButton easy = new JButton();
  /** medium - JButton reference variable to select medium mode.
    */
  JButton medium = new JButton();
  /** hard - JButton reference variable to select hard mode.
    */
  JButton hard = new JButton();
  /** backDiff - JButton reference variable to go back.
    */
  JButton backDiff = new JButton();
  
  /** This constructor sets the game state manager in the stage one state.
   * @param gsm GameStateManager reference variable.
   */
  public DifficultyState(GameStateManager gsm) {
    this.gsm = gsm;
  }
  
  /** This is the init method that initializes the constraints and the pane's size. It also adds the components.
    */
  public void init() {
    gsm.pane.setSize(400, 400);
    SwingUtilities.getWindowAncestor(gsm.pane).pack();
    SwingUtilities.getWindowAncestor(gsm.pane).setLocationRelativeTo(null);
    initLabels();
    setStaticConstraints();
    addComponents();
  }
  
  /** This method initializes the all of the labels used in to select the difficulty. It loads all the image icons used
    * for the buttons. It also adds the key mnemonics for each button and also activates each button.
    * @param titleImg ImageIcon reference variable.
    * @param easyImg ImageIcon reference variable.
    * @param easyRollImg ImageIcon reference variable, activates when cursor is hovering over its corresponding button.
    * @param mediumImg ImageIcon reference variable.
    * @param mediumRollImg ImageIcon reference variable, activates when cursor is hovering over its corresponding button.
    * @param hardImg ImageIcon reference variable.
    * @param hardRollImg ImageIcon reference variable, activates when cursor is hovering over its corresponding button.
    * @param selectImg ImageIcon reference variable.
    * @param splash ImageIcon reference variable, activates when cursor is hovering over its corresponding button.
    * @param backDiffImg ImageIcon reference variable.
    * @param backDiffRollImg ImageIcon reference variable, activates when cursor is hovering over its corresponding button.
    */
  public void initLabels() {
    ImageIcon titleImg = new ImageIcon();    
    ImageIcon easyImg = new ImageIcon();
    ImageIcon easyRollImg = new ImageIcon();
    ImageIcon mediumImg = new ImageIcon();
    ImageIcon mediumRollImg = new ImageIcon();
    ImageIcon hardImg = new ImageIcon();
    ImageIcon hardRollImg = new ImageIcon();
    
    ImageIcon selectImg = new ImageIcon();
    ImageIcon splash = new ImageIcon();
    
    ImageIcon backDiffImg = new ImageIcon();
    ImageIcon backDiffRollImg = new ImageIcon();    
    try {
      titleImg = new ImageIcon(getClass().getResource("/source/resources/title2.png"));
      easyImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Easy.png"));
      easyRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Easy2.png"));
      mediumImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Medium.png"));
      mediumRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Medium2.png"));
      hardImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Hard.png"));
      hardRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Hard2.png"));
      selectImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Select.png"));
      backDiffImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Back.png"));
      backDiffRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Back2.png"));
      splash = new ImageIcon(getClass().getResource("/source/resources/diffsplash.png"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    titleDiff = new JLabel(titleImg);
    diffSplash = new JLabel(splash);
    
    easy = new JButton(easyImg);
    easy.setRolloverIcon(easyRollImg);
    easy.setBorderPainted(false);
    easy.setContentAreaFilled(false);
    easy.setFocusPainted(false);
    easy.setMnemonic(KeyEvent.VK_A);
    
    medium = new JButton(mediumImg);
    medium.setRolloverIcon(mediumRollImg);
    medium.setBorderPainted(false);
    medium.setContentAreaFilled(false);
    medium.setFocusPainted(false);
    medium.setMnemonic(KeyEvent.VK_S);
    
    hard = new JButton(hardImg);
    hard.setRolloverIcon(hardRollImg);
    hard.setBorderPainted(false);
    hard.setContentAreaFilled(false);
    hard.setFocusPainted(false);
    hard.setMnemonic(KeyEvent.VK_D);
    
    backDiff = new JButton(backDiffImg);
    backDiff.setRolloverIcon(backDiffRollImg);
    backDiff.setBorderPainted(false);
    backDiff.setContentAreaFilled(false);
    backDiff.setFocusPainted(false);
    backDiff.setMnemonic(KeyEvent.VK_B);
    
    easy.setActionCommand("Easy");
    medium.setActionCommand("Medium");
    hard.setActionCommand("Hard");
    
    easy.addActionListener(this);
    medium.addActionListener(this);
    hard.addActionListener(this);
    backDiff.addActionListener(this);
  }
  
  /** 
   * This method sets the static constraints for each button and label. A spring layout is used.
   */
  public void setStaticConstraints() {
    gsm.layout.putConstraint(SpringLayout.NORTH, titleDiff, 30, SpringLayout.NORTH, gsm.pane);
    gsm.layout.putConstraint(SpringLayout.WEST, titleDiff, 60, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, easy, 210, SpringLayout.SOUTH, titleDiff);
    gsm.layout.putConstraint(SpringLayout.WEST, easy, 200, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, medium, 5, SpringLayout.SOUTH, easy);
    gsm.layout.putConstraint(SpringLayout.WEST, medium, 200, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, hard, 5, SpringLayout.SOUTH, medium);
    gsm.layout.putConstraint(SpringLayout.WEST, hard, 200, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, backDiff, 20, SpringLayout.SOUTH, hard);
    gsm.layout.putConstraint(SpringLayout.WEST, backDiff, 210, SpringLayout.WEST, gsm.pane);
    
    gsm.layout.putConstraint(SpringLayout.NORTH, diffSplash, 60, SpringLayout.SOUTH, titleDiff);
    gsm.layout.putConstraint(SpringLayout.WEST, diffSplash, 250, SpringLayout.WEST, gsm.pane);
  }
  
  /**
   * This method adds all the JLabel and JButton components onto the screen.
   */
  public void addComponents() {
    gsm.pane.add(titleDiff);
    gsm.pane.add(easy);
    gsm.pane.add(medium);
    gsm.pane.add(hard);
    gsm.pane.add(backDiff);
    gsm.pane.add(diffSplash);
  }
  
  /**
   * This is an overriden method that has no implementation.
   */
  public void update() {
  }
  
  /**
   * This is an overriden method that has no implementation.
   * @param g Graphics2D reference variable
   */
  public void draw(Graphics2D g) {
  }
  
  /**
   * This is an overriden method that has no implementation.
   * @param g Graphics reference variable
   */
  public void paintComponent(Graphics g) {
  }
  
  /**
   * This the action performed method that defines the action of each button. Easy, medium, hard, and back are the 
   * choices.
   * @param ae ActionEvent reference variable
   */
  public void actionPerformed(ActionEvent ae) {
    if (ae.getActionCommand().equals("Easy"))
    {
      StageOneState.difficulty = 1;
      gsm.pane.stateCheck = true;
      gsm.pane.state = gsm.STAGEONESTATE;
    }
    else if (ae.getActionCommand().equals("Medium"))
    {
      StageOneState.difficulty = 2;
      gsm.pane.stateCheck = true;
      gsm.pane.state = gsm.STAGEONESTATE;
    }
    else if (ae.getActionCommand().equals("Hard"))
    {
      StageOneState.difficulty = 3;
      gsm.pane.stateCheck = true;
      gsm.pane.state = gsm.STAGEONESTATE;
    }
    else
    {
      gsm.pane.stateCheck = true;
      gsm.pane.state = gsm.MENUSTATE;
    }
  }
}