// Driver Class, contains PlayPrompt, Player/CPU Blackjack, and PlayAgainPrompt
// enum re: Marshall

package project3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class BlackJackDriver extends CardCreator{

	
	//variables block
	private enum Status {CONTINUE, BUST, WON, PUSH};
	private enum PlayAgain {YES, NO};
	
	static Formatter output;	//outputs text to a file
	static Scanner input;		//inputs text from a file
	
	static int playerhandvalue=0; 	//total value of all cards in players hand
	static int dealerhandvalue=0;	//total value of all cards dealt to the dealer
	static int cardvalue;			//value of card dealt
	static int hitcommand;			//0 means hit, 1 means stay
	static int losecount;			//counts of games lost
	static int wincount;			//counts of games won
	static int tiecount;			//counts games pushed
	
	static Status gamestatus;		//gamestatus for player to continue game
	static PlayAgain againstatus;			//status for player to play again
	static CardCreator getcardface = new CardCreator(); //reference to get to the class CardCreator
	static introduction intro = new introduction();		//references the introduction class
	static String newname;
	
	public void main(String[] args)throws IOException{runthegame();}
	
	
	@SuppressWarnings("static-access")
	public void runthegame()throws IOException{
		
		intro.GameStart();
//		newname = intro.GetName();
		//PlayPrompt();
		
		againstatus=PlayAgain.YES;
		for(;againstatus==PlayAgain.YES;){
			playerhandvalue = 0; dealerhandvalue = 0; 
			BlackJackPlayer();		
		
			
//			OPEN THE FILES AND SHIT HERE
			try{input = new Scanner(Paths.get("blackjacklog.txt"));}
			catch(IOException ioException){System.err.println("Error opening file1. Terminating.");System.exit(1);}
			

			
//			READ THE SHIT FROM THE FILE RIGHT HERE
			wincount=input.nextInt();losecount=input.nextInt();tiecount=input.nextInt();
			
			
			
			while(gamestatus==Status.CONTINUE)
				{System.out.println("\nThe Game is Continuing \n");
		
				try{Thread.sleep(1500);}catch(Exception e){};
		
				BlackJackCPU();
				System.out.println("Player's Current Hand is: " + playerhandvalue);
				System.out.println("Dealer's Current Hand is: " + dealerhandvalue);
				if(playerhandvalue > dealerhandvalue){gamestatus = Status.WON;}
				if(playerhandvalue < dealerhandvalue){gamestatus = Status.BUST;}
				if(playerhandvalue == dealerhandvalue){gamestatus = Status.PUSH;}
				
				break;} // end while loop
		
			while(gamestatus==Status.WON){wincount++;System.out.println("Player Wins!"); break;}
			while(gamestatus==Status.BUST){losecount++;System.out.println("Player Loses!"); break;}
			while(gamestatus==Status.PUSH){tiecount++;System.out.println("Player and dealer push!"); break;}
		
			System.out.println("\nGames Won: " + wincount + "\nGames Lost: " + losecount + "\nGames Pushed: " + tiecount + "\n");
		
			
			
//			CLOSE THE READING FILE
			if (input != null)
				input.close();
			
			
//			OPEN THE WRITING FILE SSHITE RIGHT HERE
			try{output = new Formatter("blackjacklog.txt");}//open the file
			catch(SecurityException securityException)
				{System.err.println("Write permission denied. Terminating.");System.exit(1);}//terminates the program
			catch(FileNotFoundException fileNotFoundException)
				{System.err.println("Error opening file2. Terminating.");System.exit(1);}//terminate the program
			
			
//			WRITE THE SHIT TO THE FILE RIGHT HERE
			try{output.format(wincount + "%n" + losecount + "%n" + tiecount);} //should write to file
			catch(FormatterClosedException formatterClosedException)
			{System.err.println("Error writing to file. Terminating.");}
			
			
//			CLOSE THE WREITING FILES AND SHIT HERE

			if (output != null)
				output.close();
			
			
			
			
			PlayAgainPrompt();
		}//end for loop
	

	}//end void main
	
	
	
	
	
	
	
	
	
	
	
	//"Do you want to play" Buttons Prompt
	private static void PlayPrompt(){
		String[] buttons = {"Yes", "No"}; 
		int play = JOptionPane.showOptionDialog(null, newname + ", do you want to play Blackjack?","Play Prompt", 
				JOptionPane.PLAIN_MESSAGE, 3, null, buttons, buttons[0]);
		
		if(play==1){System.exit(0);}
		if (play == JOptionPane.CLOSED_OPTION){System.exit(0);} //JOptionPane.; EXIT PROGRAM ON CLOSE
		
	}//end PlayGame method
	
	
	
	
	
	
	
	
	
	
	
	//Playing BlackJack Game
	private static int BlackJackPlayer(){
		
		do{
			cardvalue = getcardface.CardValue();
			String cardface = getcardface.CardFace();
			System.out.println("The " + cardvalue + " of " + cardface + " is dealt.");
			playerhandvalue = playerhandvalue + cardvalue;
//			System.out.println("the variable cardvalue is " + cardvalue);
//			System.out.println("the variable playerhandvalue is " + playerhandvalue);
			System.out.println("The card's value is: " + cardvalue);
			System.out.println("The player's hand is: " + playerhandvalue + "\n");
			
			if(playerhandvalue>21){gamestatus = Status.BUST;break;}//end if
			
			hitcommand = getcardface.HitStay();
//			System.out.println("hitcommand is " + hitcommand);
			gamestatus = Status.CONTINUE;} //end do loop
		while(hitcommand==0);
			
		System.out.println("Players Total Hand value is: " + playerhandvalue);
			
	return(playerhandvalue);
	}//end BlackJackPlayer
	
	
	
	
	
	
	
	
	
	
	//Runs the BlackJackCode for the Dealer
	private static int BlackJackCPU(){
		do{
			cardvalue = getcardface.CardValue();
			String cardface = getcardface.CardFace();
			System.out.println("The " + cardvalue + " of " + cardface + " is dealt.");
			dealerhandvalue = dealerhandvalue + cardvalue;
//			System.out.println("the variable cardvalue is " + cardvalue);
//			System.out.println("the variable dealerhandvalue is " + dealerhandvalue);
			System.out.println("The card's value is: " + cardvalue);
			System.out.println("The dealer's hand is: " + dealerhandvalue + "\n");
			
			try{Thread.sleep(1500);}catch(Exception e){};
			
			if(dealerhandvalue<17){System.out.println("Dealer Hits");}
		
			try{Thread.sleep(500);}catch(Exception e){};}//end do loop
	
		while(dealerhandvalue<17);
		
		if(dealerhandvalue>21){dealerhandvalue=0;System.out.println("Dealer Busts");
		try{Thread.sleep(500);}catch(Exception e){};} //this makes busting win for the Player
		
			else {System.out.println("Dealer stays");
			try{Thread.sleep(500);}catch(Exception e){};}
		
			System.out.println("Dealer's Total Hand value is: " + dealerhandvalue + "\n");
	return(dealerhandvalue);
	}//end BlackJackCPU
	
	
	
	
	
	
	
	
	
	
	
	
	//"Do you want to play again" Buttons Prompt
	private static void PlayAgainPrompt(){
		String[] buttons = {"Yes", "No"}; 
		int playagain = JOptionPane.showOptionDialog(null, newname + ", do you want to play again?","Play Again Prompt", 
				JOptionPane.PLAIN_MESSAGE, 3, null, buttons, buttons[0]);
		
		if(playagain==1){againstatus=PlayAgain.NO;}
		if (playagain == JOptionPane.CLOSED_OPTION){System.exit(0);}
		}//end PlayAgainPrompt method

}//end class Driver