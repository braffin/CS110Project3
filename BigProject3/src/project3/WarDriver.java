package project3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

//import java.io.IOException;
//import java.util.Random;
//import java.util.Scanner;
import javax.swing.JOptionPane;



public class WarDriver extends CardCreator{

	private enum Status {WON, LOST, WAR};
	private enum PlayAgain {YES, NO};
	
	static Formatter output;	//outputs text to a file
	static Scanner input;		//inputs text from a file
	
	static int playerhandvalue=0; 	//total value of all cards in players hand
	static int dealerhandvalue=0;	//total value of all cards dealt to the dealer
	static int cardvalue;			//value of card dealt
	static int losecount;			//counts of games lost
	static int wincount;			//counts of games won
	
	static Status gamestatus;		//gamestatus for player to continue game
	static PlayAgain againstatus;			//status for player to play again
	static CardCreator getcardface = new CardCreator(); //reference to get to the class CardCreator
	static introduction intro = new introduction();		//references the introduction class
	static String newname;
	
	public void main(String[] args) {runwargame();}
	@SuppressWarnings("static-access")
	public void runwargame(){
		
		try {intro.WarGameStart();} catch (IOException e1) {e1.printStackTrace();}
		//PlayPrompt();
		
		againstatus=PlayAgain.YES;
		for(;againstatus==PlayAgain.YES;){
			playerhandvalue = 0; dealerhandvalue = 0; 
					
			
			
//			OPEN THE FILES AND SHIT HERE
			try{input = new Scanner(Paths.get("warlog.txt"));}
			catch(IOException ioException){System.err.println("Error opening file1. Terminating.");System.exit(1);}
			

			
//			READ THE SHIT FROM THE FILE RIGHT HERE
			wincount=input.nextInt();losecount=input.nextInt();
			
		
			do{
				WarGame();
//				System.out.println("\nThe Game is Continuing \n");
		
				try{Thread.sleep(1500);}catch(Exception e){};
		

//				System.out.println("Player's Current Hand is: " + playerhandvalue);
//				System.out.println("Dealer's Current Hand is: " + dealerhandvalue);
				if(playerhandvalue > dealerhandvalue){gamestatus = Status.WON;}
				if(playerhandvalue < dealerhandvalue){gamestatus = Status.LOST;}
				if(playerhandvalue == dealerhandvalue){gamestatus = Status.WAR;}
				
				} // end while loop
			while(gamestatus==Status.WAR);
		
			while(gamestatus==Status.WON){wincount++;System.out.println("Player Wins!"); break;}
			while(gamestatus==Status.LOST){losecount++;System.out.println("Player Loses!"); break;}
		
			System.out.println("\nGames Won: " + wincount + "\nGames Lost: " + losecount);
			
//			CLOSE THE READING FILE
			if (input != null)
				input.close();
			
			
//			OPEN THE WRITING FILE SSHITE RIGHT HERE
			try{output = new Formatter("warlog.txt");}//open the file
			catch(SecurityException securityException)
				{System.err.println("Write permission denied. Terminating.");System.exit(1);}//terminates the program
			catch(FileNotFoundException fileNotFoundException)
				{System.err.println("Error opening file2. Terminating.");System.exit(1);}//terminate the program
			
			
//			WRITE THE SHIT TO THE FILE RIGHT HERE
			try{output.format(wincount + "%n" + losecount);} //should write to file
			catch(FormatterClosedException formatterClosedException)
			{System.err.println("Error writing to file. Terminating.");}
			
			
//			CLOSE THE WREITING FILES AND SHIT HERE

			if (output != null)
				output.close();
		
			PlayAgainPrompt();
		}//end for loop
	

	}//end void main

	
	
	
	
	
	
	//"Do you want to play" Buttons Prompt
		@SuppressWarnings("unused")
		private static void PlayPrompt(){
			String[] buttons = {"Yes", "No"}; 
			int play = JOptionPane.showOptionDialog(null, newname + ", do you want to play War?","Play Prompt", 
					JOptionPane.PLAIN_MESSAGE, 3, null, buttons, buttons[0]);
			
			if(play==1){System.exit(0);}
			if (play == JOptionPane.CLOSED_OPTION){System.exit(0);} //JOptionPane.; EXIT PROGRAM ON CLOSE
			
		}//end PlayGame method
		
		
		
		
		
	//War Game Method
		private static void WarGame(){
			cardvalue = getcardface.CardValue();
			playerhandvalue = cardvalue;
			String cardface = getcardface.CardFace();
			
			System.out.println("Player plays the " + cardvalue + " of " + cardface);
			
			
			cardvalue = getcardface.CardValue();
			dealerhandvalue = cardvalue;
			cardface = getcardface.CardFace();
			System.out.println("CPU plays the " + cardvalue + " of " + cardface);
			
			return;
			
		}//end WarGame
		
		
		
		
		
		
		
		public void openfiles(){
			
			try{input = new Scanner(Paths.get("warlog.txt"));}
			catch(IOException ioException){System.err.println("Error opening file1. Terminating.");System.exit(1);}
			
			try{output = new Formatter("warlog.txt");}//open the file
			catch(SecurityException securityException)
				{System.err.println("Write permission denied. Terminating.");System.exit(1);}//terminates the program
			catch(FileNotFoundException fileNotFoundException)
				{System.err.println("Error opening file2. Terminating.");System.exit(1);}//terminate the program
		}//end openfiles()
		
		public void closefiles(){
			if (input != null)
				input.close();
			
			if (output != null)
				output.close();
		}//end closefiles()
		
		
		
		
		
		
		
		//"Do you want to play again" Buttons Prompt
		private static void PlayAgainPrompt(){
			String[] buttons = {"Yes", "No"}; 
			int playagain = JOptionPane.showOptionDialog(null,"Do you want to play again?","Play Again Prompt", 
					JOptionPane.PLAIN_MESSAGE, 3, null, buttons, buttons[0]);
			
			if(playagain==1){againstatus=PlayAgain.NO;}
			if (playagain == JOptionPane.CLOSED_OPTION){System.exit(0);}
			}//end PlayAgainPrompt method

}//end class WarDriver
