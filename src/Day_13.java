import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day_13 {

	public static int leaveTimestamp;
	public static String[] busIdArray;

	public static void main(String[] args) {
		loadData();
		System.out.println(Arrays.toString(busIdArray));
		int minWait = leaveTimestamp;
		int leaveBusId = 0;
		for (String busIdStr : busIdArray) {
			if (busIdStr.equals("x"))
				continue;
			int busId = Integer.valueOf(busIdStr);
			int res = leaveTimestamp / busId;
			res = (res + 1) * busId;
			res = res - leaveTimestamp;
			if (res < minWait) {
				minWait = res;
				leaveBusId = busId;
			}

		}
		System.out.println(minWait);
		System.out.println(minWait * leaveBusId);

		List<Integer> numList = new ArrayList<>();
		List<Integer> remList = new ArrayList<>();
		for (int i = 0; i < busIdArray.length; i++) {
			if (busIdArray[i].equals("x") == false) {
				numList.add(Integer.valueOf(busIdArray[i]));
				remList.add(Math.floorMod(-i, Integer.valueOf(busIdArray[i])));
			}
		}

		int[] num = numList.stream().mapToInt(i -> i).toArray();
		int[] rem = remList.stream().mapToInt(i -> i).toArray();

		System.out.println(Arrays.toString(num));
		System.out.println(Arrays.toString(rem));

		System.out.println(findMinX(num, rem));

	}

	static BigInteger findMinX(int num[], int rem[]) {
		BigInteger prod = BigInteger.ONE;
		for (int i = 0; i < num.length; i++)
			prod = prod.multiply(BigInteger.valueOf(num[i]));

		BigInteger result = BigInteger.ZERO;

		for (int i = 0; i < num.length; i++) {
			BigInteger pp = prod.divide(BigInteger.valueOf(num[i]));

			BigInteger inv = pp.modInverse(BigInteger.valueOf(num[i]));

			result = result.add(BigInteger.valueOf(rem[i]).multiply(inv).multiply(pp));
		}

		return result.mod(prod);
	}

	public static void loadData() {
		try {
			Scanner sc = new Scanner(new File("./Inputs/day_13.txt"));
			leaveTimestamp = Integer.valueOf(sc.nextLine());
			String line = sc.nextLine();
			busIdArray = line.split(",");

		} catch (IOException e) {
			System.err.println("couldn't open file");
		}
	}

}
