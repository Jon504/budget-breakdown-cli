package com.jonathan.budget;
import java.time.LocalDate;

public class Expense {

    private String name;
    private double amount;
    private String category;
    private LocalDate date;

    
    public Expense(String name, double amount, String category,LocalDate date){
        
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;

    }
    //Getters for necessary information
    public String getName(){
        return name;
    }

    public double getAmount(){
        return amount;
    }

    public String getCategory(){
        return category;
    }

    public LocalDate getDate(){
        return date;
    }

    @Override
    public String toString(){
        return date + " | " + name + " | " + category  + " | $" + String.format("%.2f", amount);
    }




}
