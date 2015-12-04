
package project3;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
 
public class CasinoDriver {
    
	public int Play;
	static BlackJackDriver blackjack = new BlackJackDriver();		//references the blackjack class
	static WarDriver war = new WarDriver();							//references the war class
	static Craps craps = new Craps();								//references the craps class
	static introduction intro = new introduction();
	
   private JFrame mainFrame;
   private JLabel headerLabel;
//   private JLabel statusLabel;
   private JPanel controlPanel;

   public int betamount=0;
   
   public CasinoDriver(){
      prepareGUI();
   }

   public static void main(String[] args){
//	   @SuppressWarnings({ "static-access", "unused" })
//		String newname = intro.GetName();
	   
      CasinoDriver  swingControlDemo = new CasinoDriver();      
      swingControlDemo.gamebuttons();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Totally Not Indian Casino Name");
      mainFrame.setSize(400,300);
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.setLocation(100, 150);
  
      headerLabel = new JLabel("", JLabel.CENTER);        
//      statusLabel = new JLabel("status",JLabel.CENTER);    

//      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
//      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   public void gamebuttons(){
	   
	   	@SuppressWarnings("static-access")
		String newname = intro.GetName();

      headerLabel.setText(newname + ", what game do you want to play?"); 

      JButton War = new JButton("War");        
      JButton Blackjack = new JButton("BlackJack");
      JButton Craps = new JButton("Craps");
      JButton cancel = new JButton("Cancel");
     

      War.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	war.runwargame();
//        	mainFrame.setVisible(false);
         }          
      });

      Blackjack.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 try {blackjack.runthegame();} catch (IOException e1) {e1.printStackTrace();}
//        	 mainFrame.setVisible(false);
         }
      });
      
      Craps.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e){
    		  craps.runcraps2();
    	  }
      });
      
     
      cancel.addActionListener(new ActionListener(){
    	  public void actionPerformed(ActionEvent e){
    		  System.exit(0);
    	  }
      });

      controlPanel.add(War);
      controlPanel.add(Blackjack);
      controlPanel.add(Craps);
      controlPanel.add(cancel);

      mainFrame.setVisible(true);  
      
 
   }
   
}//end class PlayGameButtons
