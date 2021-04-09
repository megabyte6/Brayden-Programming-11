public class Product {
    private String name;
    private int quantity;
    private double cost;

    Product(String name, int quantity, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public void purchase() {
        this.quantity--;
    }

    public String toString() {
        return name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public double getCost() {
        return cost;
    }
}
