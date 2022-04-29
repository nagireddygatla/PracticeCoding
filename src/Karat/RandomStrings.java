package Karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomStrings {

    private static String findWord(String [] words, String keyString){

        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i< keyString.length(); i++){
            char c = keyString.charAt(i);
            map.put(c, map.getOrDefault(c,0)+1);
        }

        for(int i=0; i< words.length; i++){
            String word = words[i];
            Map<Character, Integer> wordMap = new HashMap<>();
            boolean found = true;
            for(int j=0; j< word.length(); j++){
                char c = word.charAt(j);
                wordMap.put(c, wordMap.getOrDefault(c,0)+1);
            }
            for(Map.Entry<Character, Integer> mapItem: wordMap.entrySet()){
                if(map.getOrDefault(mapItem.getKey(),0) < mapItem.getValue()){
                    found = false;
                    break;
                }
            }
            if(found)
                return word;

        }
        return "NONE";
    }

    private static List<List<String>> getWordMatrix(char [][] characters, String searchWord){
        List<List<String>> result = new ArrayList<>();
        int row = characters.length;
        int col = characters[0].length;


        for(int i=0; i< row; i++){
            for(int j=0; j< col; j++){
                if(characters[i][j]==searchWord.charAt(0)){
                    boolean [][] visited = new boolean[row][col];
                    List<String> currList = new ArrayList<>();
                    int index=0;
                    dfsCharacters(characters, searchWord, i,j,visited, currList, result, index);
                }
            }
        }
        return result;

    }

    private static void dfsCharacters(char[][] characters, String searchWord, int currRow, int currCol,
                                      boolean[][] visited, List<String> currList, List<List<String>> result, int index) {

        if(currRow<0 || currRow>=characters.length || currCol <0 || currCol>=characters[0].length
                || visited[currRow][currCol] || characters[currRow][currCol] != searchWord.charAt(index)){
            return;
        }

        visited[currRow][currCol] = true;
        currList.add("(" + currRow+ ","+ currCol+") ");

        if(searchWord.length()-1==index){
            List<String> temp = new ArrayList<>(currList);
            result.add(temp);
            return;
        }
        dfsCharacters(characters, searchWord, currRow+1,currCol, visited, currList, result, index+1);
        dfsCharacters(characters, searchWord, currRow,currCol+1, visited, currList, result, index+1);
        visited[currRow][currCol] = false;
        currList.remove(currList.size()-1);

    }


    public static void main(String [] args){
        String [] words = {"cat", "baby", "dog", "bird", "car", "ax"};
        String string1 = "tcabnihjs";
        String string2 = "tbcanihjs";
        String string3 = "baykkjl";
        String string4 = "bbabylkkj";
        String string5 = "ccc";
        String string6 = "nbird";
//        System.out.println(findWord(words, string1));
//        System.out.println(findWord(words, string2));
//        System.out.println(findWord(words, string3));
//        System.out.println(findWord(words, string4));
//        System.out.println(findWord(words, string5));
//        System.out.println(findWord(words, string6));

        //Second part here:

        char [][] grid1 = {
                {'c', 'c', 'c', 'a', 'r', 's'},
                {'c', 'c', 'i', 't', 'n', 'b'},
                {'a', 'c', 'n', 'n', 't', 'i'},
                {'t', 'c', 'i', 'i', 'p', 't'}};
        String word1 = "catnip";
        String word2 = "cccc";
        //System.out.println(getWordMatrix(grid1, word1));
       // System.out.println(getWordMatrix(grid1, word2));
        char [][] grid2 = {
    {'c', 'p', 'a', 'n', 't', 's'},
    {'a', 'b', 'i', 't', 'a', 'b'},
    {'t', 'f', 'n', 'n', 'c', 'i'},
    {'x', 's', 'c', 'a', 't', 'n'},
    {'x', 's', 'd', 'd', 'e', 'a'},
    {'s', 'q', 'w', 'x', 's', 'p'}
};

        String word3 = "catnap";
        //find_word_location(grid2, word2)-> { (3, 2), (3, 3), (3, 4), (3, 5), (4, 5), (5, 5) }
        //System.out.println(getWordMatrix(grid2, word3));

        char [][] grid3 = {
    {'c', 'r', 'c', 'a', 'r', 's'},
    {'a', 'b', 'i', 't', 'n', 'i'},
    {'t', 'f', 'n', 'n', 'x', 'p'},
    {'x', 's', 'i', 'x', 'p', 't'}
};
        String word4 = "catnip";
        //find_word_location(grid3, word3)-> { (0, 2), (0, 3), (1, 3), (1, 4), (1, 5), (2, 5) }
        System.out.println(getWordMatrix(grid3, word4));

        char [][] grid4 = {
    {'a', 'o', 'o', 'o', 'a', 'a'},
    {'b', 'b', 'i', 't', 'n', 'i'},
    {'c', 'f', 'n', 'n', 'v', 'p'},
    {'o', 'a', 'a', 'a', 'o', 'o'}
};
        String word5 = "aaa";
        String word6 = "ooo";
        //System.out.println(getWordMatrix(grid4, word5));
        //System.out.println(getWordMatrix(grid4, word6));

    }
}
