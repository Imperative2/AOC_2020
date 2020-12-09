import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day_9 {
	
	private static List<BigInteger> inputList;
	
	public static void main(String[] args)
	{
		int preambleLength = 25;
		loadData();
		
		int indexOfBadNumber = 0;
		BigInteger foundBadNumber = null;
		
		for(int i = preambleLength; i< inputList.size(); i++)
		{
			BigInteger number = inputList.get(i);
			List<BigInteger> subList = inputList.subList(i-preambleLength, i);
			boolean flagFound = false;
			for(int j=0; j<subList.size(); j++)
			{
				BigInteger difference = number.subtract(subList.get(j));
				
				flagFound = flagFound | subList.contains(difference);
			}
			if(flagFound == false)
			{
				indexOfBadNumber = i;
				foundBadNumber = number;
				System.out.println(number.toString());
			}
		}
		
		System.out.println(indexOfBadNumber);
		
		for(int i= indexOfBadNumber-1; i>=0; i--)
		{
			BigInteger sum = BigInteger.ZERO;
			for(int j = i; j>=0; j--)
			{
				sum = sum.add(inputList.get(j));
				if(sum.compareTo(foundBadNumber) == 0)
				{
					BigInteger min = inputList.subList(j, i+1).stream()
							.min(Comparator.naturalOrder())
							.orElse(BigInteger.ZERO);
					
					BigInteger max = inputList.subList(j, i+1).stream()
					        .max(Comparator.naturalOrder())
					        .orElse(BigInteger.ZERO);
					
					System.out.println(min.add(max).toString());
				}
				else if( sum.compareTo(foundBadNumber) > 0)
					break;
				
			}
		}
	}
	
	public static void loadData() {
		inputList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_9.txt"));
			while (sc.hasNext()) {
				String number = sc.next();
				inputList.add(new BigInteger(number));
			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}

}
