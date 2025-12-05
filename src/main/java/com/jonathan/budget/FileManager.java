package com.jonathan.budget;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FileManager {

    //Save list of expenses to CSV file
    public void saveToCSV(List<Expense> expenses, String filename){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){

            //Write header
            writer.write("date,name,category,amount");
            writer.newLine();

            //Write each expense
            for (Expense e: expenses) {
                String line = e.getDate().format(formatter) + "," +
                              e.getName() + "," +
                              e.getCategory() + "," +
                              String.format("%.2f", e.getAmount());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Expenses save to " + filename);
        } catch (IOException ex) {
            System.out.println("Error saving CSV: " + ex.getMessage());
        }

    }

    //Load expenses from CSV file
    public List<Expense> loadFromCSV(String filename) {

        List<Expense> loadedExpenses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            // Skip header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Expense e = parseExpense(line);
                if (e != null) {
                    loadedExpenses.add(e);
                }
            }
            System.out.println("Expenses loaded from " + filename);
        } catch (IOException ex) {
            System.out.println("Error loading CSV: " + ex.getMessage());
        }
        return loadedExpenses;
    }

    // Convert one expense to CSV line
    private String formatExpense(Expense e) {
        // TODO: return a string like: date,name,category,amount
        return null;
    }

    // Parse CSV line into Expense object
    private Expense parseExpense(String line) {
        
         try {
        String[] parts = line.split(",");
        
        String dateStr = parts[0];
        String name = parts[1];
        String category = parts[2];
        double amount = Double.parseDouble(parts[3]);

        LocalDate date = LocalDate.parse(dateStr);

        return new Expense(name, amount, category, date);

    } catch (Exception e) {
        System.out.println("Skipping invalid line: " + line);
        return null;
    }
    
}
}
