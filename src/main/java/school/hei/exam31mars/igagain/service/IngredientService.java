package school.hei.exam31mars.igagain.service;

import org.springframework.stereotype.Service;
import school.hei.exam31mars.igagain.entity.Ingredient;
import school.hei.exam31mars.igagain.entity.StockValue;
import school.hei.exam31mars.igagain.entity.Unit;
import school.hei.exam31mars.igagain.exception.ResourceNotFoundException;
import school.hei.exam31mars.igagain.repository.IngredientRepository;

import java.time.Instant;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAllIngredients();
    }

    public Ingredient findIngredientById(Integer id) {
        try {
            return ingredientRepository.findIngredientById(id);
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("Ingredient not found")) {
                throw new ResourceNotFoundException("Ingredient.id=" + id + " is not found");
            }
            throw e;
        }
    }

    public StockValue getIngredientStock(Integer id, Instant temporal, Unit unit) {
        Ingredient ingredient = findIngredientById(id);
        StockValue stockValue = ingredient.getStockValueAt(temporal);
        // Note : L'énoncé demande de vérifier si 'at' et 'unit' sont présents, ce qui est géré par les annotations du Controller.
        // On pourrait aussi ajouter une logique ici si l'unité differe, mais aucune règle de conversion n'est spécifiée.
        return stockValue;
    }
}
