package visualso.data;

import java.util.Random;

import visualso.exception.DataTypeException;
import visualso.exception.NullException;
import visualso.exception.OutOfBoundException;

public class DataGuard {
    public static int[] randomArray(int qtBound, int maxValue) {
    	Random generator = new Random();
        int[] randArray = new int[generator.nextInt(2, qtBound)];
        for (int i = 0; i < randArray.length; i++) {
        	randArray[i] = generator.nextInt(1, maxValue);
        }
        return randArray ;
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static int[] parseString(String str, int qtBound, int valueBound) 
    		throws DataTypeException, OutOfBoundException, NullException {
        String str1 = str;
        str1 = str1.replaceAll(" ","");
        String[] tokens = str1.split(",");
        if (tokens.length == 1) {
            tokens = str.split(" ");
        }
        int[] inputArray = new int[tokens.length];
        if (isNullOrEmpty(str)) {
            throw new NullException("None of array was passed. Please pass an array.");
        }

        if (tokens.length > qtBound) {
            throw new OutOfBoundException("A valid array only contains up to " + qtBound + " elements");
        }

        try {
            for (int i = 0; i <tokens.length; i++) {
                inputArray[i] = Integer.parseInt(tokens[i]);
                if (inputArray[i] < 0) {
                    throw new NumberFormatException();
                } else if (inputArray[i] > valueBound) {
                	throw new OutOfBoundException("A valid array only has the max value smaller than " + valueBound);
                }
            }
        } catch (NumberFormatException e) {
            throw new DataTypeException("A valid array only contains positive integers");
        }

        return inputArray;
    }
}