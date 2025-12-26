package lab;

import java.util.Scanner;
import java.net.URL;
import java.io.*;
import java.net.URLConnection;

public class L7Q2 {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.fsktm.um.edu.my");
            URLConnection cnn = u.openConnection();
            InputStream stream = cnn.getInputStream();
            Scanner in = new Scanner(stream);
            
            PrintWriter out = new PrintWriter(new FileOutputStream("index.htm"));
            
            while (in.hasNextLine()) {
                out.println(in.nextLine());
            }
            
            in.close();
            out.close();
            System.out.println("Web page content successfully saved to index.htm");
            
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }
    }
}
