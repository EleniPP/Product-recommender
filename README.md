# Product Recommender (Console App)

A small Java console application that lets you:

- Add products with a name, category, and price  
- List all available products  
- Simulate purchases  
- Get simple product recommendations based on a chosen product  

It’s a minimal example of how you might structure a recommendation feature with basic similarity logic, using plain Java and standard library collections.

---

## 🧠 What This Project Demonstrates

- Basic **object-oriented design** in Java (`Product`, `ProductRecommender`, `Main`)
- Use of **collections** (`List`, `Map`, `Set`) and sorting with **comparators**
- Simple **recommendation logic**:
  - Category similarity (bag-of-words)
  - Price similarity
  - Popularity scoring (based on recorded purchases)
- A text-based **menu UI** using `Scanner` for user input

This is the kind of small, focused program that can be extended toward a more realistic backend service later (e.g., exposing the recommender via HTTP instead of a console).

---

## 🏗 Project Structure

```text
.
├── Main.java
├── Product.java
└── ProductRecommender.java
