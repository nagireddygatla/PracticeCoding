package General;

/*
Warner media is in the movie ticket sales.
Movie theaters may want to pack every seat, we want to keep our customers safe,
by keeping them away from each other.

After ticket sales, a movie theater will send their seating arrangements.

A seating arrangement comes in the following format.

[
  O, 1, 2, 3, O
  1, 2, 3, 2, 1
  2, 3, O, 3, 2
]
"O" means a seat is occupied
"_" means an empty seat

Please write an algorithm that will determine that between any two occupant
that can be no less than k seats apart.

Using the example above,
3 movie-goers are watching "Tom and Jerry" in a small theater that has 15 seats.
We will pass above seating arrangement into our function
and we want to keep 3 seats in between any two customers.
The algorithm should return "true",

However if we pass the following seating arrangement

[
  O, _, _, _, O
  _, _, _, _, _
  _, _, O, _, _
]
with 4 customers and 3 seats-in-between requirement,
the algorithm should return "false"
*/

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class HBOMax1 {

    private static boolean seatingValidity(char [][] seatingArrangement, int k){

        if(seatingArrangement == null || seatingArrangement.length==0 || k < 1) return true;
        int m = seatingArrangement.length;
        int n = seatingArrangement[0].length;

        boolean visited [][] = new boolean[m][n];

        for(int i=0; i< m; i++){
            for(int j=0; j< n; j++){
                if(seatingArrangement[i][j]=='O'){
                    if(!dfsSeating(seatingArrangement, i, j, k+1, 0, visited))
                        return false;
                }
            }
        }
        return true;
    }

    private static  boolean dfsSeating(char [][] seatingArrangement, int i, int j, int k, int curr, boolean [][] visited){

        int m = seatingArrangement.length;
        int n = seatingArrangement[0].length;

        if(i <0 || i>=m  || j<0 || j>=n || visited[i][j] || curr == k)
            return true;

        if(seatingArrangement[i][j]=='O' && curr != 0)
            return false;

        visited[i][j] = true;

        int dx [] = {-1,0,1,0};
        int dy [] = {0,1,0,-1};

        for(int l=0; l<4; l++){

            int posX = i + dx[l];
            int posY = j + dy[l];

            if(!dfsSeating(seatingArrangement,  posX,  posY,  k,  curr+1, visited))
                return false;


        }
        visited[i][j] = false;

        return true;


    }

    public static void main(String[] args) {
        char [][] seatingArrangement = {{'O', '_', '_', '_', 'O'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', 'O', '_', '_'}};

        boolean valid = seatingValidity(seatingArrangement, 5);

        System.out.println("Valid Arrangement: "+ valid);
    }
}

