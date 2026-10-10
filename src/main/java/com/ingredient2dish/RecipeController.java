
package com.ingredient2dish;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final PantryRepository pantryRepository;

    public RecipeController(
            RecipeRepository recipeRepository,
            PantryRepository pantryRepository) {
        this.recipeRepository = recipeRepository;
        this.pantryRepository = pantryRepository;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeRepository.deleteById(id);
    }

    @GetMapping("/recommendations")
    public List<Recipe> getRecommendedRecipes() {
        List<PantryItem> pantryItems = pantryRepository.findAll();
        List<Recipe> recipes = recipeRepository.findAll();

        return recipes.stream()
                .filter(recipe -> {
                    String ingredients =
                            recipe.getIngredients().toLowerCase();

                    return pantryItems.stream()
                            .anyMatch(item ->
                                    ingredients.contains(
                                            item.getName().toLowerCase()
                                    )
                            );
                })
                .toList();
    }
}
