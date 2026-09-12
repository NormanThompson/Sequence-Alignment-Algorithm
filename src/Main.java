/*
Norman Thompson
Dr. Cain
CSCD 320
5/5/2026
Sequence alignment hw3
 */
import java.util.*;
import java.io.*;

public class Main {
    /*
    All that this main method does is create a scanner and a buffered write for the input
    and output files respectively, and then reads stuff from the input some variables so we can
    use them to calculate the alignment.
     */
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");
        Scanner scan = null;
        BufferedWriter writeFile = null;

        int match = 0;
        int mismatch = 0;
        int gap = 0;

        try{
            scan = new Scanner(inputFile);
            writeFile = new BufferedWriter(new FileWriter(outputFile));
        }catch(Exception e){
            System.out.println("problem initiating scanner or BufferedWriter");
            System.out.println(e);
        }
        String string1 = scan.nextLine().trim();

        String string2 = scan.nextLine().trim();

        match = Integer.parseInt(scan.nextLine().split(" ")[1]);

        mismatch = Integer.parseInt(scan.nextLine().split(" ")[1]);

        gap = Integer.parseInt(scan.nextLine().split(" ")[1]);

        String[] result = SequenceAlignment.align(string1, string2, match, mismatch, gap);

        scan.close();

        try{
            writeFile.write(result[0]);
            writeFile.newLine();
            writeFile.write(result[1]);
            writeFile.newLine();
            writeFile.write(result[2]);
            writeFile.close();
        }catch(Exception e){
            System.out.println("Problem BufferedWriting");
            System.out.println(e);
        }









    }
}