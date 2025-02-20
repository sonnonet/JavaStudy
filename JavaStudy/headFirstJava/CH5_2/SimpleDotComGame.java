package headFirst_CH5;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SimpleDotComGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfGuesses=0;
		SimpleDotCom dot = new SimpleDotCom();
		GameHelper helper = new GameHelper();
	
		String result="miss";
		
		/*
		int[] locations = new int[7];
		Arrays.fill(locations,  -1);
		Random rand = new Random();
		int insertPosition = rand.nextInt(5);
		
		for(int i=0; i<3; i++)
		{
			locations[insertPosition+i]=insertPosition+i;
		}
		*/
		int randomNum = (int)(Math.random()* 5);
		int[] locations = {randomNum , randomNum+1, randomNum+2};
		
		dot.setLocationCells(locations);
		
		boolean isAlive = true;
		//Scanner scanner = new Scanner(System.in);
		while(isAlive)
		{
		
			//System.out.println("enter a number ");
			//String userGuess= scanner.next();
			String userGuess = helper.getUserInput("enter a number");
			result = dot.checkYourself(userGuess);
			numOfGuesses++;
			if(result.equals("kill"))
			{
				isAlive = false;
				System.out.println("You took "+numOfGuesses+ "guesses");
				
			}
		}
		//scanner.close();
	}

}
