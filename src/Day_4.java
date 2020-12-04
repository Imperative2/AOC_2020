import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day_4 {
	private static List<String[]> credentialsList;
	public static void main(String[] args)
	{
		loadData();
		int valid = 0;
		List<String[]> validCredentialsList = new ArrayList<>();
		
		for(String[] credentials : credentialsList)
		{
			if(credentials.length == 8)
			{
				valid++;
				validCredentialsList.add(credentials);
				continue;
			}
			if(credentials.length <7)
				continue;
			
			if(credentials[0].substring(0, 3).equals("byr") == true 
					&& credentials[1].substring(0, 3).equals("cid") == false)
			{
				valid++;
				validCredentialsList.add(credentials);
			}		
		}
		System.out.println(valid);
		
		valid = 0;
		for(String[] c: validCredentialsList)
		{
			if(c.length == 8)
			{
				boolean correct = byr(c[0]) & ecl(c[2]) & eyr(c[3])
						& hcl(c[4]) & hgt(c[5]) & iyr(c[6]) & pid(c[7]); 
				if(correct == true)
					valid++;
			}
			else {
				boolean correct = byr(c[0]) & ecl(c[1]) & eyr(c[2])
						& hcl(c[3]) & hgt(c[4]) & iyr(c[5]) & pid(c[6]); 
				if(correct == true)
					valid++;
			}
			
		}
		System.out.println(valid);
		
	}
	
	public static boolean byr(String input)
	{
		input = input.substring(4);
		int year = Integer.parseInt(input);
		if(year >= 1920 && year <=2002)
		{
			return true;
		}
		return false;
	}
	
	public static boolean ecl(String input)
	{
		input = input.substring(4);
		List<String> colors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
		return colors.contains(input);	
	}
	
	public static boolean eyr(String input)
	{
		input = input.substring(4);
		int year = Integer.parseInt(input);
		if(year >= 2020 && year <=2030)
		{
			return true;
		}
		return false;
	}
	public static boolean hcl(String input)
	{
		input = input.substring(4);
		return input.matches("^#[0-9,a-f]{6}$");
	}
	
	public static boolean hgt(String input)
	{
		input = input.substring(4);

		if(input.matches("^[0-9]{3}cm$") == true) {
			int height = Integer.parseInt(input.substring(0,3));
			if(height >= 150 && height <= 193)
				return true;
		};
		if(input.matches("^[0-9]{2}in$") == true) {
			int height = Integer.parseInt(input.substring(0,2));
			if(height >= 59 && height <= 76)
				return true;
		};

		return false;
	}
	
	public static boolean iyr(String input)
	{
		input = input.substring(4);
		int year = Integer.parseInt(input);
		if(year >= 2010 && year <=2020)
		{
			return true;
		}
		return false;
	}
	
	public static boolean pid(String input)
	{
		input = input.substring(4);
		return input.matches("^[0-9]{9}$");
	}
	
	
	
	
	public static void loadData() {

		credentialsList = new ArrayList<>();
		
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_4.txt"));
			while (sc.hasNextLine()) {
				String inputLine = "";
				while(true) {
					if(sc.hasNextLine() == false)
						break;
					String nextLine = sc.nextLine();
					if(nextLine.equals(""))
						break;
					inputLine+=" "+nextLine;
				}
				
				//System.out.println(inputLine);
				inputLine = inputLine.trim();
				String[] creds = inputLine.split(" ");
				creds = Stream.of(creds).sorted().toArray(String[]::new);
			//	System.out.println(Arrays.toString(creds));
				credentialsList.add(creds);

			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}
}
