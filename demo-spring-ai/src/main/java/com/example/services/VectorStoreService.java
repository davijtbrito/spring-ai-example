package com.example.services;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VectorStoreService {

    private final VectorStore vectorStore;

    public VectorStoreService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    // Add a document to the vector store
    public void addDocument(String content, Map<String, Object> metadata) {
        Document document = new Document(content, metadata);
        vectorStore.add(List.of(document));
        System.out.println("✅ Document added: " + content.substring(0, 50) + "...");
    }

    // Search similar documents
    public List<Document> search(String query, int topK) {
        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(topK)
                .build();

        List<Document> results = vectorStore.similaritySearch(request);
        System.out.println("🔍 Found " + results.size() + " similar documents for: " + query);
        return results;
    }

    /**
     * Business Intelligence Query using Semantic Search
     */
    public String answerBusinessQuery(String question) {
        System.out.println("🔍 Answering business question: " + question);

        SearchRequest request = SearchRequest.builder()
                .query(question)
                .topK(10)
                .similarityThreshold(0.5)
                .build();

        List<Document> results = vectorStore.similaritySearch(request);

        if (results.isEmpty()) {
            return "Sorry, I couldn't find relevant information.";
        }

        // Fixed: Use .getContent() correctly
        String context = results.stream()
                .map(doc -> ((org.springframework.ai.document.Document) doc).getFormattedContent())           // ← Correct way
                .collect(Collectors.joining("\n\n"));

        String response = """
                Question: %s

                Based on the sales and product data, here is what I found:

                %s
                """.formatted(question, context);

        System.out.println("✅ Query answered successfully with " + results.size() + " documents!");
        return response;
    }   
}
