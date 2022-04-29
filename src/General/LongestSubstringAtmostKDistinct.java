package General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstringAtmostKDistinct {

    //abac
    private static int twoDistinct(String input, int k){
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int result = 0;

        for(int i=0; i<input.length(); i++){
            char curr = input.charAt(i);
            map.put(curr, map.getOrDefault(curr,0)+1);
            if(map.size()<=k){
                result = Math.max(result, i-start+1);
            }
            else{
                while(map.size()>k){
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
        String input = "abcadcacacaca";
        int k = 3;
        System.out.println(twoDistinct(input,k));

        Map<String, List<Integer>> map = new HashMap<>();

        map.computeIfAbsent("temp", l -> new ArrayList<>()).add(1);

    }
}
