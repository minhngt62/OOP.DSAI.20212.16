package utils;

public class ArrayUtils {
	public static int max(int[] array) {
		int max = array[0];
		for (int element : array) {
			if (element > max) {
				max = element;
			}
		}
		return max;
	}
	
	public static int[][] resizeIntegers(int[][] array) {
		int[][] resizedArray = new int[array.length * 2][array[0].length];
		for (int i = 0; i < array.length; i++) {
			resizedArray[i] = array[i];
		}
		return resizedArray;
	}
	
	public static String[] resizeStrings(String[] array) {
		String[] resizedArray = new String[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			resizedArray[i] = array[i];
		}
		return resizedArray;
	}
	
	public static void slice(int[] fillArray, int[] array, int start, int end) {
		int j = 0;
		for (int i = start; i < end; i++) { 
			array[i] = fillArray[j];
			j++;
		}
	}
}
