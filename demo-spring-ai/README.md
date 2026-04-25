# Demo Spring AI Backend

A Spring Boot application with PostgreSQL + pgvector + Ollama for semantic search and business intelligence queries.

## Features

- PostgreSQL with pgvector for vector embeddings
- Automatic loading of Products & Sales data into Vector Store
- Semantic search using natural language queries
- Local AI using Ollama (no API key needed)

---

## Prerequisites

### 1. Java
- **Java 17** or higher

### 2. PostgreSQL with pgvector
- PostgreSQL 16+ with **pgvector** extension installed

### 3. Ollama (Local AI)
- Download and install from: [https://ollama.com/download](https://ollama.com/download)
- Required models:
  ```bash
  ollama pull nomic-embed-text
  ollama pull llama3.2