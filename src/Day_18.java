import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day_18 {

	private static List<String> linesList;

	private static int currentPosition = 0;

	public static void main(String[] args) {
		loadData();
		System.out.println(linesList.size());
		
		long sum = 0;
		for(String line: linesList)
		{
			line = line.replaceAll(" ", "");
			currentPosition = 0;
			
			long val = solve(line);
			sum += val;
			
		}
		
		System.out.println(sum);
		
		
		sum = 0;
		for(String line: linesList)
		{
			line = line.replaceAll(" ", "");
			
			line = addParenthesis(line);
			
			currentPosition = 0;
			
			long val = solve(line);
			System.out.println(val);
			sum += val;
			
		}
		System.out.println(sum);
	}

	public static long solve(String input) {
		long sum = 0;
		long value = -1;
		int operation = -1;
		while (currentPosition < input.length()) {
			char chr = input.charAt(currentPosition);
			if (chr == '*') {
				operation = 2;
			} else if (chr == '+') {
				operation = 1;
			} else if (chr == '(') {
				currentPosition++;
				value = solve(input);
				

				switch (operation) {
				case -1: {
					if (value != -1)
						sum += value;
					else {
						sum += Integer.valueOf(chr + "");
					}
					value = -1;
					break;
				}
				case 1: {
					if (value != -1)
						sum += value;
					else {
						sum += Integer.valueOf(chr + "");

					}
					value = -1;
					break;
				}
				case 2: {
					if (value != -1)
						sum *= value;
					else {
						sum *= Integer.valueOf(chr + "");
					}
					value = -1;
					break;
				}
				}
			
			} else if (chr == ')') {
				return sum;
			} else {
				switch (operation) {
				case -1: {
					if (value != -1)
						sum += value;
					else {
						sum += Integer.valueOf(chr + "");
					}
					value = -1;
					break;
				}
				case 1: {
					if (value != -1)
						sum += value;
					else {
						sum += Integer.valueOf(chr + "");

					}
					value = -1;
					break;
				}
				case 2: {
					if (value != -1)
						sum *= value;
					else {
						sum *= Integer.valueOf(chr + "");
					}
					value = -1;
					break;
				}
				}
			}

			currentPosition += 1;
		}
		
		return sum;
	}
	
	public static String addParenthesis(String line)
	{
	System.out.println(line);
		while(line.indexOf('+') != -1)
		{
			int index = line.indexOf('+');
			line = line.replaceFirst("\\+", "\\.");
			int indexEnd = findClosingPosition(line, index);
			
			line = line.substring(0, indexEnd) + ")" + line.substring(indexEnd, line.length()); 
			
			int indexStart = findOpeningPosition(line, index);
			
			line = line.substring(0, indexStart) + "(" + line.substring(indexStart, line.length());
			
		}
		line = line.replace('.', '+');
		System.out.println(line);
		return line;
	}
	
	public static int findClosingPosition(String line, int index)
	{
		index+=1;
		int openingCount = 0;
		while(index< line.length())
		{
			char chr = line.charAt(index);
			if(chr =='(')
			{
				openingCount+=1;
				index++;
			}

			else if(chr == ')')
			{
				openingCount -= 1;
				if(openingCount == 0)
				{
					index +=1;
					return index;	
				}
				index +=1;
			}
			else if (chr == '+' || chr == '.' || chr== '*')
			{
				index+=1;
			}
			else if(openingCount == 0)
			{
				index +=1;
				return index;
			}
			else
				index+=1;
		}
		return index;
	}
	
	public static int findOpeningPosition(String line, int index)
	{
		index-=1;
		int openingCount = 0;
		while(index>= 0)
		{
			char chr = line.charAt(index);
			if(chr =='(')
			{
				openingCount-=1;
				if(openingCount == 0)
				{
					return index;	
				}
				index -=1;
			}

			else if(chr == ')')
			{
				openingCount += 1;
				index -= 1;
			}

			else if (chr == '+' || chr == '.' || chr== '*')
			{
				index-=1;
			}
			else if(openingCount == 0)
			{
				return index;
			}
			else
				index-=1;
		}
		return index;
	}

	public static void loadData() {

		linesList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_18.txt"));
			while (sc.hasNextLine()) {
				linesList.add(sc.nextLine());
			}
		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}

}
