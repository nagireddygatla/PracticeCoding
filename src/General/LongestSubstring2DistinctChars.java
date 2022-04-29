package General;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring2DistinctChars {

    //abac
    private static int twoDistinct(String input){
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int result = 0;

        for(int i=0; i<input.length(); i++){
            char curr = input.charAt(i);
            map.put(curr, map.getOrDefault(curr,0)+1);
            if(map.size()>2){
                result = Math.max(result, i-start);
                while(map.size()>2){
                    char remove = input.charAt(start);
                    int count = map.get(remove);
                    if(count==1)
                        map.remove(remove);
                    else
                        map.put(remove,count-1);
                    start++;
                }
            }
        }
        return result;
    }

    public static void main(String [] args){
        String input = "abcbbbbcccbdddadacb";
        System.out.println(twoDistinct(input));

    }
}
