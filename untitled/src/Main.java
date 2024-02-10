



public class Main {
    public static void main(String[] args) {
        int[] listA = {4, 5, 5, 7, 2, 9, 3, 8, 4, 3 ,7, 1, 5, 2, 7, 8};
        int[] listB = {3, 9, 2, 3, 5, 9, 4, 2, 3, 3, 6, 8, 2, 2, 1, 6};
        int[][] matrixA = matrixMaker(listA, 4);
        int[][] matrixB = matrixMaker(listB, 4);
        int[][] bruteForce = dotProduct(matrixA, matrixB);
        int[][] otherWay = otherWay(matrixA, matrixB);
        int[][] strassemMatrix = strassen(matrixA, matrixB);

        System.out.println("\nBruteForce\n");
        printM(bruteForce);
        System.out.println("\n Strassen below\n");
        printM(strassemMatrix);
        System.out.println("\nOther Way\n");
        printM(otherWay);

    }


    /**
     * print matrix
     * @param m matrix to print
     */
    public static void printM(int[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m.length; j++){
                System.out.print(m[i][j] + ", ");
            }
            System.out.println("");
        }
    }

    /**
     * make the matrix
     * @param m row
     * @param num number of columns in row
     * @return n x n matrix
     */
    public static int[][] matrixMaker(int[] m, int num){
        int[][] max = new int [num][num];
        int count = 0;
        for(int i = 0; i < num; i++){
            for(int j = 0; j < num; j++){
                max[i][j] = m[count];
                count++;
            }
        }
        return max;
    }

    /**
     * getting the dot (matrix multiplication) product of 2 matrix
     * @param m1 matrix 1
     * @param m2 matrix 2
     * @return a combined m1 and m2 multiplied and stuff
     */
    public static int[][] dotProduct(int[][] m1, int[][] m2){
        int size = Math.min(m1.length, m2.length);
        int [][] mAnswer = new int[m1.length][m1.length];
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++) {
                int []m1r = getRow(m1, row);
                int []m2c = getCol(m2, col);
                mAnswer[row][col] = getAnswer(m1r, m2c);
            }
        }
        return mAnswer;
    }

    /**
     * The other methode to multiply matricies
     * Figured I should throw it in just because I was confused early on
     * and that this was the Strassen way. Well, I was wrong
     * This will run at n^3
     * @param m1 Matrix a
     * @param m2 Matrix b
     * @return the multiplied matrix
     */
    public static int[][] otherWay(int [][] m1, int [][] m2){
        //matrix A
        int[][] a = reduce(m1, 1);
        int[][] b = reduce(m1, 2);
        int[][] c = reduce(m1, 3);
        int[][] d = reduce(m1, 4);

        //matrix B
        int[][] e = reduce(m2, 1);
        int[][] f = reduce(m2, 2);
        int[][] g = reduce(m2, 3);
        int[][] h = reduce(m2, 4);

        int [][] i = addMatix(matrixMultiple(a,e),matrixMultiple(b,g));
        int [][] j = addMatix(matrixMultiple(a,f),matrixMultiple(b,h));
        int [][] k = addMatix(matrixMultiple(c,e),matrixMultiple(d,g));
        int [][] l = addMatix(matrixMultiple(c,f),matrixMultiple(d,h));

        return combineMatrix(i,j,k,l,4);
    }

    /**
     * method to break out the row
     * @param m1 matrix
     * @param row which row to break out
     * @return the broke-out row
     */
    public static int[] getRow(int[][] m1, int row){
        int[] result = new int [m1.length];
        for(int i = 0; i < m1.length; i++){
            result[i] = m1[row][i];
        }
        return result;
    }

    /**
     * what column to break out of matrix
     * @param m1 matrix to take the column out of
     * @param col what column you want to take out
     * @return the column you took out
     */
    public static int[] getCol(int[][] m1, int col){
        int[] result = new int [m1.length];
        for(int i = 0; i < m1.length; i++){
            result[i] = m1[i][col];
        }//end for
        return result;
    }//end getCol

    /**
     * For the franken brute force method.
     * It adds thing
     * @param m1 Matrix A
     * @param m2 Matrix b
     * @return the answer
     */
    public static int getAnswer(int[] m1, int[] m2){
        int result = 0;
        for(int i = 0; i < m1.length; i++){
            result += m1[i] * m2[i];
        }//end for
        return result;
    }

    /**
     * this is the matrix adder.
     * this is very import I have learned
     * @param m1 Matrix A
     * @param m2 Matrix b
     * @return the added together Matrix
     */
    public static int[][] addMatix(int [][] m1, int [][] m2){
        int [][] mc = new int[m1.length][m1.length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1.length; j++){
                mc[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return mc;
    }

    /**
     * Just like the adder, this will subtract two matrices
     * Order is really important, just like in Matrix Multiplication
     * @param m1 matrix a
     * @param m2 matrix b
     * @return the subtracted matrix a - b (Order important)
     */
    public static int[][] subtractMatix(int [][] m1, int [][] m2){
        int [][] mc = new int[m1.length][m1.length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m1.length; j++){
                mc[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return mc;
    }


    /**
     * The first strassen round that will get the square into things of 4 and then
     * do the strassen equation on them. It will even call the second strassen equation
     * to multiple for the first equation so its strassen all the way
     * @param m1 Matrix A
     * @param m2 Matrix B
     * @return the multiple matrix in .3 exponent of a time quicker.
     */
    public static int[][] strassen(int [][] m1, int [][]m2){


            //matrix A
            int[][] a = reduce(m1, 1);
            int[][] b = reduce(m1, 2);
            int[][] c = reduce(m1, 3);
            int[][] d = reduce(m1, 4);

            //matrix B
            int[][] e = reduce(m2, 1);
            int[][] f = reduce(m2, 2);
            int[][] g = reduce(m2, 3);
            int[][] h = reduce(m2, 4);

            int[][] s1 = attempt21(a, subtractMatix(f , h));
            int[][] s2 = attempt21(addMatix(a,b),h);
            int[][] s3 = attempt21(addMatix(c, d), e);
            int[][] s4 = attempt21(d, subtractMatix(g,e));
            int[][] s5ad = addMatix(a, d);
            int[][] s5eh = addMatix(e, h);
            int[][] s5 = attempt21(s5ad,s5eh);
            int[][] s6bd = subtractMatix(b, d);
            int[][] s6gh = addMatix(g,h);
            int[][] s6 = attempt21(s6bd,s6gh);
            int[][] s7ac = subtractMatix(a, c);
            int[][] s7ef = addMatix(e,f);
            int[][] s7 = attempt21(s7ac,s7ef);
            int[][] j = addMatix(s1, s2);
            int[][] i = subtractMatix(addMatix(addMatix(s5,s6), s4), s2);
            int[][] k = addMatix(s3, s4);
            int[][] l = addMatix(subtractMatix(subtractMatix(s1, s7), s3), s5);


            return combineMatrix(i, j, k, l, m1.length);

    }//end multiple, yea it was named something else

    /**
     * combined matrixs form 2X2 to a 4x4
     * @param q1 i
     * @param q2 j
     * @param q3 k
     * @param q4 l
     * @param n length of original matrix
     * @return answer
     */
    public static int[][] combineMatrix(int[][] q1, int[][] q2, int[][] q3, int[][] q4, int n){
        int [][] answer = new int [n][n];
        //q1
        int r = 0;
        int c = 0;
        for(int q = 1; q < 5; q++) {
            int[][] qi = q1;
            int cStart = 0;
            int rStart = 0;
            if (q == 2) {
                qi = q2;
                cStart = 2;
            } else if (q == 3) {
                qi = q3;
                rStart = 2;
            } else if (q == 4) {
                qi = q4;
                rStart = 2;
                cStart = 2;
            }
            c = cStart;
            r = rStart;
            for (int ro = 0; ro < 2; ro++) {
                for (int co = 0; co < 2; co++) {
                    answer[r][c] = qi[ro][co];
                    c++;
                }
                c = cStart;
                r++;
            }
        }
        return answer;
    }

    /**
     * Method used for the Strassen thing, figured if I was going to use Strassen, then I should use strassen all the way
     * THis method will multiple matrices together and save a whole whopping .3 exponent of time :)
     * Why attempt21 Well, let me tell you, on a Monday afternoon ......
     * @param a Matrix a
     * @param b Matrix b
     * @return The multiplied Matrix
     */
    public static int [][] attempt21(int[][] a, int[][] b){
        int[][] c = new int[a.length][a.length];
        //break down the matrix to S's algoritim
        int s1 = a[0][0] * (b[0][1] - b[1][1]);
        int s2 = (a[0][0] + a[0][1]) *b[1][1] ;
        int s3 = (a[1][0] + a[1][1]) * b[0][0];
        int s4 = a[1][1] * (b[1][0] - b[0][0]);
        int s5 = (a[0][0] + a[1][1]) * (b[0][0] + b[1][1]);
        int s6 = (a[0][1] - a[1][1]) * (b[1][0] + b[1][1]);
        int s7 = (a[0][0] - a[1][0]) * (b[0][0] + b[0][1]);
        c[0][0] = s5 + s6 + s4 - s2;
        c[0][1] = s1 + s2;
        c[1][0] = s3 + s4;
        c[1][1] = s1 - s7 - s3 + s5;
        return c;
    }

    /**
     * will reduce the matrix by half
     * @param m matrix to reduce
     * @param quad what quadrant you want back
     * @return that quadrant
     */
    public static int [][] reduce(int [][] m, int quad) {
        int len = m.length;
        int newLen = len / 2;
        //split m in half
        int[][] m1 = new int[newLen][newLen];
        int rowStart = 0;
        int rowEnd = 2;
        int colStart = 0;
        int colend = 2;
        if (quad == 2) {
            colStart = 2;
            colend = 4;
        } else if (quad == 3) {
            rowStart = 2;
            rowEnd = 4;
        } else if (quad == 4) {
            rowStart = 2;
            rowEnd = 4;
            colStart = 2;
            colend = 4;
        }
        int r = 0;
        int c = 0;
        for (int row = rowStart; row < rowEnd; row++) {
            for (int col = colStart; col < colend; col++) {
                m1[r][c] = m[row][col];
                c++;
            }//end for
            c = 0;
            r++;
        }//end row
        return m1;
    }

    /**
     * Requires 2x2 matrix which it would then compute the result
     * This is the other multiplier you talked about that takes n^3 time
     * This is still better than the brute force multiplier that I have up top
     * @param m1 -matrix a
     * @param m2 -matrix b
     * @return - the multiplied matrix
     */
    public static int[][] matrixMultiple(int [][] m1, int [][] m2){
        //base case

        int[][] mI = new int[m1.length][m2.length];
        //get mI
        int a = m1[0][0];
        int b = m1[0][1];
        int c = m1[1][0];
        int d = m1[1][1];
        int e = m2[0][0];
        int f = m2[0][1];
        int g = m2[1][0];
        int h = m2[1][1];

        int i = (a * e) + (b * g);
        int j = (a * f) + (b * h);
        int k = (c * e) + (d * g);
        int l = (c * f) + (d * h);
        mI[0][0] = i;
        mI[0][1] = j;
        mI[1][0] = k;
        mI[1][1] = l;
        return mI;
    }//end matrixMultiple
}//end class