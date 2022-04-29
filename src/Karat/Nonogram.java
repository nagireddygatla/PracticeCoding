package Karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nonogram {

    public boolean isValidNonogram(int [][] matrix, int [][] rows, int [][] cols) {
        if (matrix==null || rows==null || cols==null) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 || m != rows.length || n != cols.length) {

            return false;
        }

        return isNonogramRowsValid(matrix, rows, m, n) && isNonogramColsValid(matrix, cols, m, n);
    }


    public boolean isNonogramRowsValid(int [][] matrix, int [][] rows, int m, int n) {


        for (int i = 0; i < m; i++) {
            int count = 0;
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    count++;
                }
                else{
                    if(count!=0) {
                        list.add(count);
                        count = 0;
                    }
                }
            }
            if(count!=0)
                list.add(count);
            if(list.size()!=rows[i].length){
                return false;
            }

            for(int k=0; k< rows[i].length; k++){
                if(list.get(k)!=rows[i][k])
                    return false;
            }

        }
        return true;
    }


    public boolean isNonogramColsValid(int [][] matrix, int [][] cols, int m, int n) {
        for (int j = 0; j < n; j++) {
            int count = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (matrix[i][j] == 0) {
                    count++;
                }
                else{
                    if(count!=0) {
                        list.add(count);
                        count = 0;
                    }
                }
            }
            if(count!=0)
                list.add(count);

            if(list.size()!=cols[j].length){
                return false;
            }

            for(int k=0; k< cols[j].length; k++){
                if(list.get(k)!=cols[j][k])
                    return false;
            }

        }
        return true;
    }

    public static void main(String [] args){
        int [][] matrix1 = {
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 0, 0},
                {1, 1, 0, 1},
                {0, 0, 1, 1}};

        int [][] rows1_1 = {{},{1},{1,2},{1},{2}};
        int [][] columns1_1 = {{2,1},{1},{2},{1}};
        Nonogram obj = new Nonogram();
        System.out.println(obj.isValidNonogram(matrix1,rows1_1,columns1_1));

        int [][] rows1_2 = {{},{},{1},{1},{1,1}};
        int [][] columns1_2 = {{2},{1},{2},{1}};
        System.out.println(obj.isValidNonogram(matrix1,rows1_2,columns1_2));

        int [][] matrix2 = {
                {1, 1},
                {0, 0},
                {0, 0},
                {1, 0}};
        int [][] rows2_1 = {{},{2},{2},{1}};
        int [][] columns2_1 = {{1,1},{3}};
        System.out.println(obj.isValidNonogram(matrix2,rows2_1,columns2_1));


        int [][] rows2_2 = {{}, {2}, {2}, {1}};
        int [][] columns2_2 = {{3}, {3}};
        System.out.println(obj.isValidNonogram(matrix2,rows2_2,columns2_2));


        int [][] rows2_3 = {{}, {}, {}, {}};
        int [][] columns2_3 = {{}, {}};
        System.out.println(obj.isValidNonogram(matrix2,rows2_3,columns2_3));



    }
}
