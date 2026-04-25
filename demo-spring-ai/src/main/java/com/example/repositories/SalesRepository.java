package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Sale;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {
    
}
