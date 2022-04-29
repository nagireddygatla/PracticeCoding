package Karat;

import java.util.*;

public class BoardMoves {

    private static List<List<Integer>> possibleMoves(int [][] board, int [] pos){

        int currRow = pos[0];
        int currCol = pos[1];
        List<List<Integer>> result = new ArrayList<>();

        int [][] arr = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0; i< arr.length; i++){
            int row = currRow+arr[i][0];
            int col = currCol+arr[i][1];
            List<Integer> list = new ArrayList<>();
            if(row>=0 && row< board.length && col >=0 && col< board[0].length && board[row][col]!=-1){
                list.add(row);
                list.add(col);
                result.add(list);
            }
        }
        return result;
    }

    private static List<List<String>> findTreasure(int [][] board, int [] start, int [] end){
        List<List<String>> result = new ArrayList<>();
        List<String> currList = new ArrayList<>();
        boolean [][] visited = new boolean[board.length][board[0].length];

        for(int i=0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(i==start[0] && j== start[1]){
                    dfs(i,j,end[0],end[1],board, currList,visited,result);
                }
            }
        }
        return result;
    }

    private static void dfs(int currRow, int currCol, int finRow, int finCol, int [][] board,
                            List<String> currList, boolean[][] visited, List<List<String>> result) {

        if(currRow<0 || currRow>= board.length || currCol<0 || currCol>= board[0].length || board[currRow][currCol]==-1 || visited[currRow][currCol])
            return;

        visited[currRow][currCol] = true;
        currList.add(""+ "(" + currRow+","+currCol+") ");


        if(currRow==finRow && currCol==finCol){
            List<String> list = new ArrayList<>(currList);
            result.add(list);
            return;
        }

        dfs(currRow+1, currCol, finRow, finCol, board, currList, visited, result);
        dfs(currRow-1, currCol, finRow, finCol, board, currList, visited, result);
        dfs(currRow, currCol+1, finRow, finCol, board, currList, visited, result);
        dfs(currRow, currCol-1, finRow, finCol, board, currList, visited, result);
        visited[currRow][currCol] = false;
        currList.remove(currList.size()-1);


    }


    public static void main(String [] args){

        int [][] board3_1 = {
    {  1,  0,  0, 0, 0 },
    {  0, -1, -1, 0, 0 },
    {  0, -1,  0, 1, 0 },
    { -1,  0,  0, 0, 0 },
    {  0,  1, -1, 0, 0 },
    {  0,  0,  0, 0, 0 },
};
        int [] pos = {0,2};
        System.out.println(possibleMoves(board3_1, pos));

        int [][] board3_2 = {
    {  0,  1, -1 },
    {  0,  0,  0 },
    {  0,  0,  0 },
};
        System.out.println(possibleMoves(board3_2, pos));

        //  [(5, 2), (5, 1), (4, 1), (3, 1), (3, 2), (2, 2), (2, 3), (1, 3), (0, 3), (0, 2), (0, 1), (0, 0), (1, 0), (2, 0)]
        System.out.println(findTreasure(board3_1, new int[]{0, 0}, new int[]{4, 1}));

         //[(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (2, 2), (3, 2), (3, 1), (4, 1)]

        //        [(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (3, 3), (3, 2), (3, 1), (4, 1)]


        
    }
}
