import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day_16 {

	private static int[] userTicket;
	private static List<String> otherTicketsList;
	private static Set<Integer> validSet;
	private static List<Rule> rulesList;

	public static void main(String[] args) {

		loadData();

		long res = otherTicketsList.stream().flatMap(str -> Stream.of(str.split(","))).mapToInt(Integer::parseInt)
				.filter(val -> validSet.contains(val) == false).sum();
		System.out.println(res+"\n");
		
		String[] validTickets = otherTicketsList.stream().filter(ticket -> validateTicket(ticket)).toArray(String[]::new);
		
		int[][] validTicketsArray = new int[validTickets.length][];
		for(int i=0; i< validTickets.length; i++)
		{
			validTicketsArray[i] = Stream.of(validTickets[i].split(",")).mapToInt(Integer::parseInt).toArray();
		}
		
		Rule[] ruleAtPosition = new Rule[rulesList.size()];
		ArrayList<Rule> usedRules = new ArrayList<>();
		boolean flagNoneAdded = true;
		while(true)
		{
			Rule lastAddedRule = null;
			int check =0;
			int position = 0;
			for(Rule rule : rulesList)
			{
				if(usedRules.contains(rule) == true)
					break;
				
				check =0;
				position = 0;
				for(int j =0; j< validTicketsArray[0].length; j++)
				{
					
					if(ruleAtPosition[j] != null)
						continue;
					
					boolean flagValid = true;
					for(int i=0; i< validTicketsArray.length; i++)
					{
						int val = validTicketsArray[i][j];
						
						if((val < rule.firstBoundStart || val > rule.firstBoundEnd) && (val < rule.secondBoundStart || val > rule.secondBoundEnd))
						{
							flagValid = false;
							break;
						}
							
					}
					if(flagValid == true)
					{
						check++;
						position = j;
						lastAddedRule = rule;
					}
				}
				
				if(check == 1 || flagNoneAdded == true)
				{
					ruleAtPosition[position] = lastAddedRule;
					usedRules.add(rule);
					flagNoneAdded = false;
				}
			}
			
			System.out.println(usedRules.size());
			if(usedRules.size() == rulesList.size())
				break;
		}
		
		int product = 1;
		for(int i=0; i<userTicket.length; i++)
		{
			if(ruleAtPosition[i].name.equals("dep") == true)
				product*= userTicket[i];
		}

		System.out.println(product);
	}

	public static boolean validateTicket(String ticket) {
		return Stream.of(ticket.split(",")).mapToInt(Integer::parseInt).allMatch(val -> validSet.contains(val) == true);
	}

	public static void addValidNumbersRange(int start, int end) {
		for (int i = start; i <= end; i++)
			validSet.add(i);
	}

	public static void loadData() {

		validSet = new HashSet<>();
		otherTicketsList = new ArrayList<>();
		rulesList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_16.txt"));
			String line = sc.nextLine();
			while (line.equals("") == false) {
				Matcher matcher = Pattern.compile("(\\d+)-(\\d+) or (\\d+)-(\\d+)").matcher(line);
				matcher.find();
				addValidNumbersRange(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
				addValidNumbersRange(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

				Rule rule = new Rule();
				rule.name = line.substring(0, 3);
				rule.firstBoundStart = Integer.parseInt(matcher.group(1));
				rule.firstBoundEnd = Integer.parseInt(matcher.group(2));
				rule.secondBoundStart = Integer.parseInt(matcher.group(3));
				rule.secondBoundEnd = Integer.parseInt(matcher.group(4));

				rulesList.add(rule);

				line = sc.nextLine();
			}
			userTicket = Stream.of(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

			sc.nextLine();
			line = sc.nextLine();
			while (line.equals("") == false) {
				otherTicketsList.add(line);
				if (sc.hasNextLine() == false)
					break;
				line = sc.nextLine();
			}

		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}

	static class Rule {
		String name;
		int firstBoundStart;
		int firstBoundEnd;
		int secondBoundStart;
		int secondBoundEnd;
	}
}
