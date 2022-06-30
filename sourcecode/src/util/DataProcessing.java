public class DataProcessing {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNumber(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    public static int[] StringToIntArray(String str) {
        String str1 = str;
        str1 = str1.replaceAll(" ","");
        String[] items = str1.split(",");
        if (items.length == 1){
            items = str.split(" ");
        }
        int[] result = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            if (isNumber(items[i])){
                result[i] = Integer.parseInt(items[i]);
                //System.out.println(items[i]);
            }
            else{
                return new int[0];
            }
        }
        return result;
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
