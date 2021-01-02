import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_19 {

	private static Map<Integer, Rule> rulesMap;
	private static List<String> inputsArray;

	private static long kk = 0;

	public static void main(String[] args) {
		loadData();

		String res = unravel(rulesMap.get(0), "");
		System.out.println(res);

		final String result = "^" + res + "$";

		long count = inputsArray.stream().filter(input -> input.matches(result)).count();
		System.out.println(count);

		String res42 = unravel(rulesMap.get(42), "");
		String res31 = unravel(rulesMap.get(31), "");

		long sum = 0;

		for (int i = 1; i < 100; i++) {
			res = "^(" + res42 + "{" + (i + 1) + ",}" + res31 + "{" + i + "}" + ")$";
			final String result2 = res;
			count = inputsArray.stream().filter(input -> input.matches(result2)).count();
			System.out.println(count);
			sum += count;
		}
		System.out.println(sum);
	}

	public static String unravel(Rule rule, String str) {
		str += "(";
		System.out.println(rule + " " + kk++);
		if (rule.endRule != null) {
			str += rule.endRule;
			str += ")";
			return str;
		}

		String[] children = rule.children;

		for (String child : children) {
			child = child.trim();
			String[] splitedRules = child.split(" ");

			for (String subRule : splitedRules) {
				Rule r = rulesMap.get(Integer.parseInt(subRule));
				str = unravel(r, str);
			}
			str += "|";
		}

		str = str.substring(0, str.length() - 1);

		str += ")";
		return str;
	}

	public static void loadData() {

		rulesMap = new HashMap<>();
		inputsArray = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new File("./Inputs/day_19.txt"));

			while (sc.hasNextLine() == true) {
				String line = sc.nextLine();
				if (line.equals(""))
					break;

				Matcher matcher = Pattern.compile("(\\d+): (\"\\w\")?(.+)?").matcher(line);
				matcher.matches();
				int ruleNumber = Integer.valueOf(matcher.group(1));
				String group2 = matcher.group(2);
				String group3 = matcher.group(3);

				Rule rule = null;

				if (group2 != null) {
					rule = new Rule(ruleNumber, group2, null);
				} else {
					rule = new Rule(ruleNumber, null, group3);
				}

				rulesMap.put(ruleNumber, rule);
			}

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				inputsArray.add(line);
			}

		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}

	static class Rule {
		int number;
		String endRule = null;
		String[] children = null;

		public Rule(int number, String endRule, String children) {
			this.number = number;

			if (endRule != null) {
				endRule = endRule.replaceAll("\"", "");
				this.endRule = endRule;
			} else {
				String[] separated = children.split("\\|");
				this.children = separated;
			}
		}

		@Override
		public String toString() {
			return "Rule [number=" + number + ", endRule=" + endRule + ", children=" + Arrays.toString(children) + "]";
		}

	}

}
