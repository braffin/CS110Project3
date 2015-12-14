//STOLEN DIRECTLY FROM MARSHALL, witha few changes to match what I wanted
// Fig. 6.8: Craps.java

// Craps class simulates the dice game craps.
package project3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Craps 
{
   // create secure random number generator for use in method rollDice
   private static final SecureRandom randomNumbers = new SecureRandom();

   // enum type with constants that represent the game status
   private enum Status {CONTINUE, WON, LOST};
   private enum PlayAgain {YES, NO};
   static PlayAgain againstatus;			//status for player to play again
   static int wincount;
   static int losecount;
   static Formatter output;	//outputs text to a file
   static Scanner input;		//inputs text from a file
   
   static introduction intro = new introduction();

   // constants that represent common rolls of the dice
   private static final int SNAKE_EYES = 2;
   private static final int TREY = 3;
   private static final int SEVEN = 7;
   private static final int YO_LEVEN = 11;
   private static final int BOX_CARS = 12;

   // plays craps
//public static void main(String[] args) throws IOException{runcraps2();}
   
   public void runcraps2(){againstatus=PlayAgain.YES;for(;againstatus==PlayAgain.YES;){runcraps();PlayAgainPrompt();}}
   
   @SuppressWarnings("static-access")
public static void runcraps()
   {
	   
//		OPEN THE FILES AND SHIT HERE
		try{input = new Scanner(Paths.get("crapslog.txt"));}
		catch(IOException ioException){System.err.println("Error opening file1. Terminating.");System.exit(1);}
		

		
//		READ THE SHIT FROM THE FILE RIGHT HERE
		wincount=input.nextInt();losecount=input.nextInt();
		
		
	   
	   try {intro.CrapsGameStart();} catch (IOException e) {e.printStackTrace();}
	   
	   
      int myPoint = 0; // point if no win or loss on first roll
      Status gameStatus; // can contain CONTINUE, WON or LOST

      int sumOfDice = rollDice(); // first roll of the dice

      // determine game status and point based on first roll 
      switch (sumOfDice) 
      {
         case SEVEN: // win with 7 on first roll
         case YO_LEVEN: // win with 11 on first roll           
            gameStatus = Status.WON;
            break;
         case SNAKE_EYES: // lose with 2 on first roll
         case TREY: // lose with 3 on first roll
         case BOX_CARS: // lose with 12 on first roll
            gameStatus = Status.LOST;
            break;
         default: // did not win or lose, so remember point         
            gameStatus = Status.CONTINUE; // game is not over
            myPoint = sumOfDice; // remember the point
            System.out.printf("Point is %d%n", myPoint);
            break;
      } 

      // while game is not complete
      while (gameStatus == Status.CONTINUE) // not WON or LOST
      { 
         sumOfDice = rollDice(); // roll dice again

         // determine game status
         if (sumOfDice == myPoint) // win by making point
            gameStatus = Status.WON;
         else 
            if (sumOfDice == SEVEN) // lose by rolling 7 before point
               gameStatus = Status.LOST;
      } 

      // display won or lost message
      if (gameStatus == Status.WON){
    	  wincount++;
         System.out.println("Player wins");}
      if (gameStatus == Status.LOST){
    	  losecount++;
         System.out.println("Player loses");}
   
         System.out.println("\nGames Won: " + wincount + "\nGames Lost: " + losecount);
         
         
//			CLOSE THE READING FILE
			if (input != null)
				input.close();
			
			
//			OPEN THE WRITING FILE SSHITE RIGHT HERE
			try{output = new Formatter("crapslog.txt");}//open the file
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
		
			
			
  }
   

   // roll dice, calculate sum and display results
   public static int rollDice()
   {
      // pick random die values
      int die1 = 1 + randomNumbers.nextInt(6); // first die roll
      int die2 = 1 + randomNumbers.nextInt(6); // second die roll

      int sum = die1 + die2; // sum of die values

      // display results of this roll
      System.out.printf("Player rolled %d + %d = %d%n", 
         die1, die2, sum);

      return sum; 
   }
   
   
   private static void PlayAgainPrompt(){
		String[] buttons = {"Yes", "No"}; 
		int playagain = JOptionPane.showOptionDialog(null,"Do you want to play again?","Play Again Prompt", 
				JOptionPane.PLAIN_MESSAGE, 3, null, buttons, buttons[0]);
		
		if(playagain==1){againstatus=PlayAgain.NO;}
		if (playagain == JOptionPane.CLOSED_OPTION){System.exit(0);}
		}//end PlayAgainPrompt method
   
} // end class Craps

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
