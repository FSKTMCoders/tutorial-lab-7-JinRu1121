package lab;

import java.io.*;
import java.util.Scanner;

public class L7Q1 {
    public static void main(String[] args) {
        String[][] courses = {
            {"WXES1116", "Programming I"},
            {"WXES1115", "Data Structure"},
            {"WXES1110", "Operating System"},
            {"WXES1112", "Computing Mathematics I"}
        };

        //Writing to binary file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("coursename.dat"))) {
            for (String[] course : courses) {
                out.writeUTF(course[0]); // Write Course Code
                out.writeUTF(course[1]); // Write Course Name
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        //Read and Search
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter course code: ");
        String searchCode = sc.nextLine();
        boolean found = false;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("coursename.dat"))) {
            while (true) {
                String code = in.readUTF();
                String name = in.readUTF();
                if (code.equalsIgnoreCase(searchCode)) {
                    System.out.println("Course Name: " + name);
                    found = true;
                    break;
                }
            }
        } catch (EOFException e) {
            if (!found) System.out.println("Course code not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

