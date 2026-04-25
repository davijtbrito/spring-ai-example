-- V5__Create_vector_store.sql

-- Main table for Spring AI Vector Store
CREATE TABLE IF NOT EXISTS vector_store (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content TEXT NOT NULL,
    metadata JSONB,
    embedding VECTOR(1536)  -- 1536 is standard for OpenAI embeddings
);

-- Create HNSW index for fast similarity search (very important!)
CREATE INDEX IF NOT EXISTS vector_store_embedding_idx 
ON vector_store USING hnsw (embedding vector_cosine_ops);