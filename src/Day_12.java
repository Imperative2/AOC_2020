import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day_12 {

	private static List<String> instructionsList;
	private static long ship_x = 0;
	private static long ship_y = 0;
	private static int rotation = 0;

	private static long waypoint_x = 10;
	private static long waypoint_y = 1;

	public static void main(String[] args) {
		loadData();
		for (String instruction : instructionsList) {
			executeInstruction(instruction);
		}

		System.out.println(ship_x + "  " + ship_y);
		System.out.println(Math.abs(ship_x) + Math.abs(ship_y));

		ship_x = 0;
		ship_y = 0;
		rotation = 0;

		loadData();
		for (String instruction : instructionsList) {
			executeInstruction2(instruction);
		}

		System.out.println(ship_x + "  " + ship_y);
		System.out.println(Math.abs(ship_x) + Math.abs(ship_y));
	}

	public static void executeInstruction(String instruction) {
		char instructionType = instruction.charAt(0);
		int value = Integer.valueOf(instruction.substring(1));

		switch (instructionType) {
		case 'E':
			ship_x += value;
			break;
		case 'N':
			ship_y += value;
			break;
		case 'W':
			ship_x -= value;
			break;
		case 'S':
			ship_y -= value;
			break;
		case 'L':
			rotation = Math.floorMod(rotation + value, 360);
			break;
		case 'R':
			rotation = Math.floorMod(rotation - value, 360);
			break;
		case 'F':
			switch (rotation) {
			case 0:
				ship_x += value;
				break;
			case 90:
				ship_y += value;
				break;
			case 180:
				ship_x -= value;
				break;
			case 270:
				ship_y -= value;
				break;
			}
			break;
		}

	}

	public static void executeInstruction2(String instruction) {
		char instructionType = instruction.charAt(0);
		int value = Integer.valueOf(instruction.substring(1));

		switch (instructionType) {
		case 'E':
			waypoint_x += value;
			break;
		case 'N':
			waypoint_y += value;
			break;
		case 'W':
			waypoint_x -= value;
			break;
		case 'S':
			waypoint_y -= value;
			break;
		case 'L': {
			long relative_x = waypoint_x - ship_x;
			long relative_y = waypoint_y - ship_y;
			long res_x = Math
					.round(Math.cos(Math.toRadians(value)) * relative_x - Math.sin(Math.toRadians(value)) * relative_y);
			long res_y = Math
					.round(Math.sin(Math.toRadians(value)) * relative_x + Math.cos(Math.toRadians(value)) * relative_y);

			waypoint_x = ship_x + res_x;
			waypoint_y = ship_y + res_y;
			break;
		}
		case 'R': {
			long relative_x = waypoint_x - ship_x;
			long relative_y = waypoint_y - ship_y;

			long res_x = Math.round(
					Math.cos(Math.toRadians(-value)) * relative_x - Math.sin(Math.toRadians(-value)) * relative_y);
			long res_y = Math.round(
					Math.sin(Math.toRadians(-value)) * relative_x + Math.cos(Math.toRadians(-value)) * relative_y);

			waypoint_x = ship_x + res_x;
			waypoint_y = ship_y + res_y;

			break;
		}

		case 'F':
			long relative_x = waypoint_x - ship_x;
			long relative_y = waypoint_y - ship_y;
			ship_x = ship_x + value * relative_x;
			ship_y = ship_y + value * relative_y;
			waypoint_x = waypoint_x + value * relative_x;
			waypoint_y = waypoint_y + value * relative_y;
			break;
		}

	}

	public static void loadData() {
		instructionsList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_12.txt"));

			while (sc.hasNextLine()) {
				instructionsList.add(sc.nextLine());
			}
		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}
}
