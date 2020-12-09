import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Scanner;

public class Day_8 {
	
	private static List<String> instructionsList;
	private static int ip = 0;
	private static int acc = 0;

	public static void main(String[] args) 
	{
		
		loadData();
		while(instructionsList.get(ip) != null)
		{
			String instruction = instructionsList.get(ip);
			instructionsList.set(ip, null);
			
			decodeAndRun(instruction);
		}
		
		System.out.println(acc);
		
		for(int i =0; i<instructionsList.size(); i++)
		{
			instructionsList.clear();
			ip = 0;
			acc = 0;
			loadData();
			while(instructionsList.get(ip) != null)
			{
				String instruction = instructionsList.get(ip);
				instructionsList.set(ip, null);
				
				decodeSwitchAndRun(instruction, i);
				if(ip >= instructionsList.size())
					break;
			}
			if(ip >= instructionsList.size())
				System.out.println(acc);
		}
		

	}
	
	public static void decodeAndRun(String instruction) {
		instruction = instruction.replace("+", "");
		String[] instructionSplit = instruction.split(" ");
		switch(instructionSplit[0]){
			case "nop":
			{
				ip+=1;
				break;
			}
			case "acc":
			{
				ip += 1;
				acc += Integer.parseInt(instructionSplit[1]);
				break;
			}
			case "jmp":
			{
				ip += Integer.parseInt(instructionSplit[1]);
				break;
			}
			default:
				break;
		}
	}
	
	public static void decodeSwitchAndRun(String instruction, int switchPosition) {
		instruction = instruction.replace("+", "");
		String[] instructionSplit = instruction.split(" ");
		switch(instructionSplit[0]){
			case "nop":
			{
				if(ip == switchPosition)
				{
					ip += Integer.parseInt(instructionSplit[1]);
					break;
				}
				ip+=1;
				break;
			}
			case "acc":
			{
				ip += 1;
				acc += Integer.parseInt(instructionSplit[1]);
				break;
			}
			case "jmp":
			{
				if(ip == switchPosition)
				{
					ip+=1;
					break;
				}
				ip += Integer.parseInt(instructionSplit[1]);
				break;
			}
			default:
				break;
		}
	}
	
	public static void loadData() {
		instructionsList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_8.txt"));
			while (sc.hasNextLine()) {
				instructionsList.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}
	


}
