import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductRecommender recommender = new ProductRecommender();
        int nextId = 1;
        while (true) {
            System.out.println("\n1) Add product");
            System.out.println("2) List products");
            System.out.println("3) Simulate purchase");
            System.out.println("0) Exit");
            System.out.print("> ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" :
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Price: ");
                    double price = Double.parseDouble(sc.nextLine());
                    recommender.addProduct(new Product(nextId++, name, cat, price));
                    System.out.println("✅ Product added.");
                    break;
                case "2":
                    if (recommender.getProducts().isEmpty()) {
                        System.out.println("No products yet.");
                    } else {
                        for (Product p : recommender.getProducts()) {
                            System.out.println(p);
                        }
                    }
                    break;
                case "3":
                    if (recommender.getProducts().isEmpty()) {
                        System.out.println("No products available.");
                        break;
                    }
                    System.out.println("Enter product ID to simulate purchase:");
                    for (Product p : recommender.getProducts()) {
                        System.out.println(p);
                    }
                    System.out.print("> ");
                    int id = Integer.parseInt(sc.nextLine());
                    Product chosen = null;
                    for (Product p : recommender.getProducts()) {
                        if (p.getId() == id) { chosen = p; break; }
                    }
                    if (chosen == null) {
                        System.out.println("Invalid ID");
                        break;
                    }

                    recommender.recordPurchase(chosen.getId());
                    System.out.println("\n💡 Recommendations based on " + chosen.getName() + ":");
                    List<Product> recs = recommender.recommend(chosen,3);
                    if (recs.isEmpty()) System.out.println("(No similar products yet)");
                    else recs.forEach(p -> System.out.println("- " + p));
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Unknown option.");
                    break;
            }
        }
    }
}
