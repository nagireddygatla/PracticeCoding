package Karat;

import java.util.ArrayList;
import java.util.List;

public class Rectangles {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 1, 1, 1, 1}};
        System.out.println(getCoordinates1(matrix));
        matrix = new int[][]{{1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 1, 1, 1, 0}};
        System.out.println(getCoordinates(matrix));
        matrix = new int[][]{{0, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}};
        System.out.println(getCoordinates(matrix));
    }

    public static List<List<Integer>> getCoordinates1(int [][] matrix){
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> startPair = new ArrayList<>();

        outerloop:
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j]==0) {
                    startPair.add(i);
                    startPair.add(j);
                    break outerloop;
                }
            }
        }

        List<Integer> endPair = new ArrayList<>();
        outerloop:
        for(int i= matrix.length-1; i>=0; i--){
            for(int j=matrix[0].length-1; j>=0; j--){
                if(matrix[i][j]==0) {
                    endPair.add(i);
                    endPair.add(j);
                    break outerloop;
                }
            }
        }
        list.add(startPair);
        list.add(endPair);
        return list;
    }

    public static List<List<List<Integer>>> getCoordinates(int[][] matrix){
        List<List<List<Integer>>> list = new ArrayList<>();

        for(int i=0; i< matrix.length; i++){
            for(int j=0; j< matrix[0].length; j++){
                if(matrix[i][j]==0){
                    List<Integer> startPair = new ArrayList<>();
                    startPair.add(i);
                    startPair.add(j);
                    int width = j;
                    while(width<matrix[0].length && matrix[i][width]==0)
                        width++;

                    width = width-1;
                    int depth = i;

                    while(depth<matrix.length && matrix[depth][j]==0)
                        depth++;
                    depth = depth-1;

                    List<Integer> endPair = new ArrayList<>();
                    endPair.add(depth);
                    endPair.add(width);
                    makeAllOnes(matrix, startPair, endPair);
                    List<List<Integer>> totalPair = new ArrayList<>();
                    totalPair.add(startPair);
                    totalPair.add(endPair);
                    list.add(totalPair);
                }
            }
        }
        return list;
    }

    private static void makeAllOnes(int[][] matrix, List<Integer> startPair, List<Integer> endPair) {
        int startX = startPair.get(0);
        int startY = startPair.get(1);
        int endX = endPair.get(0);
        int endY = endPair.get(1);

        for(int i= startX; i<=endX; i++){
            for(int j=startY; j<=endY; j++)
                matrix[i][j] = 1;
        }
        return;
    }


}
