package sample;

public class Product {
    public String name;
    private int quantity;
    private double cost;

    Product(String name, int quantity, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public void addQuantity(int amt) {
        this.quantity += amt;
    }

    public void purchase() {
        quantity--;
    }

    public String toString() {
        return name;
    }

    // Getters
    public int getQuantity() {return this.quantity;}

    public double getCost() {return this.cost;}
}
