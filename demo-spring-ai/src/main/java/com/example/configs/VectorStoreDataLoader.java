package com.example.configs;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entities.Product;
import com.example.entities.Sale;
import com.example.repositories.ProductRepository;
import com.example.repositories.SalesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class VectorStoreDataLoader implements CommandLineRunner {

    private final VectorStore vectorStore;
    private final ProductRepository productRepository;
    private final SalesRepository saleRepository;

    public VectorStoreDataLoader(VectorStore vectorStore,
                                 ProductRepository productRepository,
                                 SalesRepository saleRepository) {
        this.vectorStore = vectorStore;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Starting to load Products & Sales into Vector Store...");

        List<Document> documents = new ArrayList<>();

        // Load Products
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            String content = String.format("Product: %s | Price: $%.2f | Stock: %d | Description: %s",
                    p.getName(), p.getPrice(), p.getStockQuantity(), p.getDescription());

            Document doc = new Document(content, Map.of(
                    "type", "product",
                    "id", p.getId(),
                    "name", p.getName(),
                    "price", p.getPrice()
            ));
            documents.add(doc);
        }

        // Load Sales
        List<Sale> sales = saleRepository.findAll();
        for (Sale s : sales) {
            String content = String.format("Sale: %s bought %d units of product ID %d on %s. Total: $%.2f",
                    s.getCustomerName(), s.getQuantity(), 
                    s.getProduct().getId(), s.getSaleDate(), s.getTotalAmount());

            Document doc = new Document(content, Map.of(
                    "type", "sale",
                    "id", s.getId(),
                    "customer", s.getCustomerName(),
                    "product_id", s.getProduct().getId()
            ));
            documents.add(doc);
        }

        if (!documents.isEmpty()) {
            vectorStore.add(documents);
            System.out.println("✅ Successfully loaded " + documents.size() + " documents into Vector Store!");
        } else {
            System.out.println("⚠️ No data found to load into vector store.");
        }
    } 

}
