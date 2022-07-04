package utils;

import java.util.Random;

public class RandomArray {
    public static int[] random_array(int n, int maxValue){
        int[] result = new int[n];
        for (int i = 0; i < result.length; i++){
            Random generator = new Random();
            int k = generator.nextInt(maxValue) + 1;
            if (DataProcessing.notIncludes(result,k)){
                result[i] = k;
            }
            else {
            	result[i] = generator.nextInt(k)+1;
            }
        }
        return result ;
    }
}