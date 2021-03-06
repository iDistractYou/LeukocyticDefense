/**
 * Description: This class is the quiz screen between stages in Leukocytic defense. It contains all the buttons, labels,
 * image icons, action listeners, fonts, and is also responsible for increasing the player's "bone marrow" which are
 * currency, and their score. It reads from the bioquiz set of text files.
 * 
 * @author Peter Huang and Kareem Golaub
 * @version 1.1 June 11th, 2014
 */

package Gameplay;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.SpringLayout.*;
import GameState.StageOneState;

public class QuizScreen extends JFrame implements ActionListener
{
  /** pane - reference variable to JPanel. 
    */
  JPanel pane = new JPanel();
  /** done - boolean variable used to indicate when the quiz screen should finish or not.
    */
  boolean done = false;
  /** trueOption - JButton reference variable for the true button in the true and false set of questions. 
    */
  JButton trueOption = new JButton("True");
  /** falseOption - JButton reference variable for the false button in the true and false set of questions. 
    */
  JButton falseOption = new JButton("True");
  /** continueButton - JButton reference variable for the continue button to proceed to the nest stage. 
    */
  JButton continueButton = new JButton("Continue");
  /** correctAnswer - JLabel reference variable for the correct answer screen message. 
    */
  JLabel correctAnswer = new JLabel ("Correct Answer! Bone Marrow increased by 100 and score increased by 500!");
  /** incorrectAnswer - JLabel reference variable for the incorrect answer screen message. 
    */
  JLabel incorrectAnswer = new JLabel ("Sorry, your answer was incorrect. Better luck next time.");
  /** incorrectAnswer - SpringLayout reference variable for the layout used in this pane. 
    */
  SpringLayout layoutz;
  /** answer - String variable storing the true or false answer from the text file. 
    */
  String answer = "No answer yet.";
  /** postAnswer - String variable storing the true or false post answer or additional information, from the text file. 
    */
  JLabel postAnswer;
  
