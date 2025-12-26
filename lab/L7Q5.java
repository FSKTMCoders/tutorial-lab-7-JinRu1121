package lab;

import java.io.*;
import java.util.*;

class Person {
    String name;
    int age;
    char gender;

    Person(String n, int a, char g) { name = n; age = a; gender = g; }
}

public class L7Q5 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("lab/person.dat"))) {
            int total = in.readInt();
            for (int i = 0; i < total; i++) {
                list.add(new Person(in.readUTF(), in.readInt(), in.readChar()));
            }
            
            // Sort by name
            list.sort(Comparator.comparing(p -> p.name));
            
            System.out.printf("%-20s %-5s %-10s\n", "Name", "Age", "Gender");
            for (Person p : list) {
                System.out.printf("%-20s %-5d %-10s\n", p.name, p.age, p.gender == 'M' ? "Male" : "Female");
            }
        } catch (IOException e) {
            System.out.println("Error reading binary file.");
        }
    }
}
