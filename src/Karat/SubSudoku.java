    package Karat;

    import java.util.HashSet;
    import java.util.Set;

    public class SubSudoku {

        private static boolean checkSudoku(int [][] sudoku, int n){
            Set<Integer> numbers = new HashSet<>();
            for(int i=1; i<=n; i++) {
                numbers.add(i);
            }

            for(int i=0; i<sudoku.length; i++){
                Set<Integer> newSet = new HashSet<>(numbers);
                for(int j=0; j< sudoku[0].length; j++){
                    if(sudoku[i][j]<1 || sudoku[i][j]>n)
                        return false;
                    newSet.remove(sudoku[i][j]);

                }
                if(newSet.size()>0)
                    return false;
            }

            for(int j=0; j<sudoku[0].length; j++){
                Set<Integer> newSet = new HashSet<>(numbers);
                for(int i=0; i< sudoku.length; i++){
                    if(sudoku[i][j]<1 || sudoku[i][j]>n)
                        return false;
                    newSet.remove(sudoku[i][j]);

                }
                if(newSet.size()>0)
                    return false;
            }
            return true;

        }

        public static void main(String [] args){
            int [][] input1 = {{1, 3, 2},
    {3, 1, 2},
    {2, 3, 1}}; //False
            System.out.println(checkSudoku(input1,3));

    int [][] input2 = {{1, 2, 3},
    {3, 1, 2},
    {2, 3, 1}}; //-> True
            System.out.println(checkSudoku(input2,3));

                    int [][] input3 = {{1, 2, 3},
    {1, 2, 3},
    {1, 2, 3}};// -> False
            System.out.println(checkSudoku(input3,3));

                 int [][] input4  =  {{1, 1, 1},
    {2, 2, 2},
    {3, 3, 3}}; //-> False
            System.out.println(checkSudoku(input4,3));

                  int [][] input5 = {{1000, -1000, 6},
    { 2, 3, 1},
    { 3, 1, 2}}; //-> False
            System.out.println(checkSudoku(input5,3));

                   int [][] input6 =  {{0}}; //-> False
            System.out.println(checkSudoku(input6,1));

                    int [][] input7 = {{3, 2, 3, 2},
    {2, 3, 2, 3},
    {3, 2, 3, 2},
    {2, 3, 2, 3}}; //-> False
            System.out.println(checkSudoku(input7,4));

                    int [][] input8 = {{2, 3, 4},
    {3, 4, 2},
    {4, 2, 3}}; //-> False
            System.out.println(checkSudoku(input8,3));

                    int [][] input9 = {{-1,-2,-3},
    {-2,-3,-1},
    {-3,-1,-2}}; //-> False
            System.out.println(checkSudoku(input9,3));

                    int [][] input10 = {{1,1,1},
    {1,1,2},
    {1,2,3}}; //-> False
            System.out.println(checkSudoku(input10,3));
        }
    }
