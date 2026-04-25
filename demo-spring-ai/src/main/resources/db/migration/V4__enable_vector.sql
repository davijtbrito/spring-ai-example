-- V4__Enable_pgvector.sql

-- Enable the pgvector extension (required for vector columns)
CREATE EXTENSION IF NOT EXISTS vector;

-- Optional: Enable extensions that are often useful with vector search
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS hstore;