// Introduction class, contains GameStart, Get/SetName. 
// Image in a JFrame re: StackOverflow

package project3;

import java.io.IOException;
import java.security.SecureRandom;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class introduction {
	
	public static final SecureRandom randomName = new SecureRandom(); 
	
	@SuppressWarnings("unused")
	public static void main(String [] args) throws IOException {
			
		GameStart();
		String newname = GetName();  
		
	}
	
	public static void GameStart() throws IOException {
	
				        
		//puts the image in the "label" part
		JLabel label = new JLabel(new ImageIcon("title.png"));
				        
		JFrame title = new JFrame();
		title.add(label);
		title.getContentPane().add(label);
		title.pack();
		title.setLocationRelativeTo(null);
		label.repaint();
		title.setVisible(true); 
	}
	
	public static void WarGameStart() throws IOException {
				        
		//puts the image in the "label" part
		JLabel label2 = new JLabel(new ImageIcon("WarTitle.png"));
				        
		JFrame title2 = new JFrame();
		title2.add(label2);
		title2.getContentPane().add(label2);
		title2.pack();
		title2.setLocationRelativeTo(null);
		label2.repaint();
		title2.setVisible(true);
		
	}
	
	
	public static void CrapsGameStart() throws IOException {
        
		//puts the image in the "label" part
		JLabel label3 = new JLabel(new ImageIcon("craps.png"));
				        
		JFrame title3 = new JFrame();
		title3.add(label3);
		title3.getContentPane().add(label3);
		title3.pack();
		title3.setLocationRelativeTo(null);
		label3.repaint();
		title3.setVisible(true);
		
	}
	
//	public static void GameEnd(){title.setVisible(false);}//end GameEnd
	public static void sleep(){try{Thread.sleep(1500);}catch(Exception e){};}
	
	
	
	
	
	
	
	
	
	
	public static String GetName() {
		String newname; 
		
		JOptionPane.showMessageDialog(null, "This is a high stakes game. An alias is encouraged.","New Name",JOptionPane.INFORMATION_MESSAGE);
		
		String[] NameOptions = { "Enter Name", "Random Name" };

		// prompts user to either enter their own name or a random name 
	    int NewName = JOptionPane.showOptionDialog(null, "What would you like to do?", "Prompt",
	        JOptionPane.ERROR_MESSAGE, 3, null, NameOptions, NameOptions[0]); 
	    
	    if (NewName == JOptionPane.CLOSED_OPTION){System.exit(0);}
	    
	    if (NewName == 1) {
	    	String[] name = {"Randy", "Tod", "Michael", "Drew", "Cole", "Alvin", "Emma", "Leanne", "Brenda", "Cher", "Mary", "Julia"};
		
	    	int first = randomName.nextInt(12);
	    	newname = name[first];
		
	    	JOptionPane.showMessageDialog(null, "Your new identity is " + newname + ".","New Name",JOptionPane.INFORMATION_MESSAGE); // assigns a random name
	    }	
		
	    else {
	    	String [] CheckOptions = {"Yes", "No"};
	    	int CheckName; 
	    	do {newname = JOptionPane.showInputDialog("Please enter your new identity:");
	    
	    		CheckName = JOptionPane.showOptionDialog(null, "You entered the name " + newname + ". Is this okay?", "Prompt",
		        JOptionPane.ERROR_MESSAGE, 3, null, CheckOptions, CheckOptions[0]); } // allows user to enter their own name
	    	
	    	while (CheckName == 1); }
	    
		return(newname);
	}
}
