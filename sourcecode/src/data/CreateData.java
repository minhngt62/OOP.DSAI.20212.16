package data;

import java.net.PortUnreachableException;
import java.util.Random;

import exception.DataTypeException;
import exception.NullException;
import exception.OutOfBoundException;

public class CreateData{
    public static int[] randomArray(int n, int maxValue){
        int[] result = new int[n];
        for (int i = 0; i < result.length; i++){
            Random generator = new Random();
            int k = generator.nextInt(maxValue) + 1;
            if (notIncludes(result,k)){
                result[i] = k;
            }
            else {
            	result[i] = generator.nextInt(k)+1;
            }
        }
        return result ;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNumber(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    public static int[] StringToIntArray(String str, int qtBound, int valueBound) throws DataTypeException, OutOfBoundException, NullException{
        String str1 = str;
        str1 = str1.replaceAll(" ","");
        String[] tokens = str1.split(",");
        if (tokens.length == 1){
            tokens = str.split(" ");
        }
        int[] inputArray = new int[tokens.length];
        if (isNullOrEmpty(str)){
            throw new NullException("None of array was passed. Please pass an array.");
        }

        if (tokens.length > qtBound){
            throw new OutOfBoundException("A valid array only contains up to " + qtBound + " elements");
        }

        try{
            for (int i = 0; i <tokens.length; i++){
                inputArray[i] = Integer.parseInt(tokens[i]);
                if (inputArray[i] < 0){
                    throw new NumberFormatException();
                } else if (inputArray[i] > valueBound){
                    throw new OutOfBoundException("A valid array only has the max value smaller than " + valueBound);

                }
            }
        } catch (NumberFormatException e){
            throw new DataTypeException("A valid array only contains positive integers");
        }

        return inputArray;



    }

    public static boolean notIncludes(int[] m, int n){
        for (int i = 0; i < m.length; i++){
            if (m[i] == n){
                return false;
            }
        }
        return true;
    }


}