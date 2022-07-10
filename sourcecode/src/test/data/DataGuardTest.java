package test.data;

import data.DataGuard;
import exception.DataTypeException;
import exception.NullException;
import exception.OutOfBoundException;

public class DataGuardTest {
	public static void main(String[] args) {
		DataGuard dataHandler = new DataGuard();
		
		// Random array test
		int[] randomArray = dataHandler.randomArray(20, 50);
		for (int e : randomArray) {
			if (e <= 50 && e >= 1) {
				System.out.println(e);
			} else {
				System.out.println("FAILED " + e);
				break;
			}
		}
		
		// Parse string
		String virtualInput = "10, 9, 11, 50, 1000000";
		try {
			dataHandler.parseString(virtualInput, 20, 50);
		} catch (DataTypeException | OutOfBoundException | NullException e1) {
			e1.printStackTrace();
		}
	}
}
