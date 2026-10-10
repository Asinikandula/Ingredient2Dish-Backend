
package com.ingredient2dish;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pantry")
@CrossOrigin(origins = "http://localhost:5173")
public class PantryController {

    private final PantryRepository pantryRepository;

    public PantryController(PantryRepository pantryRepository) {
        this.pantryRepository = pantryRepository;
    }

    @GetMapping
    public List<PantryItem> getAllItems() {
        return pantryRepository.findAll();
    }

    @PostMapping
    public PantryItem addItem(@RequestBody PantryItem item) {
        return pantryRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        pantryRepository.deleteById(id);
    }
}
