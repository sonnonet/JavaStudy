package headFirst_CH5;

import java.util.Arrays;

public class SimpleDotComTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDotCom dot = new SimpleDotCom();
		
		int[] locations = {2,3,4};
		dot.setLocationCells(locations);
		
		String userGuess = "2";
		String result = dot.checkYourself(userGuess);
		
		String testResult = "failed";
		
		if(result.equals("hit"))
		{
			testResult = "passed";
		}
		
		System.out.println(testResult);
		System.out.println(Arrays.toString(dot.getLocationCells()));
		
	}

}
