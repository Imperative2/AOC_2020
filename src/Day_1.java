import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.paukov.combinatorics3.Generator;

public class Day_1 {

	private static List<Integer> numbersList;
	
	public static void main(String[] args) {
		
		loadData();

		Integer num1 = null, num2= null;
		
		Generator.combination(numbersList).simple(2).stream().forEach(array -> {
			if(array.get(0)+array.get(1) == 2020)
			{
				System.out.println(array.get(0)*array.get(1));
			}
		});
		
		Generator.combination(numbersList).simple(3).stream().forEach(array -> {
			if(array.get(0)+array.get(1)+array.get(2) == 2020)
			{
				System.out.println(array.get(0)*array.get(1)*array.get(2));
			}
		});
		
		
	}
	
	
	public static void loadData()
	{
		numbersList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_1.txt"));
			while(sc.hasNextInt())
			{
				numbersList.add(sc.nextInt());
			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}

}
