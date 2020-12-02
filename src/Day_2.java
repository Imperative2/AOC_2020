import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day_2 {
	private static List<Rule> rulesList;
	public static void main(String[] args)
	{
		loadData();
		System.out.println(rulesList.size());
		int correctCount = 0;
		for(Rule rule : rulesList)
		{
			long count = rule.password.chars().filter(ch -> ch == rule.letter).count();
			if(count <= rule.max && count >= rule.min)
				correctCount++;	
		}
		System.out.println(correctCount);
		
		correctCount = 0;
		for(Rule rule: rulesList)
			{
				boolean flag1 = false, flag2 = false;
				flag1 = rule.password.charAt(rule.min-1) == rule.letter;
				flag2 = rule.password.charAt(rule.max-1) == rule.letter;
				if( flag1 ^ flag2 == true)
					correctCount++;
			}
		System.out.println(correctCount);
	}

	
	public static void loadData()
	{
		rulesList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_2.txt"));
			while(sc.hasNextLine())
			{
				String inputLine = sc.nextLine();
				inputLine = inputLine.replace('-',' ');
				inputLine = inputLine.replace(':',' ');
				inputLine = inputLine.replaceAll(" +", " ");
				
				String[] input = inputLine.split(" ");
				rulesList.add(new Rule(Integer.valueOf(input[0]),Integer.valueOf(input[1]), input[2].charAt(0), input[3]));
				
			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}
	
	static class Rule{
		public int min;
		public int max;
		public char letter;
		public String password;
		
		public Rule(int min, int max, char letter, String password) {
			this.min = min; this.max = max; this.letter = letter; this.password = password;
		}
	}
}
