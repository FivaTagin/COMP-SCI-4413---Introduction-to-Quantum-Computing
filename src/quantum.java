import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.util.Arrays;


import java.util.*;

class quantum {

    public static void main (String[] strs)  throws FileNotFoundException, IOException{
        List<Integer> list = new ArrayList<Integer>();
        File file = new File(strs[0]);
        BufferedReader reader = null;
        Scanner scanner = new Scanner(file);
        
        System.out.println("quantum");

        while(scanner.hasNext()){                                             
            System.out.println(scanner.next());                            
        }

        
        // List<Integer> ints = Files.lines(Paths.get(file))
        // .map(Integer::parseInt)
        // .collect(Collectors.toList());

        //print out the list
        System.out.println(list);
        
        
        scanner.close();
        return;
    }
}