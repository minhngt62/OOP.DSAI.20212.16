package sorting;

import utils.ArrayUtils;

public class MergeSort extends SortingAlgorithm {
	private int[] supportArray;
	
	public MergeSort(int[] array) {
		super(array);
		supportArray = new int[array.length];
	}
	
	@Override
	public void sort() {
		mergeSort(inputArray, 0, inputArray.length);
	}
	
	private void mergeSort(int[] inputArray, int start, int end) {
		if (inputArray.length > 1) {
			supportArray = new int[this.inputArray.length];
			int mid = inputArray.length / 2;
			
			int[] leftSubArray = new int[mid];
			for (int i=0; i < mid; i++) {
				leftSubArray[i] = inputArray[i];
			}
			int[] rightSubArray = new int[inputArray.length - mid];
			for (int i=mid; i < inputArray.length; i++) {
				rightSubArray[i - mid] = inputArray[i];
			}
			addLogs(inputArray, supportArray, new int[] {-1, -1}, 
					"Split the array (index " + start + " to " + end + ") into partitions (at index " + (start + mid) + ")");
			
			mergeSort(leftSubArray, start, start + mid);
			mergeSort(rightSubArray, start + mid, end);
			ArrayUtils.slice(leftSubArray, supportArray, start, start + mid);
			ArrayUtils.slice(rightSubArray, supportArray, start + mid, end);
			addLogs(inputArray, supportArray, new int[] {-1, -1}, 
					"Merge partitions (index " + start + " to " + (start + mid - 1) + ") and (index " + (start + mid) + " to " + end + ")" );
			
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < leftSubArray.length && j < rightSubArray.length) {
				if (leftSubArray[i] <= rightSubArray[j]) {
					inputArray[k] = leftSubArray[i];
					i++;
					String sign = " => ";
					addLogs(inputArray, supportArray, new int[] {k, k},
							"Since " + leftSubArray[i-1] + " (left partition)" + sign + rightSubArray[j] + " (right partition), " + "we take " + inputArray[k]);
				} else {
					inputArray[k] = rightSubArray[j];
					j++;
					String sign = " < ";
					addLogs(inputArray, supportArray, new int[] {k, k},
							"Since " + leftSubArray[i] + " (left partition)" + sign + rightSubArray[j-1] + " (right partition), " + "we take " + inputArray[k]);
				}
				k++;
			}
			
			while (i < leftSubArray.length) {
				inputArray[k] = leftSubArray[i];
				addLogs(inputArray, supportArray, new int[] {k, k},
						"Since right partition is empty, we take " + inputArray[k] + " (left partition)");
				i++;
				k++;
			}
			while (j < rightSubArray.length) {
				inputArray[k] = rightSubArray[j];
				addLogs(inputArray, supportArray, new int[] {k, k},
						"Since left partition is empty, we take " + inputArray[k] + " (right partition)");
				j++;
				k++;
			}
		}
	}
	
	// test
	public static void main(String[] args) {
		int[] array = new int[] {3, 3, 2, 1, 1, 2, 0};
		MergeSort sorter = new MergeSort(array);
		sorter.sort();
		for (String e : sorter.getGuideLog()) {
			System.out.println(e);
		}
	}
}