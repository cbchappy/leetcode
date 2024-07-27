import java.util.HashMap;
import java.util.Map;

/**
 * @Author Cbc
 * @DateTime 2024/7/26 22:36
 * @Description
 */
public class Solution {

    //316的重写官方题解
    public String removeDuplicateLetters(String s) {
        StringBuilder builder = new StringBuilder();
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            nums[index] = nums[index] + 1;
        }
        boolean[] used = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!used[c - 'a']) {
                while (builder.length() > 0 && builder.charAt(builder.length() - 1) >= c) {
                    char mid = builder.charAt(builder.length() - 1);
                    if (nums[mid - 'a'] == 1) {
                        break;
                    }
                    int in = mid - 'a';
                    used[in] = false;
                    nums[in] = nums[in] - 1;
                    builder.deleteCharAt(builder.length() - 1);
                }
                builder.append(c);
                used[c - 'a'] = true;
            }else {
                nums[c - 'a'] = nums[c - 'a'] - 1;
            }

        }
        //bcabc  bcabc cb
        return builder.toString();

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicateLetters("bccab"));
    }
}
