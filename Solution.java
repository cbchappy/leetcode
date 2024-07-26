import java.util.HashMap;
import java.util.Map;

/**
*@Author Cbc
*@DateTime 2024/7/26 22:36
*@Description
*/
public class Solution {

    //316
    public String removeDuplicateLetters(String s) {
        //num 个数  是否被使用  最靠前的
        Map<Character, int[]> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                int[] v = new int[4];
                v[0] = 1;
                v[2] = i;
                v[3] = i;
                map.put(c, v);
            }else {
                int[] ints = map.get(c);
                ints[0] = ints[0] + 1;
                ints[3] = i;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int[] v = map.get(c);
            if(v[0] == 1){
                builder.append(c);
                continue;
            } else if (v[1] == 1) {
                continue;
            }

            char after = ' ';
            for(int n = i + 1; n < s.length(); n++){
                char m = s.charAt(n);
                if(m == c){
                    continue;
                }
                int[] vm = map.get(m);
                if(vm[1] == 1){
                   continue;
                }
                if(vm[0] == 1){
                    after = m;
                    break;
                }
                if(m > c && vm[3] == n){
                    after = m;
                    break;
                }
                if(m < c){
                    after = m;
                    break;
                }
            }

            if(after == ' '){
                builder.append(c);
                break;
            }

            if(after > c){
                builder.append(c);
                v[1] = 1;
            }else {
                v[0] = v[0] - 1;//"bcabc"
            }
        }
        //abacb
        //bccab
        return builder.toString();

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicateLetters("bccab"));
    }
}
