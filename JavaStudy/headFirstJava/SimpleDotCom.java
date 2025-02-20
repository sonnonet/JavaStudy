package headFirst_CH5;

public class SimpleDotCom {
	
	int [] locationCells;
	int numOfHits=0;
	

	public String checkYourself(String Guess)
	{
		int guess = Integer.parseInt(Guess);
		String result = "miss";
		for(int cell : locationCells)
		{
			System.out.println(cell);
			if(guess==cell)
			{
				result = "hit";
				numOfHits++;
				break;
			}
		}
		if(numOfHits == locationCells.length)
		{
			result="Kill";
		}
		System.out.println(result);
		return result;
	
		
	}

	public void setLocationCells(int[] loc)
	{
		locationCells = loc;
	}
	public int[] getLocationCells()
	{
		return locationCells;
	
	}

}
