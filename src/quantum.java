/*
 * Copyright (c) 2020, Chung-Chien Josh An. All Rights Reserved.
 *
 * This file is part of Assignment 2.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.util.Arrays;

import java.util.*;
import java.math.*;

class quantum {
    
    //
    // Private 
    //

    //
    // Private global variables
    //
    private static int INDEX_T = 0; // the index of T colum
    private static int INDEX_M = 1; // the index of nput quantum states to process.
    private static int INDEX_WIRE_1 = 2; // the index of Wire 1
    private static int INDEX_WIRE_2 = 3; // the index of Wire 2
    private static int INDEX_DATA_0 = 4; // data start.
    private static int LENGTH_MATIX = 4; // 2 qubit length 
    
    private static char CMD_IDENT = '-'; // - Identity (single qubit).
    private static char CMD_NOT = 'X';   // X Not (single qubit).
    private static char CMD_PSHIFT = 'Z';// Z Phase shift (single qubit).
    private static char CMD_NPSHIFT ='Y';// Y Negation and phase shift (single qubit).
    private static char CMD_HADMARD ='H';// H Hadamard (single qubit).
    private static char CMD_CONBIT ='.'; // . Control bit.
    private static char CMD_TARBIT = '+';// + Target bit.

    

    private static double [][] standard_Matrix_X = {{0,1}, 
                                                    {1,0}};

    private static double [][] standard_Matrix_Z = {{1,0}, 
                                                    {0,-1}};

    private static double [][] standard_Matrix_Y = {{0,-1}, 
                                                    {1,0}};

    private static double [][] standard_Matrix_H = {{0.7071,0.7071}, 
                                                    {0.7071,-0.7071}};
    
    
    private static double [][] standard_Matrix_0 = {{0},{1}};
    private static double [][] standard_Matrix_1 = {{1},{0}};
    
    //
    // Private Functions
    //
    // private static double[] funcSignleStageCal () {


    // }

    // private static double[]

    private static double[] funcDoCaclulate2Qbit (
        String instr1,
        String instr2,
        int numInstr,
        double[] data
    ){
        double [] result = new double [4];

        // for (int count = 0; count < numInstr; count++) {

        //     }

        
        return result;
    }

    private static void funcProcessIntroductions (List<String> list) {
        int varT;
        int varM;
        int varNumofCalucate;
        String strWire1;
        String strWire2;

        // reach the values of T and M
        varT = Character.getNumericValue(list.get(INDEX_T).charAt(0));
        varM = Character.getNumericValue(list.get(INDEX_M).charAt(0));
        strWire1 = list.get(INDEX_WIRE_1);
        strWire2 = list.get(INDEX_WIRE_2);
        varNumofCalucate = strWire1.length();
        
        
        System.out.println("size : " +list.size());
        System.out.println("T : " +varT);
        System.out.println("M : " +varM);
        System.out.println("strWire1 : " +strWire1);
        System.out.println("strWire2 : " +strWire2);

        double [][] inputData = new double [varM][LENGTH_MATIX];
        double [] tempData = new double [LENGTH_MATIX];
        double [][] outputData = new double [varM][LENGTH_MATIX];
        
        // restore data in to martix
        for (int count = 0; count < varM; count++) {
            for (int count2 = 0; count2 < LENGTH_MATIX; count2++) {
                inputData[count][count2] = Double.valueOf(list.get(INDEX_DATA_0 * (count + 1) +count2));
                tempData [count2] = Double.valueOf(list.get(INDEX_DATA_0 * (count + 1) +count2)); 
            }

            tempData = funcDoCaclulate2Qbit (
                strWire1,
                strWire2,
                varT,
                tempData
            );
            
            // restore data back to the final data.
            for (int count2 = 0; count2 < LENGTH_MATIX; count2++) {
                outputData[count][count2] = te mpData [count2];
            }
        }


        return;
    }
    
    //
    // M1 matix is the formula matrix 2x2
    // M2 is the input data martix 2x1 
    //
    private double[][] matrixtensor(double[][] m1, double[][] m2) {
        double [][] matrixResult = new double [1][2];
        //
        // due to the behavior of assignment 2, we know there will be only 2x2 matrix as the input.
        // therefore, this functionality is built by hardcode calculation
        //

        matrixResult [0][0] = m1[0][0]*m2[0][0] + m1[0][1]*m2[0][1];
        matrixResult [0][1] = m1[1][0]*m2[0][0] + m1[1][1]*m2[0][1];

        return matrixResult; 
    }

        // rowa and cola are no of rows and columns 
    // of matrix A 
    // rowb and colb are no of rows and columns 
    // of matrix B 
    private static int cola = 2, rowa = 3, colb = 3, rowb = 2; 
      
    // Function to computes the Kronecker Product 
    // of two matrices 
    private static void Kroneckerproduct(int A[][], int B[][]) 
    { 
      
        int[][] C= new int[rowa * rowb][cola * colb]; 
      
        // i loops till rowa 
        for (int i = 0; i < rowa; i++)  
        { 
      
            // k loops till rowb 
            for (int k = 0; k < rowb; k++) 
            { 
      
                // j loops till cola 
                for (int j = 0; j < cola; j++)  
                { 
      
                    // l loops till colb 
                    for (int l = 0; l < colb; l++) 
                    { 
      
                        // Each element of matrix A is 
                        // multiplied by whole Matrix B 
                        // resp and stored as Matrix C 
                        C[i + l + 1][j + k + 1] = A[i][j] * B[k][l]; 
                        System.out.print( C[i + l + 1][j + k + 1]+" "); 
                    } 
                } 
                System.out.println(); 
            } 
        } 
    } 

    //
    // Public 
    //
    public static void main (String[] strs)  throws FileNotFoundException, IOException{
        List<String> list = new ArrayList<String>();
        File file = new File(strs[0]);
        int varIndex = 0;
        Scanner scanner = new Scanner(file);
        
        System.out.println("quantum");

        // obstrate the data from input file.
        while(scanner.hasNext()){                                             
            list.add(varIndex, scanner.next());
            varIndex++;                
        }
        scanner.close();

        funcProcessIntroductions (list);

        // //print out the list
        // System.out.println(list);
        
        
        
        return;
    }
}