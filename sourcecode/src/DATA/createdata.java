package DATA;
import java.util.Random;

public class createdata{
    static Random generator = new Random();
    static int n = generator.nextInt(100) + 1;
    public static Object data_result(String arr){
        if (arr == "random"){
            return random_arr.random_array(n);
        }
        else{
            if (data_processing.isNullOrEmpty(arr) == false && data_processing.StringToIntArray(arr).length > 0){
                return data_processing.StringToIntArray(arr);
            }
        }
        

        return "cant sort";
    }

    // //test
    // public static void main(String[] args){
    //     for (int i = 0; i < data_processing.StringToIntArray("12 ,2424, 121212, 433").length; i++) {
    //         System.out.println(data_processing.StringToIntArray("12 2424 121212 433")[i]);
    //     }
    //     int[] k = (int[]) data_result("random");
    //     System.out.println(data_result("12 ,2424, 121212, 433"));
    //     System.out.println(k.length);
    //     for (int i = 0; i < k.length; i++){
    //         System.out.println(k[i]);
    //     }
    // }

    

}