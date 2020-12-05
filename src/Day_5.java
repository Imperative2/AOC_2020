import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day_5 
{

	private static List<String> boardingPasses;
	
	public static void main(String[] args) 
	{
		loadData();
		boolean[][] sits = new boolean[128][8];
		
		int[] max = {0};
		boardingPasses.stream().forEach(pass ->{
			int column = Integer.parseInt(pass.substring(0, 7), 2);
			int row = Integer.parseInt(pass.substring(7,10) ,2);
			int value = column*8+row;
			sits[column][row] = true;
			if(max[0] < value)
				max[0] = value;

		});
		
		System.out.println(max[0]);
		
		for(int i=0; i< sits.length; i++)
		{
			for(int j=0; j<sits[0].length; j++)
			{
				if(sits[i][j] == false)
				{
					System.out.println("i: "+i+" j: "+j);
				}
			}
		}
	}

	public static void loadData() 
	{
		boardingPasses = new ArrayList<>();
		
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_5.txt"));
			while (sc.hasNextLine()) 
			{
					String line = sc.nextLine();
					line = line.replace('F', '0');
					line = line.replace('B', '1');
					line = line.replace('L', '0');
					line = line.replace('R', '1');
					boardingPasses.add(line);
			}
				


			
		} catch (FileNotFoundException e) 
		{
			System.err.println("couldn't open file");

		}
	}

}
