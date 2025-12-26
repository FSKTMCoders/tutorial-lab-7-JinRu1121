package lab;

import java.io.*;
import java.util.*;

public class L7Q6 {
    public static void main(String[] args) {
        Map<String, String> productName = new HashMap<>();
        Map<String, Double> productPrice = new HashMap<>();

        try {
            // Read Products
            Scanner sProd = new Scanner(new FileInputStream("product.txt"));
            while (sProd.hasNextLine()) {
                String[] data = sProd.nextLine().split(",");
                productName.put(data[0], data[1]);
                productPrice.put(data[0], Double.parseDouble(data[2]));
            }
            sProd.close();

            // Read Orders and Merge
            Scanner sOrder = new Scanner(new FileInputStream("order.txt"));
            System.out.printf("%-10s %-20s %-10s %-15s %-10s\n", "ProductID", "ProductName", "Quantity", "PricePerUnit", "Total");
            
            while (sOrder.hasNextLine()) {
                String[] data = sOrder.nextLine().split(",");
                String id = data[1];
                int qty = Integer.parseInt(data[2]);
                double price = productPrice.get(id);
                double total = qty * price;
                
                System.out.printf("%-10s %-20s %-10d %-15.2f %-10.2f\n", id, productName.get(id), qty, price, total);
            }
            sOrder.close();
        } catch (IOException e) {
            System.out.println("Error processing files.");
        }
    }
}
