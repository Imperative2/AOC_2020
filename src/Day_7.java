import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day_7 {

	private static Map<String, Bag> bagsMap;
	private static Set<Bag> validParentBagSet = new HashSet<>();
	private static long childrenCount = 0L;

	public static void main(String[] args) {
		loadData();

		addParentToSet(bagsMap.get("shiny gold"));
		System.out.println(bagsMap.get("shiny gold").getParents().toString());
		System.out.println(validParentBagSet.size() - 1);

		System.out.println(addChildren(bagsMap.get("shiny gold")) - 1);

	}

	public static void addParentToSet(Bag bag) {
		System.out.println(bag.getName());
		validParentBagSet.add(bag);
		List<Bag> parentsList = bag.getParents();
		for (Bag parent : parentsList) {
			addParentToSet(parent);
		}
	}

	public static long addChildren(Bag bag) {
		Map<Bag, Integer> childrenMap = bag.getChildren();
		Set<Bag> keys = childrenMap.keySet();
		long sum = 1;
		for (Bag key : keys) {
			sum += childrenMap.get(key) * addChildren(key);
		}
		return sum;
	}

	public static void loadData() {

		bagsMap = new HashMap<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_7.txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				line = line.replace("bags", "");
				line = line.replace("bag", "");
				line = line.replace(".", "");
				String[] lineParts = line.split(",");

				String[] firstSentence = lineParts[0].split("contain");
				lineParts[0] = firstSentence[1];

				String parentBagName = firstSentence[0].trim();

				Bag parentBag = bagsMap.get(parentBagName);
				if (parentBag == null) {
					parentBag = new Bag(parentBagName);
					bagsMap.put(parentBagName, parentBag);
				}

				for (String bag : lineParts) {
					bag = bag.trim();
					if (bag.equals("no other") == false) {
						String bagName = bag.substring(2);
						Integer quantity = Integer.parseInt(bag.substring(0, 2).trim());
						Bag foundBag = bagsMap.get(bagName);
						if (foundBag == null) {
							Bag newBag = new Bag(bagName);
							newBag.addParent(parentBag);
							bagsMap.put(bagName, newBag);
							parentBag.addChild(newBag, quantity);
						} else {
							foundBag.addParent(parentBag);
							parentBag.addChild(foundBag, quantity);
						}

					}
				}

			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}

	static class Bag {
		private String name;
		private List<Bag> parents = new ArrayList<>();
		private Map<Bag, Integer> childrenMap = new HashMap<>();

		public Bag(String name) {
			this.name = name;
		}

		public List<Bag> getParents() {
			return this.parents;
		};

		public void addParent(Bag parent) {
			parents.add(parent);
		}

		public Map<Bag, Integer> getChildren() {
			return this.childrenMap;
		}

		public void addChild(Bag child, Integer quantity) {
			childrenMap.put(child, quantity);
		}

		public String getName() {
			return name;
		}
	}

}
