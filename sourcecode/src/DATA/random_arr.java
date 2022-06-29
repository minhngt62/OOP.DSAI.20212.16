package DATA;
import java.util.Random;

public class random_arr {
    public static int[] random_array(int n){
        int[] result = new int[n];
        for (int i = 0; i < result.length; i++){
            Random generator = new Random();
            int k = generator.nextInt(2*n) + 1;
            if (data_processing.not_includes(result,k)){
                result[i] = k;
            }
        }
        return result ;
    }
}