  /** This is the constructor of the QuizScreen class which sets up the dimensions, background colour, initial buttons,
    * the pane, where it starts off (location) in the screen, and also adds a window listener.
    */
  public QuizScreen() {
    super("Quiz Screen");
    pane.setPreferredSize(new Dimension(850,400));
    pane.setBackground(new Color(114,222,149));
    layoutz = new SpringLayout();
    pane.setLayout(layoutz);
    add(pane);
    pack();
    setLocationRelativeTo(null);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        done = true;
      }
    });
    initLabels();
    loadQuizScreen();
    setVisible(true);
  }
  
  /** Initializes all the labels and buttons with their pictures, along with rollovers. ActionCommands and ActionListeners
    * are also added here.
    * <p><b>Variable Dictionary: Reference, Type, Purpose</b>
    * <ul>
    * <li>trueImg, ImageIcon, holds the true button image
    * <li>trueRollImg, ImageIcon, holds the true button rollover image
    * <li>falseImg, ImageIcon, holds the false button image
    * <li>falseRollImg, ImageIcon, holds the false button rollover image
    * <li>continueImg, ImageIcon, holds the continue button image
    * <li>continueRollImg, ImageIcon, holds the continue button rollover image
    * </ul>
    * @param e Nullpointer Exception reference
    */
  public void initLabels()
  {
    ImageIcon trueOptionImg = new ImageIcon();
    ImageIcon trueRollImg = new ImageIcon();
    ImageIcon falseOptionImg = new ImageIcon();
    ImageIcon falseRollImg = new ImageIcon();
    ImageIcon continueImg = new ImageIcon();
    ImageIcon continueRollImg = new ImageIcon();
    
    try 
    {
      trueOptionImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/True.png"));
      trueRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/True2.png"));
      falseOptionImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/False.png"));
      falseRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/False2.png"));
      continueImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Continue.png"));
      continueRollImg = new ImageIcon(getClass().getResource("/source/resources/Menu Options/Continue2.png"));
    }
    catch (NullPointerException e) {
      e.printStackTrace();
    }
    
    trueOption = new JButton(trueOptionImg);
    trueOption.setFocusPainted(false);
    trueOption.setRolloverIcon(trueRollImg);
    trueOption.setBorderPainted(false);
    trueOption.setContentAreaFilled(false);
    trueOption.setMnemonic(KeyEvent.VK_T);
    
    falseOption = new JButton(falseOptionImg);
    falseOption.setFocusPainted(false);
    falseOption.setRolloverIcon(falseRollImg);
    falseOption.setBorderPainted(false);
    falseOption.setContentAreaFilled(false);
    trueOption.setMnemonic(KeyEvent.VK_F);
    
    continueButton = new JButton(continueImg);
    continueButton.setFocusPainted(false);
    continueButton.setRolloverIcon(continueRollImg);
    continueButton.setBorderPainted(false);
    continueButton.setContentAreaFilled(false);
    trueOption.setMnemonic(KeyEvent.VK_C);
    
    trueOption.setActionCommand("True");
    falseOption.setActionCommand("False");
    continueButton.setActionCommand("Continue");
    
    trueOption.addActionListener(this);
    falseOption.addActionListener(this);
    continueButton.addActionListener(this);
  }
  
  /**
   * This is the method that reads from a file and loads the screen components such as the question, true and false buttons, 
   * and along with setting the fonts used. Button relative locations are also set up here. Math.random() is used to generate
   * a random question each time.
   * @param font Font reference variable, bigger font used.
   * @param font2 Font reference variable, smaller font used.
   * @param questionNumber int storing which question is being asked.
   * @param question1 JLabel reference for the question selected to be asked.
   * @param read Scanner reference variable.
   * @param e Exception reference variable.
   */
  public void loadQuizScreen()
  {
    Font font = null;
    Font font2 = null;
    int questionNumber;
    questionNumber = (int)(Math.random()*18);
    JLabel question1;     
    
    try {
      Scanner read = new Scanner(new File("source/bioquiz.txt"));
      font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/source/resources/fonts/ARBERKLEY.ttf"));
      font = font.deriveFont(25f);
      
      font2 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/source/resources/fonts/ARBERKLEY.ttf"));
      font2 = font.deriveFont(19f);
      
      for(int i = 0; i < questionNumber; i++)
      {
        for(int j = 0; j < 3; j++)
        {
          read.nextLine();
        }
      }
      question1 = new JLabel("" + read.nextLine()); //line n+1
      answer = read.nextLine(); //line n+2 
      postAnswer = new JLabel("" + read.nextLine()); //line n+3
    }
    catch (Exception e) {
      e.printStackTrace();
      question1 = new JLabel("<corrupted file??>");
      postAnswer = new JLabel("<corrupted file??>");
    }   
    
    try
    {
      question1.setFont(font);
      postAnswer.setFont(font2);
      correctAnswer.setFont(font2);
      incorrectAnswer.setFont(font2);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    layoutz.putConstraint(SpringLayout.NORTH, question1, 25, SpringLayout.NORTH, pane/*top part of pane*/);
    layoutz.putConstraint(SpringLayout.WEST, question1, (int)(pane.getWidth()-question1.getPreferredSize().getWidth())/2, SpringLayout.WEST, pane/*west part of pane*/);
    pane.add(question1);
    
    layoutz.putConstraint(SpringLayout.NORTH, trueOption, 100, SpringLayout.NORTH, pane);
    layoutz.putConstraint(SpringLayout.WEST, trueOption, (int)(pane.getWidth()/4-trueOption.getPreferredSize().getWidth()/2), SpringLayout.WEST, pane);
    pane.add(trueOption);
    
    layoutz.putConstraint(SpringLayout.NORTH, falseOption, 100, SpringLayout.NORTH, pane);
    layoutz.putConstraint(SpringLayout.WEST, falseOption, (int)((pane.getWidth()/4)*3-falseOption.getPreferredSize().getWidth()/2), SpringLayout.WEST, pane);
    pane.add(falseOption);
    
    layoutz.putConstraint(SpringLayout.NORTH, correctAnswer, 170, SpringLayout.NORTH, pane);
    layoutz.putConstraint(SpringLayout.WEST, correctAnswer, (int)(pane.getWidth()-correctAnswer.getPreferredSize().getWidth())/2, SpringLayout.WEST, pane);
    
    layoutz.putConstraint(SpringLayout.NORTH, incorrectAnswer, 170, SpringLayout.NORTH, pane);
    layoutz.putConstraint(SpringLayout.WEST, incorrectAnswer, (int)(pane.getWidth()-incorrectAnswer.getPreferredSize().getWidth())/2, SpringLayout.WEST, pane);
    
    layoutz.putConstraint(SpringLayout.NORTH, postAnswer, 240, SpringLayout.NORTH, pane);
    layoutz.putConstraint(SpringLayout.WEST, postAnswer, (int)(pane.getWidth()-postAnswer.getPreferredSize().getWidth())/2, SpringLayout.WEST, pane);
    
    layoutz.putConstraint(SpringLayout.NORTH, continueButton, 333, SpringLayout.NORTH, pane);
    layoutz.putConstraint(SpringLayout.WEST, continueButton, (int)(pane.getWidth()-continueButton.getPreferredSize().getWidth())/2, SpringLayout.WEST, pane);    
  }
  
  /**
   * This method remove the function of the true and false buttons after the player selects his or her choice. 
   */
  public void removeTrueFalseOptions()
  {
    pane.remove(trueOption);
    pane.remove(falseOption);
  }
  
  /**
   * This is the actionPerformed class which contains all of the commands each button do when pressed. If the answer
   * selected and the correct answer match, the player gains bone marrow and score. If the answer selected is incorrect,
   * the user gains nothing but a message is still shown. Else, the continue button would be clicked and that would
   * continue on into the next stage of the game.
   * 
   * @param ae ActionEvent reference variable.
   */
  public void actionPerformed(ActionEvent ae) {
    if (ae.getActionCommand().equals("True")) {
      if (answer.equalsIgnoreCase("true")) {
        pane.add(correctAnswer);
        StageOneState.bank += 100;
        StageOneState.score += 500;
        System.out.print ("You answered correctly and your coins/exp increased" + StageOneState.bank + " and " + StageOneState.score);      
      }
      else {
        pane.add(incorrectAnswer);
        System.out.print ("You answered incorrectly");
      }
      removeTrueFalseOptions();
      pane.add(postAnswer);
      pane.add(continueButton);
      //revalidate();
      //done = true; 
    }
    else if (ae.getActionCommand().equals("False")) {
      removeTrueFalseOptions();
      pane.add(postAnswer);
      pane.add(continueButton);
      if (answer.equalsIgnoreCase("false"))
      {
        pane.add(correctAnswer);
        StageOneState.bank += 100;
        StageOneState.score += 500;
        System.out.print ("You answered correctly and your coins/exp increased" + StageOneState.bank + " and " + StageOneState.score);
      }
      else
      {
        pane.add(incorrectAnswer);
        System.out.print ("You answered incorrectly");
      }      
      
      //revalidate();
    }
    else {
      done = true;
      dispose();
    }
    validate();
  }
  
  /**
   * This method returns if done is true or false. If done the window will close and go back to the game screen's window.
   */
  public boolean isDone() {
    return done;
  }
}