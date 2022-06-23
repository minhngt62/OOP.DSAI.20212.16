import java.util.Random;

public class createdata{
    static Random generator = new Random();
    static int n = generator.nextInt(100) + 1;
    public static Object data_result(String arr, String soft_method){
        if (arr == "random"){
            if (soft_method == ""){
                Random generator = new Random();
                int n = generator.nextInt(10) + 1;
                return random_arr.random_array(n);
            }
            else{
                return random_arr.random_array(n);
            }
        }
        else{
            if (data_processing.isNullOrEmpty(arr) == false && data_processing.StringToIntArray(arr).length > 0){
                if (soft_method == "" && data_processing.StringToIntArray(arr).length > 10){
                    return "The length of string is too long";
                }
                else {
                    return data_processing.StringToIntArray(arr);
                }
            }
        }
        

        return "cant soft";
    }

    //test
    public static void main(String[] args){
        for (int i = 0; i < data_processing.StringToIntArray("12 ,2424, 121212, 433").length; i++) {
            System.out.println(data_processing.StringToIntArray("12 2424 121212 433")[i]);
        }
    }

}