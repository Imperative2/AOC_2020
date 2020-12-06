import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day_6 {
	
	private static List<String> linesList;

	public static void main(String[] args) {
		loadData();
		
		int sum = 0;
		boolean isNewGroup = false;
		Set<Character> group = new HashSet<>();
		for(String line: linesList)
		{
			if(line.equals(""))
			{
				isNewGroup = true;
				continue;
			}
			if(isNewGroup == true)
			{
				sum += group.size();
				isNewGroup = false;
				group.clear();
			}
			
			line.chars().forEach(letter ->{
			group.add((char)letter);
			});

		}
		
		sum+= group.size();
		System.out.println(sum);
		
		sum = 0;
		isNewGroup = false;
		boolean newMember = true;
		group.clear();
		for(String line: linesList)
		{
			if(line.equals(""))
			{
				isNewGroup = true;
				continue;
			}
			if(isNewGroup == true)
			{
				sum += group.size();
				isNewGroup = false;
				newMember = true;
				group.clear();
			}
			if(newMember == true)
			{
				line.chars().forEach(letter ->{
					group.add((char)letter);
					});
				newMember = false;
			}
			else {
				for(char letter : new HashSet<Character>(group))
				{
					if (line.contains(letter+"") == false)
							group.remove(letter);
				}
			}

		}
		sum+= group.size();
		System.out.println(sum);

	}
	
	public static void loadData() {
		linesList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_6.txt"));
			while (sc.hasNextLine()) {
				linesList.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}

}
