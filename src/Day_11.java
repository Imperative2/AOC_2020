import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day_11 {

	private static Character[][] seatsArray;

	public static void main(String[] args) {
		loadData();
		System.out.println(seatsArray.length);

		Character curr = null;
		boolean hasChangedFlag = true;

		while (hasChangedFlag == true) {
			Character[][] seatsArrayCopy = new Character[seatsArray.length][seatsArray[0].length];
			hasChangedFlag = false;
			for (int i = 0; i < seatsArray.length; i++) {
				for (int j = 0; j < seatsArray[0].length; j++) {
					Character seatTransformed = getSeatTransformation(i, j);
					seatsArrayCopy[i][j] = seatTransformed;
					if (seatsArray[i][j] != seatTransformed)
						hasChangedFlag = true;
				}
			}

			seatsArray = seatsArrayCopy;
		}

		long count = Arrays.stream(seatsArray).flatMap(row -> Arrays.stream(row)).filter(seat -> seat == '#').count();
		System.out.println(count);

		loadData();

		hasChangedFlag = true;
		int k= 0;
		while (hasChangedFlag == true) {
			System.out.println(k++);
			Character[][] seatsArrayCopy = new Character[seatsArray.length][seatsArray[0].length];
			hasChangedFlag = false;
			for (int i = 0; i < seatsArray.length; i++) {
				for (int j = 0; j < seatsArray[0].length; j++) {
					Character seatTransformed = getSeatTransformation2(i, j);
					seatsArrayCopy[i][j] = seatTransformed;
					if (seatsArray[i][j] != seatTransformed)
						hasChangedFlag = true;
				}
			}
			printArray(seatsArray);
			seatsArray = seatsArrayCopy;
			printArray(seatsArray);

		}
		count = Arrays.stream(seatsArray).flatMap(row -> Arrays.stream(row)).filter(seat -> seat == '#').count();
		System.out.println(count);

	}

	public static void printArray(Character[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println();
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]);
			}
		}
		System.out.println();
	}

	public static char getSeatTransformation(int i, int j) {
		List<Character> neighbours = getSeatNeighbours(i, j);
		if (seatsArray[i][j] == 'L')
			if (neighbours.contains('#') == true)
				return 'L';
			else
				return '#';
		else if (seatsArray[i][j] == '#') {
			if (neighbours.stream().filter(seat -> seat == '#').count() >= 4)
				return 'L';
			else
				return '#';
		} else
			return '.';
	}

	public static List<Character> getSeatNeighbours(int i, int j) {
		List<Character> seatsList = new ArrayList<>();
		int rowStart = i - 1 >= 0 ? i - 1 : 0;
		int rowEnd = i + 1 < seatsArray.length ? i + 1 : i;
		int columnStart = j - 1 >= 0 ? j - 1 : 0;
		int columnEnd = j + 1 < seatsArray[0].length ? j + 1 : j;

		for (int m = rowStart; m <= rowEnd; m++)
			for (int n = columnStart; n <= columnEnd; n++) {
				if (m == i && n == j)
					continue;
				seatsList.add(seatsArray[m][n]);
			}
		return seatsList;
	}
	
	public static char getSeatTransformation2(int i, int j) {
		List<Character> neighbours = getSeatNeighbours2(i, j);
		if (seatsArray[i][j] == 'L')
			if (neighbours.contains('#') == true)
				return 'L';
			else
				return '#';
		else if (seatsArray[i][j] == '#') {
			if (neighbours.stream().filter(seat -> seat == '#').count() >= 5)
				return 'L';
			else
				return '#';
		} else
			return '.';
	}
	
	public static List<Character> getSeatNeighbours2(int i, int j) {
		List<Character> seatsList = new ArrayList<>();

		for (int m = -1; m <= 1; m++)
			for (int n = -1; n <= 1; n++) {
				if (m == 0 && n == 0)
					continue;
				seatsList.add(getVisiblechair(i,j, m, n));
			}
		return seatsList;
	}
	public static Character getVisiblechair(int i, int j, int stepVertical, int stepHorizontal)
	{
		i+= stepVertical;
		j+= stepHorizontal;
		while( i>=0 && j>=0 && i<seatsArray.length && j<seatsArray[0].length)
		{
			if(seatsArray[i][j] != '.')
				return seatsArray[i][j];
			i+= stepVertical;
			j+= stepHorizontal;
		}
		return '.';
	}

	public static void loadData() {

		try {
			Scanner sc = new Scanner(new File("./Inputs/day_11.txt"));
			int lines = (int) Files.lines(Paths.get("./Inputs/day_11.txt")).count();

			seatsArray = new Character[lines][];
			seatsArray[0] = sc.nextLine().chars().mapToObj(c -> (char) c).toArray(Character[]::new);

			int i = 1;

			while (sc.hasNextLine()) {

				Character[] row = sc.nextLine().chars().mapToObj(c -> (char) c).toArray(Character[]::new);
				seatsArray[i] = row;
				i++;
			}
		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}
}
