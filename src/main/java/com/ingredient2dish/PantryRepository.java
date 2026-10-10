package com.ingredient2dish;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PantryRepository extends JpaRepository<PantryItem, Long> {
}
