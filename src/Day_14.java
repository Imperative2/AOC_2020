import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day_14 {

	private static List<Mask> masksList;
	private static Map<Long, Long> memoryMap = new HashMap<>();

	public static void main(String[] args) {
		loadData();
		for (Mask currentMask : masksList) {
			for (String instruction : currentMask.instructions) {
				Matcher instructionMatcher = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)$").matcher(instruction);
				instructionMatcher.matches();
				long memAdress = Long.valueOf(instructionMatcher.group(1));
				long value = Long.valueOf(instructionMatcher.group(2));

				executeInstruction(currentMask.mask, memAdress, value);
			}
		}

		long res = memoryMap.values().stream().collect(Collectors.summingLong(Long::longValue));
		System.out.println("result:  " + res);

		memoryMap.clear();
		for (Mask currentMask : masksList) {
			for (String instruction : currentMask.instructions) {
				Matcher instructionMatcher = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)$").matcher(instruction);
				instructionMatcher.matches();
				long memAdress = Long.valueOf(instructionMatcher.group(1));
				long value = Long.valueOf(instructionMatcher.group(2));

				executeInstruction2(currentMask.mask, memAdress, value);
			}
		}

		res = memoryMap.values().stream().collect(Collectors.summingLong(Long::longValue));
		System.out.println("result:  " + res);
	}

	public static void executeInstruction(String mask, long memAdress, long value) {
		String stringValue = Long.toBinaryString(value);
		stringValue = String.format("%0" + (36 - stringValue.length()) + "d%S", 0, stringValue);
		String result = "";
		for (int i = 0; i < stringValue.length(); i++) {
			if (mask.charAt(i) == 'X') {
				result += stringValue.charAt(i);
			} else
				result += mask.charAt(i);
		}

		memoryMap.put(memAdress, Long.valueOf(result, 2));
	}

	public static void executeInstruction2(String mask, long memAdress, long value) {
		String stringMemoryValue = Long.toBinaryString(memAdress);
		stringMemoryValue = String.format("%0" + (36 - stringMemoryValue.length()) + "d%S", 0, stringMemoryValue);
		String result = "";
		int xCount = 0;
		for (int i = 0; i < stringMemoryValue.length(); i++) {
			if (mask.charAt(i) == 'X') {
				xCount++;
				result += mask.charAt(i);
			} else if (mask.charAt(i) == '1')
				result += "1";
			else
				result += stringMemoryValue.charAt(i);
		}

		List<String> addressesList = new ArrayList<String>();
		generateMemoryAdresses(result, addressesList);

		for (String memoryAdress : addressesList) {

			memoryMap.put(Long.valueOf(memoryAdress, 2), value);
		}

	}

	public static void generateMemoryAdresses(String result, List<String> resultList) {
		if (result.contains("X") == false)
			resultList.add(result);
		else {
			String s1 = result.replaceFirst("X", "0");
			String s2 = result.replaceFirst("X", "1");

			generateMemoryAdresses(s1, resultList);
			generateMemoryAdresses(s2, resultList);
		}
	}

	public static void loadData() {

		masksList = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new File("./Inputs/day_14.txt"));

			Pattern maskPattern = Pattern.compile("^mask = ([1,0,X]{36})$");

			Mask mask = null;

			while (sc.hasNextLine() == true) {
				String line = sc.nextLine();
				Matcher matcher = maskPattern.matcher(line);
				if (matcher.find()) {
					mask = new Mask();
					masksList.add(mask);
					mask.mask = matcher.group(1);
					System.out.println(mask.mask);
				} else {
					mask.instructions.add(line);
				}
			}

		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}

	static class Mask {
		public String mask;
		public List<String> instructions = new ArrayList<>();
	}

}
