import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day_17 {

	private static Map<Point, Character> pointsMap;
	private static Map<Point, Integer> neighboursMap;

	public static void main(String[] args) {
		loadData();

		neighboursMap = new HashMap<>();

		for (int i = 0; i < 6; i++) {
			neighboursMap.clear();

			for (Point point : pointsMap.keySet()) {
				List<Point> neighbours = point.getNeighbours();
				for (Point neighbour : neighbours) {
					if (neighboursMap.containsKey(neighbour)) {
						int value = neighboursMap.get(neighbour);
						value += 1;
						neighboursMap.put(neighbour, value);
					} else {
						neighboursMap.put(neighbour, 1);
					}
				}
			}

			Map<Point, Character> newPointsMap = new HashMap<>();

			for (Point point : neighboursMap.keySet()) {
				int neighboursCount = neighboursMap.get(point);
				if (pointsMap.containsKey(point) == false && neighboursCount == 3) {
					newPointsMap.put(point, '#');
				} else if (pointsMap.containsKey(point) && (neighboursCount == 3 || neighboursCount == 4)) {
					newPointsMap.put(point, '#');
				}

			}
			pointsMap.clear();
			pointsMap = newPointsMap;
		}

		System.out.println("\n" + pointsMap.size());

		loadData();

		neighboursMap = new HashMap<>();

		for (int i = 0; i < 6; i++) {
			neighboursMap.clear();

			for (Point point : pointsMap.keySet()) {
				List<Point> neighbours = point.getNeighbours2();
				for (Point neighbour : neighbours) {
					if (neighboursMap.containsKey(neighbour)) {
						int value = neighboursMap.get(neighbour);
						value += 1;
						neighboursMap.put(neighbour, value);
					} else {
						neighboursMap.put(neighbour, 1);
					}
				}
			}

			Map<Point, Character> newPointsMap = new HashMap<>();

			for (Point point : neighboursMap.keySet()) {
				int neighboursCount = neighboursMap.get(point);
				if (pointsMap.containsKey(point) == false && neighboursCount == 3) {
					newPointsMap.put(point, '#');
				} else if (pointsMap.containsKey(point) && (neighboursCount == 3 || neighboursCount == 4)) {
					newPointsMap.put(point, '#');
				}

			}
			pointsMap.clear();
			pointsMap = newPointsMap;
		}

		System.out.println("\n" + pointsMap.size());
	}

	public static void loadData() {

		pointsMap = new HashMap<>();

		try {
			Scanner sc = new Scanner(new File("./Inputs/day_17.txt"));

			int y = 0;
			int z = 0;
			while (sc.hasNextLine()) {
				char[] lineChars = sc.nextLine().toCharArray();

				for (int i = 0; i < lineChars.length; i++) {
					if (lineChars[i] == '#')
						pointsMap.put(new Point(i, y, z, 0), lineChars[i]);
				}

				y++;
			}

		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}

	static class Point {

		int x;
		int y;
		int z;
		int w;

		public Point(int x, int y, int z, int w) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
		}

		public List<Point> getNeighbours() {
			ArrayList<Point> neighboursList = new ArrayList<>();

			for (int i = -1; i < 2; i++)
				for (int j = -1; j < 2; j++)
					for (int k = -1; k < 2; k++)
						neighboursList.add(new Point(i + this.x, j + this.y, k + this.z, 0));

			return neighboursList;
		}

		public List<Point> getNeighbours2() {
			ArrayList<Point> neighboursList = new ArrayList<>();

			for (int i = -1; i < 2; i++)
				for (int j = -1; j < 2; j++)
					for (int k = -1; k < 2; k++)
						for (int l = -1; l < 2; l++)
							neighboursList.add(new Point(i + this.x, j + this.y, k + this.z, l + this.w));

			return neighboursList;
		}

		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}

			if (!(o instanceof Point)) {
				return false;
			}

			Point p = (Point) o;

			return this.x == p.x && this.y == p.y && this.z == p.z && this.w == p.w;
		}

		@Override
		public int hashCode() {
			int result = 373; // Constant can vary, but should be prime
			result = 13 * result + x;
			result = 13 * result + y;
			result = 13 * result + z;
			result = 13 * result + w;
			return result;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + "]";
		}

	}

}
