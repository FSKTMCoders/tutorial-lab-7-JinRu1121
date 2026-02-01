# Tutorial 
## WIX1002 Fundamentals of Programming
## Tutorial 7 File Input and Output

1. Write statements for each of the following
   a. Store ten random integers within 0 to 1000 to a text file name integer.txt.
   ```java
   import java.io.PrintWriter;
   import java.io.FileOutputStream;
   import java.io.FileNotFoundException;
   import java.util.Random;
   
   public class Question1a {
    public static void main(String[] args) {
        Random g = new Random();
        try {
            // Create a PrintWriter linked to integer.txt
            PrintWriter out = new PrintWriter(new FileOutputStream("integer.txt"));
            
            for (int i = 0; i < 10; i++) {
                // Generate a random number from 0 to 1000 
                int num = g.nextInt(1001);
                out.println(num);
            }
            
            out.close(); // Always close your files!
            System.out.println("Integers successfully stored in integer.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not create the file.");
        }
    }
    }
    ```
    b. Read from the text file generated in a. Display all the integer and the largest integer.
    ```java
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.util.Scanner;
    
    public class Question1b {
        public static void main(String[] args) {
            try {
            Scanner read = new Scanner(new FileInputStream("integer.txt"));
            int largest = -1; // Start with a low number to compare
            
            System.out.print("Integers in file: ");
            while (read.hasNextInt()) {
                int current = read.nextInt();
                System.out.print(current + " ");
                
                // Compare to find the largest 
                if (current > largest) {
                    largest = current;
                }
            }
            
            read.close();
            System.out.println("\nThe largest integer is: " + largest);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
    }
    ```
    c. Store ten random integers within 0 to 1000 to a binary file name integer.dat.
    ```java
    import java.io.FileOutputStream;
    import java.io.ObjectOutputStream;
    import java.io.IOException;
    import java.util.Random;
    
    public class Question1c {
        public static void main(String[] args) {
            Random g = new Random();
            try {
            // Connect to integer.dat 
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("integer.dat"));
            
            for (int i = 0; i < 10; i++) {
                out.writeInt(g.nextInt(1001));
            }
            
            out.close();
            System.out.println("Integers successfully stored in integer.dat");
        } catch (IOException e) {
            System.out.println("Error writing to binary file.");
        }
    }
    }
    ```
    d. Read from the binary file generated in a c. Display the all the integer and the average.
    ```java
    import java.io.FileInputStream;
    import java.io.ObjectInputStream;
    import java.io.IOException;
    import java.io.EOFException;
    
    public class Question1d {
        public static void main(String[] args) {
            try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("integer.dat"));
            int sum = 0;
            int count = 0;
            
            System.out.print("Integers in binary file: ");
            try {
                // Loop 10 times to read the integers 
                for (int i = 0; i < 10; i++) {
                    int num = in.readInt();
                    System.out.print(num + " ");
                    sum += num;
                    count++;
                }
            } catch (EOFException e) {
                // End of file reached
            }
            
            in.close();
            double average = (double) sum / count;
            System.out.println("\nThe average is: " + average);
        } catch (IOException e) {
            System.out.println("Error reading binary file.");
        }
    }
    }
    ```

2. Correct the error for the following statements.
   a. PrintWriter out = new PrintWriter(new FileOutputStream ("d:\data\matrix.txt"));
   ```java
   PrintWriter out = new PrintWriter(
        new FileOutputStream("d:\\data\\matrix.txt"));
   ```
   b.
   try {
    PrintWriter out = new PrintWriter(new FileOutputStream("data.txt"));
    out.close();
    } catch (FileNotFoundException e) {
        System.out.println("Problem with file output");
        }
    ```java
    try {
        PrintWriter out = new PrintWriter(new
        FileOutputStream("data.txt"));
        out.println("Hello");
        out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem with file output");
            }
    ```
    c. int num;
    Scanner a = new Scanner(new FileInputStream("data.dat"));
    num = a.readInt();
    a.close();
    ```java
    int num;
    try {
        Scanner a = new Scanner(new FileInputStream("data.dat"));
        num = a.nextInt();
        a.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            }
    ```
    d.ObjectOutputStream o = new ObjectOutputStream (new FileOutputStream("data.dat"));
    o.print('A');
    o.close();
    ```java
    try {
        ObjectOutputStream o =
            new ObjectOutputStream(new FileOutputStream("data.dat"));
            o.writeChar('A');
            o.close();
            } catch (IOException e) {
    System.out.println("Output error");
    }
    ```
3. Write a program that convert a sentence into binary number (ASCII code 8 bit) and store it in a text file named data.txt. Then, read from the text file and display the sentence.
   ```java
   import java.io.*;
   import java.util.Scanner;
   
   public class SentenceToBinary {
    public static void main(String[] args) {
        String sentence = "Hello Java"; // The sentence to convert 
        String filename = "data.txt";     // The file name required 

        // --- PART 1: CONVERT AND WRITE TO FILE ---
        try {
            // Using PrintWriter to write text to data.txt [cite: 8, 15]
            PrintWriter writer = new PrintWriter(new FileOutputStream(filename));
            
            System.out.println("Converting sentence to binary...");
            
            for (int i = 0; i < sentence.length(); i++) {
                char letter = sentence.charAt(i);
                
                // 1. Convert character to ASCII binary string
                String binary = Integer.toBinaryString(letter);
                
                // 2. Ensure it is 8-bit (pad with leading zeros if needed)
                // This logic adds zeros to the front until the length is 8
                while (binary.length() < 8) {
                    binary = "0" + binary;
                }
                
                // 3. Write the 8-bit binary and a space to the file
                writer.print(binary + " ");
            }
            
            writer.close(); // Always close the file [cite: 15, 23]
            System.out.println("Binary data successfully stored in " + filename);
            
        } catch (IOException e) {
            System.out.println("An error occurred during writing.");
        }

        // --- PART 2: READ FROM FILE AND DISPLAY ---
        try {
            // Using Scanner to read the text file [cite: 21]
            Scanner reader = new Scanner(new FileInputStream(filename));
            
            System.out.print("The original sentence was: ");
            
            // Read each binary "word" separated by spaces
            while (reader.hasNext()) {
                String binaryString = reader.next();
                
                // 1. Convert the binary string back to a decimal number (base 2)
                int decimal = Integer.parseInt(binaryString, 2);
                
                // 2. Cast the number to a char and print it 
                System.out.print((char) decimal);
            }
            
            reader.close(); // Close the scanner [cite: 23]
            System.out.println(); // New line for cleanliness
            
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found.");
        }
    }
    }






