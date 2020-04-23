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
    private static boolean MSG_DEBUG = true;
    private static boolean MSG_DEBUG_MAIN = false;
    private static boolean MSG_DEBUG_KRON = false;


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

    private static double [][] standard_Matrix_I = {{1,0}, 
                                                    {0,1}};

    private static double [][] standard_Matrix_X = {{0,1}, 
                                                    {1,0}};

    private static double [][] standard_Matrix_Z = {{1,0}, 
                                                    {0,-1}};

    private static double [][] standard_Matrix_Y = {{0,-1}, 
                                                    {1,0}};

    private static double [][] standard_Matrix_H = {{0.7071,0.7071}, 
                                                    {0.7071,-0.7071}};

    private static double [][] standard_Matrix_CNOT = {{1, 0, 0, 0}, 
                                                       {0, 1, 0, 0},
                                                       {0, 0, 0, 1},
                                                       {0, 0, 1, 0}};   
    private static double [][] standard_Matrix_NOTC = {{1, 0, 0, 0}, 
                                                       {0, 0, 0, 1},
                                                       {0, 0, 1, 0},
                                                       {0, 1, 0, 0}};    

    private static double [][] standard_Matrix      = {{1, 0, 0, 0}, 
                                                       {0, 1, 0, 0},
                                                       {0, 0, 1, 0},
                                                       {0, 0, 0, 1}};       
    

    //
    // Private Functions
    //
    private static double[][] funcGetSetMatrix (char gate) {
            double [][] result = new double[2][2];
        switch (gate) {
            case '-':
            result = standard_Matrix_I;
            break;
            case 'X':
            result = standard_Matrix_X;
            break;                
            case 'Z':
            result = standard_Matrix_Z;
            break;
            case 'Y':
            result = standard_Matrix_Y;
            break;    
            case 'H': 
            result = standard_Matrix_H;

            break;

            default:
            
        }
        return result;
    }

    private static double[] funcSignleStageCal (char gate1, char gate2, double[] data) {
        double[] result = new double [4];

        double[][] mGate1 = new double [2][2];
        double[][] mGate2 = new double [2][2];
        double[][] C= new double[rowa * rowb][cola * colb]; 

        result = data;

        if (gate1 == CMD_CONBIT || gate2 == CMD_CONBIT) {
            if (gate1 == CMD_CONBIT && gate2 == CMD_TARBIT) {
                //swap 3 and 4
                // double temp = 0.0;
                // temp = result[2];
                // result [2] = result [3];
                // result [3] = temp;
                result = matrixtensor (standard_Matrix_CNOT, result);
                
            } else if (gate2 == CMD_CONBIT && gate1 == CMD_TARBIT) {
                //swap 2 and 4
                // double temp = 0.0;
                // temp = result[1];
                // result [1] = result [3];
                // result [3] = temp;
                result = matrixtensor (standard_Matrix_NOTC, result);
                

            } else if (gate1 == CMD_CONBIT){
               
                double [][] tempMatrix = standard_Matrix;
                double [][] tempMatrix2 = new double[2][2];
                tempMatrix2 = funcGetSetMatrix (gate2);
                tempMatrix [2][2] = tempMatrix2[0][0];
                tempMatrix [2][3] = tempMatrix2[0][1];
                tempMatrix [3][2] = tempMatrix2[1][0];
                tempMatrix [3][3] = tempMatrix2[1][1];
                result = matrixtensor (tempMatrix, result);


            } else if (gate2 == CMD_CONBIT) {

                
                double [][] tempMatrix = standard_Matrix;
                double [][] tempMatrix2 = new double[2][2];
                tempMatrix2 = funcGetSetMatrix (gate1);
                tempMatrix [1][1] = tempMatrix2[0][0];
                tempMatrix [1][3] = tempMatrix2[0][1];
                tempMatrix [3][1] = tempMatrix2[1][0];
                tempMatrix [3][3] = tempMatrix2[1][1];
                result = matrixtensor (tempMatrix, result);
                

            }

        } else {
            //other case without control bit and set bit
            switch (gate1) {
                case '-':
                 mGate1 = standard_Matrix_I;
                break;
                case 'X':
                 mGate1 = standard_Matrix_X;
                break;                
                case 'Z':
                 mGate1 = standard_Matrix_Z;
                break;
                case 'Y':
                 mGate1 = standard_Matrix_Y;
                break;    
                case 'H': 
                 mGate1 = standard_Matrix_H;

                break;

                default:
                
            }
            switch (gate2) {
                case '-':
                mGate2 = standard_Matrix_I;
                break;
                case 'X':
                mGate2 = standard_Matrix_X;
                break;                
                case 'Z':
                mGate2 = standard_Matrix_Z;
                break;
                case 'Y':
                mGate2 = standard_Matrix_Y;
                break;    
                case 'H': 
                mGate2 = standard_Matrix_H;

                break;

                default:
                
            }
            // reach the result of this stage
            C = Kroneckerproduct(mGate1, mGate2);  
            // data = matrixtensor (C, data);
            result = matrixtensor (C, result);

        }

        return result;


    }

    // private static double[]

    private static double[] funcDoCaclulate2Qbit (
        String instr1,
        String instr2,
        int numInstr,
        double[] data
    ){
        double [] result = new double [4];
        result = data;

        for (int count = 0; count < numInstr; count++) {
            result = funcSignleStageCal (instr1.charAt(count), 
                                         instr2.charAt(count),
                                         result);
        }

        
        return result;
    }

    private static void funcProcessIntroductions (List<String> list) {
        int varT;
        int varM;
        String strWire1;
        String strWire2;

        // reach the values of T and M
        varT = Character.getNumericValue(list.get(INDEX_T).charAt(0));
        varM = Character.getNumericValue(list.get(INDEX_M).charAt(0));
        strWire1 = list.get(INDEX_WIRE_1);
        strWire2 = list.get(INDEX_WIRE_2);
        
        if (MSG_DEBUG == true) {
            System.out.println("size : " +list.size());
            System.out.println("T : " +varT);
            System.out.println("M : " +varM);
            System.out.println("strWire1 : " +strWire1);
            System.out.println("strWire2 : " +strWire2);
        }


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
                System.out.print(tempData[count2] + " ");
            }
            System.out.println();
        }


        return;
    }
    
    //
    // M1 matix is the formula matrix 4x4
    // M2 is the input data martix 4x1 
    //
    private static double[] matrixtensor(double[][] m1, double[] m2) {
        double [] matrixResult = new double [4];
        //
        // due to the behavior of assignment 2, we know there will be only 2x2 matrix as the input.
        // therefore, this functionality is built by hardcode calculation
        //

        matrixResult [0] = m2[0]*m1[0][0] + m2[1]*m1[0][1] + m2[2]*m1[0][2] + m2[3]*m1[0][3];
        matrixResult [1] = m2[0]*m1[1][0] + m2[1]*m1[1][1] + m2[2]*m1[1][2] + m2[3]*m1[1][3];
        matrixResult [2] = m2[0]*m1[2][0] + m2[1]*m1[2][1] + m2[2]*m1[2][2] + m2[3]*m1[2][3];
        matrixResult [3] = m2[0]*m1[3][0] + m2[1]*m1[3][1] + m2[2]*m1[3][2] + m2[3]*m1[3][3];

        return matrixResult; 
    }

    // rowa and cola are no of rows and columns 
    // of matrix A 
    // rowb and colb are no of rows and columns 
    // of matrix B 
    private static int cola = 2, rowa = 2, colb = 2, rowb = 2; 
      
    // Function to computes the Kronecker Product 
    // of two matrices 
    private static double[][] Kroneckerproduct(double A[][], double B[][]) 
    { 
      
        // double[][] C= new double[rowa * rowb][cola * colb]; 
        List<Double> listC = new ArrayList<Double>();

      
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
                        // C[i + l + 1][j + k + 1] = A[i][j] * B[k][l]; 
                        listC.add (A[i][j] * B[k][l]);
                        if (MSG_DEBUG && MSG_DEBUG_KRON) {
                            System.out.print((i + l + 1) + " " + (j + k + 1)+" "); 
                            // System.out.print( C[i + l + 1][j + k + 1]+" "); 
                        }
                        
                    } 
                } 

                if (MSG_DEBUG && MSG_DEBUG_KRON) {
                System.out.println(); 
                }
                
            } 
        } 
        return funcTranslateKronMatrix(listC);
    } 

    private static double [][] funcTranslateKronMatrix( List<Double> listC) {
        double[][] result = new double[rowa * rowb][cola * colb]; 

        //
        // reorganise the address of kron produce to normal address of 4x4 matrix
        //


        result [0][0] = listC.get(0);
        result [0][1] = listC.get(1);
        result [0][2] = listC.get(2);
        result [0][3] = listC.get(3);

        result [1][0] = listC.get(4);
        result [1][1] = listC.get(5);
        result [1][2] = listC.get(6);
        result [1][3] = listC.get(7);

        result [2][0] = listC.get(8);
        result [2][1] = listC.get(9);
        result [2][2] = listC.get(10);
        result [2][3] = listC.get(11);

        result [3][0] = listC.get(12);
        result [3][1] = listC.get(13);
        result [3][2] = listC.get(14);
        result [3][3] = listC.get(15);


        return result;
    }

    
    //
    // Public 
    //
    public static void main (String[] strs)  throws FileNotFoundException, IOException{
        List<String> list = new ArrayList<String>();
        File file = new File(strs[0]);
        int varIndex = 0;
        Scanner scanner = new Scanner(file);
        double[][] C= new double[rowa * rowb][cola * colb]; 
        
        System.out.println("quantum");

        // obstrate the data from input file.
        while(scanner.hasNext()){                                             
            list.add(varIndex, scanner.next());
            varIndex++;                
        }
        scanner.close();

        funcProcessIntroductions (list);

        // //print out the list
        // Test Cases
        // System.out.println(list);
        // double A[][] = { { 1, 2 }, 
        //                  { 3, 0 },  
        //                 }; 
          
        // double B[][] = { { 1, 0 }, 
        //                  { 0, 1 } }; 

        // double data [] = {0.6962, 0.1234, 0.6962, 0.1234};

        // C = Kroneckerproduct(standard_Matrix_H, standard_Matrix_I);  
        // data = matrixtensor (C, data);
        // data = funcSignleStageCal ('H','-', data);
        // for (int count =0; count < data.length; count++) {
        //     System.out.println (data[count] + " ");
        // }

        
        //
        // debug
        //
        if (MSG_DEBUG && MSG_DEBUG_MAIN) {
            System.out.println();
            for(int count = 0; count < 4; count++) {
                for (int count1 = 0; count1 < 4; count1++) {
                    // System.out.print(count + " "+ count1 +" ");
                    System.out.print(C[count][count1]+ " ");
                    
                }
                System.out.println();
            }
        }

        
        return;
    }
}