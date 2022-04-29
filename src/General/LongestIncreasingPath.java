package General;

public class LongestIncreasingPath {
        public static int longIncreasingPath(int[][] matrix) {
            if(matrix==null || matrix.length==0)return 0;

            int result = 0;
            int [] max = new int[1];

            for(int i=0; i<matrix.length; i++){
                for(int j=0;j<matrix[0].length; j++){
                    dfsIncreasing(matrix,i,j,1,max);
                }
            }
            return max[0];
        }

        private static void dfsIncreasing(int [][] matrix, int currRow, int currCol, int count, int [] max){
            max[0] = Math.max(max[0],count);

            int [] dx = {1,0,-1,0};
            int [] dy = {0,1,0,-1};

            for(int i=0; i<4; i++){
                int x = currRow + dx[i];
                int y = currCol + dy[i];
                if(x>=0 || x<matrix.length || y>=0 || y<matrix[0].length || matrix[currRow][currCol] < matrix[x][y])
                    dfsIncreasing(matrix,x,y,count+1,max);
            }
        }

    public static int longestIncreasingPathDP(int[][] matrix) {
        int result = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] mem = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = helper(matrix, mem, i, j);
                result = Math.max(result, t);
            }
        }

        return result;
    }

    private static int helper(int[][] matrix, int[][] mem, int i, int j) {
        if (mem[i][j] > 0) {
            return mem[i][j];
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x >= 0 && y >= 0
                    && x < matrix.length
                    && y < matrix[0].length
                    && matrix[x][y] > matrix[i][j]) {
                mem[i][j] = Math.max(mem[i][j], helper(matrix, mem, x, y));
            }
        }

        return mem[i][j]=mem[i][j]+1;
    }

        public static void main(String [] args) {
            int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
            //int res = longIncreasingPath(matrix);
            int res1 = longestIncreasingPathDP(matrix);
            //System.out.println(res);
            System.out.println(res1);
        }


    }

