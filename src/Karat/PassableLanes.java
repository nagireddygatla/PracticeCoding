package Karat;

import java.util.*;

public class PassableLanes {

    private static List<Set<Integer>> passableLanes(char [][] lanes){
        int rows=lanes.length;
        int cols = lanes[0].length;

        Set<Integer> rowsSet = new HashSet<>();

        for(int i=0; i< rows; i++){
            rowsSet.add(i);
        }
        Set<Integer> colSet = new HashSet<>();

        for(int i=0; i< cols; i++){
            colSet.add(i);
        }

        for(int i=0; i<rows;i++){
            for(int j=0; j< cols; j++){
                if(lanes[i][j]=='+'){
                    rowsSet.remove(i);
                    colSet.remove(j);
                }

            }
        }

        List<Set<Integer>> result = new ArrayList<>();
        result.add(rowsSet);
        result.add(colSet);



        return result;
    }

    public static void main(String [] args){
        char [][] straight_board_1 = {{'+', '+', '+', '0', '+', '0', '0'},
                        {'0', '0', '0', '0', '0', '0', '0'},
                        {'0', '0', '+', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '+', '0', '0'},
                        {'+', '+', '+', '0', '0', '0', '+'}};

        char [][] straight_board_2 = {{'+', '+', '+', '0', '+', '0', '0'},
                        {'0', '0', '0', '0', '0', '+', '0'},
                        {'0', '0', '+', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '+', '0', '0'},
                        {'+', '+', '+', '0', '0', '0', '+'}};

        char[][] straight_board_3 = {{'+', '+', '+', '0', '+', '0', '0'},
                        {'0', '0', '0', '0', '0', '0', '0'},
                        {'0', '0', '+', '+', '0', '+', '0'},
                        {'0', '0', '0', '0', '+', '0', '0'},
                        {'+', '+', '+', '0', '0', '0', '+'}};

        char [][] straight_board_4 = {{'+'}};

        System.out.println(Arrays.toString(passableLanes(straight_board_1).toArray()));
        System.out.println(Arrays.toString(passableLanes(straight_board_2).toArray()));
        System.out.println(Arrays.toString(passableLanes(straight_board_3).toArray()));
        System.out.println(Arrays.toString(passableLanes(straight_board_4).toArray()));

    }
}
