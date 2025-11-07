public class Product {
    private int id;
    private double price;
    private String name;
    private String category;

    public Product(int id, String name, String category, double price){
        this.id = id;
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    
    @Override
    public String toString() {
        return id + " - " + name + " (" + category + ") €" + price;
    }

}
