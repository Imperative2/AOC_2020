import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day_3 {

	private static List<char[]> map;

	public static void main(String[] args) {
		loadData();
//		map.stream().forEach(line -> System.out.println(Arrays.toString(line)));
		int x = 0, y = 0;
		int treeCount = 0;
		while (y < map.size() - 1) {
			y += 1;
			x += 3;
			x %= map.get(0).length;
			if (map.get(y)[x] == '#')
				treeCount++;
		}
		System.out.println(treeCount);

		int[][] steps = { { 1, 1 }, { 3, 1 }, { 5, 1 }, { 7, 1 }, { 1, 2 } };

		BigInteger product = Stream.of(steps).map(step -> {
			int pos_x = 0, pos_y = 0;
			int trees = 0;
			while (pos_y < map.size() - 1) {
				pos_x += step[0];
				pos_y += step[1];
				pos_x %= map.get(0).length;
				if (map.get(pos_y)[pos_x] == '#')
					trees++;
			}
			System.out.println(trees);
			return BigInteger.valueOf(trees);
		}).reduce(BigInteger.ONE, BigInteger::multiply);

		System.out.println(product);
	}

	public static void loadData() {
		map = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_3.txt"));
			while (sc.hasNextLine()) {
				String inputLine = sc.nextLine();
				map.add(inputLine.toCharArray());
			}
		} catch (FileNotFoundException e) {
			System.err.println("couldn't open file");
		}
	}

}
