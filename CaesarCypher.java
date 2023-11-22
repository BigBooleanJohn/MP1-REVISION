
/*Honesty/introductory statement: this is a program for the mini-project #1: classical encryption.
all work is my own, I was also shown instruction on subtracting the 'a' bias from the 
mini-project instructions, written by P.M. Osera and adapted by Sam Rebelsky*/
//@Author John Miller

import java.io.PrintWriter;

public class CaesarCypher {// class declaration

    /*
     * ErrorHandling is a function that checks the number if parameters needed in a
     * given instance,
     * and check's whether or not the given strings are valid ("code" or "decode")
     * 
     * @Pre: sequence is an array of strings, could be empty, numParametersNeeded is
     * a valid integer,
     * functionName is a valid function name
     * 
     * @Post: returns if sequence and parameters are not satisfactory
     */
    public static void ErrorHandling(String[] sequence, int NumParametersNeeded, String functionName) {
        if (sequence.length < NumParametersNeeded)// handling a lack of sufficient input
        {
            System.err.printf("Error in %s function: you have entered insufficient commands", functionName);
            System.exit(1);
        } else if (sequence[0].compareTo("encode") != 0 && sequence[0].compareTo("decode") != 0)
        {
            System.err.println("your first input must be either 'code' or 'decode'");
            System.exit(1);
        }
    }

    /*
     * CaesarCypher takes a number n, and an array of strings from the commans line,
     * and
     * based on whether you give the command "code" or "decode" it will add or
     * subtract the bias.
     * The program then returns the modified, encoded or decoded sequence
     * 
     * @pre: sequence is an array of strings, n is a valid integer
     * 
     * @post: CaesarCypher returns a string of an encoded or decoded input string
     * that was found at index 1 of the
     * sequence array
     */
    public static String caesarCypher(int n, String[] sequence) {
        ErrorHandling(sequence, 2, "CaesarCypher");
        if (sequence[0].compareTo("encode") == 0)
        {
            char[] inputArr = sequence[1].toCharArray();
            for (int i = 0; i < inputArr.length; i++) {
                int x = (((inputArr[i] - 'a') + n) % 26) + 'a';
                inputArr[i] = (char) x;
            }
            String returnString = new String(inputArr);
            return returnString;
        }
        if (sequence[0].compareTo("decode") == 0)// if the command is decypher
        {
            char[] inputArr = sequence[1].toCharArray(); 
            for (int i = 0; i < inputArr.length; i++) {
                int x = (inputArr[i] - 'a') - n;
                if (x < 0) {
                    x = x * -1; 
                    x = (26 - x) + 'a';
                    inputArr[i] = (char) x;

                } else {
                    x = x + 'a';
                    inputArr[i] = (char) x;
                }
            }
            String returnString = new String(inputArr);
            return returnString;
        }
        return null;
    }

    /* all printing occurs here from the main function */
    public static void main(String[] args) {
        PrintWriter pen = new PrintWriter(System.out, true);
        for (int i = 0; i < 26; i++)// calling caesarCypher on all i vals
        {
            String st = caesarCypher(i, args);
            pen.printf("n = %d: %s\n", i, st);
        }
        pen.flush();// flushing the pen
    }
}
