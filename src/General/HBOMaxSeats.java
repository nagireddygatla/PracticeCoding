package General;
import java.io.*;
import java.util.*;

class HBOMaxSeats {

    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        HBOMaxSeats sol = new HBOMaxSeats();
        char[][] grid = new char[][]{
                {'0', '_', '_', '_', '0'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', '0', '_', '_'}};
        System.out.println(sol.areOccupantsKSeatsApart(grid, 5));
    }

    public boolean areOccupantsKSeatsApart(char[][] grid, int k){
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == '0'){
                    grid[i][j] = '2';
                    if(!dfs(grid, i, j, k+1, 0, visited)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean dfs(char[][] grid, int i, int j, int k, int hop, boolean [][] visited){

        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || visited[i][j] || hop == k)
            return true; // hit wall

        if(grid[i][j] == '0'){
            return false;
        }
        visited[i][j] = true;
        boolean status = true;

        for(int[] dr : dir) {
            int x = dr[0] + i;
            int y = dr[1] + j;
            status = status && dfs(grid, x, y, k, hop + 1, visited);

        }

        return status;
        //System.out.println(i+","+j+"--"+hop+Arrays.toString(grid[0]));
    }
}