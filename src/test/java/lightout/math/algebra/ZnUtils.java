package lightout.math.algebra;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ZnUtils {
	
	public static int[][] toIntArray(ZnElement[][] table) {
		int[][] result = new int[table.length][];
		int index = 0;
		for(ZnElement[] row : table) {
			result[index] = toIntArray(row);
			index++;
		}
		return result;
	}

	public static int[] toIntArray(ZnElement[] row) {
		int[] result = new int[row.length];
		int index = 0;
		for(ZnElement cell : row) {
			result[index] = cell.getValue();
			index++;
		}
		return result;
	}

	public static Integer[][] toIntegerArray(ZnElement[][] table) {
		Integer[][] result = new Integer[table.length][];
		int index = 0;
		for(ZnElement[] row : table) {
			result[index] = toIntegerArray(row);
			index++;
		}
		return result;
	}

	public static Integer[] toIntegerArray(ZnElement[] row) {
		Integer[] result = new Integer[row.length];
		int index = 0;
		for(ZnElement cell : row) {
			result[index] = cell == null ? null : cell.getValue();
			index++;
		}
		return result;
	}

	public static void outJavaArray(ZnElement[][] table) {
		IntStream.range(0, 10).forEach(i->{
			String text = Arrays.toString(table[i]);
			text = text.replace("[", "{");
			text = text.replace("]", "}");
			System.out.println(text + ",");
		});
	}

	public static void outJavaArray(ZnElement[] table) {
		String text = Arrays.toString(table);
		text = text.replace("[", "{");
		text = text.replace("]", "}");
		System.out.println(text + ",");
	}
}