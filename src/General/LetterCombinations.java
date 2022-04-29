package General;

import java.util.*;

public class LetterCombinations {


    public static List<String> letterCombinations(String input){
        List<String> list = new ArrayList<>();
        Map<Character,char[]> digitCharsMap = new HashMap<>();

        digitCharsMap.put('2',new char[]{'a','b','c'});
        digitCharsMap.put('3',new char[]{'d','e','f'});
        digitCharsMap.put('4',new char[]{'g','h','i'});
        digitCharsMap.put('5',new char[]{'j','k','l'});
        digitCharsMap.put('6',new char[]{'m','n','o'});
        digitCharsMap.put('7',new char[]{'p','q','r','s'});
        digitCharsMap.put('8',new char[]{'t','u','v'});
        digitCharsMap.put('9',new char[]{'w','x','y','z'});

        char [] arr = new char[input.length()];

        helperCombination(input,0,digitCharsMap, arr,list);
        return list;
    }


    public static  void helperCombination(String input, int index, Map<Character,char []> digitCharsMap, char [] arr, List<String> list){

        if(index == input.length()){
            list.add(new String(arr));
            return;
        }

        char num = input.charAt(index);
        char [] candidates = digitCharsMap.get(num);

        for( int i = 0; i < candidates.length; i++){
            arr[index] = candidates[i];
            helperCombination(input, index+1, digitCharsMap,arr,list);
        }
    }

    public static void main(String [] args){

        List<String> list = letterCombinations("234");
        System.out.println(Arrays.toString(list.toArray()));
    }


}
