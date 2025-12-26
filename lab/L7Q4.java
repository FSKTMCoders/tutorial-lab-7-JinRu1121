package lab;

import java.io.*;
import java.util.Scanner;

public class L7Q4 {
    public static void main(String[] args) {
        int chars = 0, words = 0, lines = 0;
        
        try (Scanner sc = new Scanner(new FileInputStream("input.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lines++;
                chars += line.length();
                
                if (!line.isEmpty()) {
                    String[] wordsArr = line.split(" ");
                    words += wordsArr.length;
                }
            }
            System.out.println("Lines: " + lines);
            System.out.println("Words: " + words);
            System.out.println("Characters: " + chars);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
