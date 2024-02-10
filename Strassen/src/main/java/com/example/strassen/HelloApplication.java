package com.example.strassen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication <T> extends Application {
    @Override
    public void start(Stage stage) throws IOException {

    }
    public static int[][] strassen(int[][] m1, int [][] m2){
        if(twoByTwo(m1)){
            return
        }
    }


    /**
     * check if a matrix is a 2 by 2 matrix
     * @param m
     * @return
     */
    public boolean twoBytwo(int [][] m){
        boolean flag = false;
        if(m.length == 4){

        }
        return flag;
    }//end twoBytwo

    public int [][] reduce(int [][] m, int quad){

        int len = m.length;
        int newLen =len/2;

        //split m in half
        int [][] m1 = new int [newLen][newLen];
        int [][] m2 = new int [newLen][newLen/2];
        int [][] m3 = new int [newLen][newLen];
        int [][] m4 = new int [newLen][newLen];
        //do the first row
        for(int row = 0; row < len; row ++){
            for(int col = 0; col < len; col++){
                if(quad == 1 && (row < newLen && col < newLen)){
                    m1[row][col] = m[row][col];
                }//
                else if(quad == 2 && col > newLen){
                    m2[row][col - newLen] = m[row][col];
                }else if( (quad == 3) && (row > newLen && col < newLen)){
                    m3[row - newLen][col] = m[row][col];
                }else{
                    //assume that row and col are greater than newLen
                    m4[row - newLen][col - newLen] = m[row][col];
                }//end if
            }//end col
        }//end row
        if(quad == 1){
            return m1;
        }else if(quad == 2){
            return m2;
        }else if(quad == 3){
            return m3;
        }else{
            return m4;
        }
    }

    /**
     * Requires 2x2 matrix which it would then compute the result
     * @param m1 -matrix a
     * @param m2 -matrix b
     * @return - matrix I
     */
    public int[][] matrixMultiple(int [][] m1, int [][] m2){
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
    }



    public static void main(String[] args) {

    }
}