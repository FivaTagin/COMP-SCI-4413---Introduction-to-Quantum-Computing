import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.util.Arrays;


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

        

        //print out the list
        System.out.println(list);
        
        
        scanner.close();
        return;
    }
}