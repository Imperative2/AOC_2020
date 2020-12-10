import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day_10 {

	private static Set<Integer> chargersSet;
	private static Map<Integer, BigInteger> waysMap;

	public static void main(String[] args) {
		loadData();
		int maxCharger = chargersSet.stream().max(Comparator.naturalOrder()).orElse(0);
		int onesCount = 0;
		int threesCount = 0;
		Integer currentJoltage = 0;
		System.out.println(maxCharger);

		System.out.println(chargersSet.contains(currentJoltage + 1));

		while (chargersSet.isEmpty() == false) {
			if (chargersSet.contains(currentJoltage + 1)) {
				onesCount++;
				chargersSet.remove(currentJoltage + 1);
				currentJoltage += 1;
			} else if (chargersSet.contains(currentJoltage + 2)) {
				chargersSet.remove(currentJoltage + 2);
				currentJoltage += 2;
			} else if (chargersSet.contains(currentJoltage + 3)) {
				threesCount += 1;
				chargersSet.remove(currentJoltage + 3);
				currentJoltage += 3;
			}

		}
		System.out.println(currentJoltage);
		System.out.println(onesCount + "  " + threesCount + 1);

		chargersSet.clear();
		loadData();
		chargersSet.add(0);
		waysMap = new HashMap<>();
		waysMap.put(0, BigInteger.ONE);

		System.out.println(chargersSet.contains(16));

		System.out.println(waysToGetToNumber(maxCharger).toString());
		System.out.println(waysMap.toString());

	}

	public static BigInteger waysToGetToNumber(Integer number) {
		BigInteger ways = BigInteger.ZERO;
		if (waysMap.containsKey(number))
			return waysMap.get(number);

		if (chargersSet.contains(number - 1)) {
			ways = ways.add(waysToGetToNumber(number - 1));
		}
		if (chargersSet.contains(number - 2)) {
			ways = ways.add(waysToGetToNumber(number - 2));
		}
		if (chargersSet.contains(number - 3))
			ways = ways.add(waysToGetToNumber(number - 3));

		waysMap.put(number, ways);

		return ways;
	}

	public static void loadData() {
		chargersSet = new HashSet<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_10.txt"));
			while (sc.hasNext()) {
				chargersSet.add(sc.nextInt());
			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}
}
