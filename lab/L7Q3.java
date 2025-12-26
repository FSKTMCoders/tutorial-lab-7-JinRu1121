package lab;

import java.io.*;
import java.util.Scanner;

public class L7Q3 {
    public static void main(String[] args) {
        try {
            Scanner read = new Scanner(new FileInputStream("lab/lecturer.txt"));
            PrintWriter write = new PrintWriter(new FileOutputStream("reverse.txt"));

            while (read.hasNextLine()) {
                String line = read.nextLine();
                // Reverse the string
                StringBuilder reversed = new StringBuilder(line).reverse();
                write.println(reversed.toString());
            }
            read.close();
            write.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }
    }
}
