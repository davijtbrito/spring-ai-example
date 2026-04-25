-- V6__Fix_vector_dimension.sql

-- Drop the old table and index
DROP INDEX IF EXISTS vector_store_embedding_idx;
DROP TABLE IF EXISTS vector_store;

-- Create the table with correct dimension (768 for nomic-embed-text)
CREATE TABLE vector_store (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content TEXT NOT NULL,
    metadata JSONB,
    embedding VECTOR(768)
);

-- Create optimized index
CREATE INDEX IF NOT EXISTS vector_store_embedding_idx 
ON vector_store USING hnsw (embedding vector_cosine_ops);

COMMENT ON TABLE vector_store IS 'Vector store for Spring AI - using 768 dimensions';