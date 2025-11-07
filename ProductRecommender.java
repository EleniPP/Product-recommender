import java.util.*;
// package C:/Users/eleni/ProductRecommender/Product.java;

public class ProductRecommender {
    private List<Product> products = new ArrayList<>();
    private final Map<Integer, Integer> purchaseCounts = new HashMap<>();

    private double categorySimilarity(String c1, String c2) {
        Set<String> words1 = new HashSet<>(Arrays.asList(c1.toLowerCase().split("\\s+")));
        Set<String> words2 = new HashSet<>(Arrays.asList(c2.toLowerCase().split("\\s+")));
        Set<String> common = new HashSet<>(words1);
        common.retainAll(words2);
        double score = (double) common.size() / Math.max(words1.size(), words2.size());
        return score; // 0.0–1.0 range
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    // A user buys a product
    public void recordPurchase(int productId) {
        purchaseCounts.put(productId, purchaseCounts.getOrDefault(productId, 0) + 1);
    }

    public List<Product> recommend(Product baseProduct, int k) {
        List<Product> sameCategory = new ArrayList<>();
        // candidates for popularity
        // List<Product> candidates = new ArrayList<>();

        // for (Product p : products) {
        //     if (p.getId() != baseProduct.getId()) candidates.add(p);
        // }
        // candidates.sort((a, b) -> Double.compare(score(baseProduct, b), score(baseProduct, a)));
        // // return first k 
        // return candidates.size() > k ? candidates.subList(0, k) : candidates;

        // Recommend also similar/related categories
        for (Product p : products) {
            if (!p.equals(baseProduct) && categorySimilarity(p.getCategory(), baseProduct.getCategory()) >= 0.5) {
                sameCategory.add(p);
            }
        }
        // sort by price difference
        sameCategory.sort(Comparator.comparingDouble(p -> Math.abs(p.getPrice() - baseProduct.getPrice())));

        // return top 3
        return sameCategory.size() > 3 ? sameCategory.subList(0, 3) : sameCategory;
    }


    private double levenshteinSim(String a, String b) {
        a = a.toLowerCase(); b = b.toLowerCase();
        int n = a.length(), m = b.length();
        if (n == 0 && m == 0) return 1.0;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        int dist = dp[n][m];
        int maxLen = Math.max(n, m);
        return 1.0 - (double) dist / maxLen;
    }

    // In case we want similar prices products
    private double priceSimilarity(double a, double b) {
        double hi = Math.max(a,b), lo = Math.min(a,b);
        if (hi <= 0) return 0.0;
        return lo / hi;
    }

    // In case we want to give priority to more popular items
    private double popularityNorm(int productId) {
        int c = purchaseCounts.getOrDefault(productId, 0);
        int max = 0;
        for (int v : purchaseCounts.values()) if (v > max) max = v;
        if (max == 0) return 0.0;
        return (double) c / max; // 0..1
    }



}
