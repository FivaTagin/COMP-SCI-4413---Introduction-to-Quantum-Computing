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

import org.ujmp.core.*;
// import org.ujmp.core.Matrix;

// import Jama.Matrix;


import java.util.*;

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
    
    private static char CMD_IDENT = '-'; // - Identity (single qubit).
    private static char CMD_NOT = 'X';   // X Not (single qubit).
    private static char CMD_PSHIFT = 'Z';// Z Phase shift (single qubit).
    private static char CMD_NPSHIFT ='Y';// Y Negation and phase shift (single qubit).
    private static char CMD_HADMARD ='H';// H Hadamard (single qubit).
    private static char CMD_CONBIT ='.'; // . Control bit.
    private static char CMD_TARBIT = '+';// + Target bit.
    
    //
    // Private Functions
    //
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
        
        
        System.out.println(list.size());
        return;
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