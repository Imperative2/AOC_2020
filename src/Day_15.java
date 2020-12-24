import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day_15 {

	private static int[] startingValues = { 13, 0, 10, 12, 1, 5, 8 };

	private static Map<Integer, LinkedList<Integer>> numbersMap = new HashMap<Integer, LinkedList<Integer>>();

	public static void main(String[] args) {

		for (int i = 0; i < startingValues.length; i++) {
			int number = startingValues[i];
			LinkedList<Integer> linkedList = new LinkedList<>();
			linkedList.push(i);
			numbersMap.put(number, linkedList);
		}

		int lastSpoken = 0;

		boolean flagFirst = true;

		for (int i = numbersMap.size(); i < 30_000_000; i++) {
			// System.out.println("i: "+i+" :"+lastSpoken);
			if (numbersMap.containsKey(lastSpoken) == false || flagFirst == true) {
				if (flagFirst == true) {
					flagFirst = false;
				} else {
					LinkedList<Integer> linkedList = new LinkedList<>();
					linkedList.push(i - 1);
					numbersMap.put(lastSpoken, linkedList);
					lastSpoken = 0;
				}

			} else {
				LinkedList<Integer> linkedList = numbersMap.get(lastSpoken);
				linkedList.push(i - 1);
				if (linkedList.size() > 2)
					linkedList.removeLast();
				int ind1 = linkedList.get(0);
				int ind2 = linkedList.get(1);
				int res = ind1 - ind2;
				lastSpoken = res;
			}
		}

		System.out.println("\n" + lastSpoken);

	}
}
