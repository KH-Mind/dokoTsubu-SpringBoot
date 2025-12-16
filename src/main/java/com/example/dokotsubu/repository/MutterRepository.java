package com.example.dokotsubu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dokotsubu.model.Mutter;

@Repository
public interface MutterRepository extends JpaRepository<Mutter, Integer> {
    // Standard methods (findAll, save, etc.) are automatically provided.
}
