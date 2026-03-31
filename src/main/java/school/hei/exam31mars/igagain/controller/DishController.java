package school.hei.exam31mars.igagain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.exam31mars.igagain.entity.Dish;
import school.hei.exam31mars.igagain.entity.DishIngredient;
import school.hei.exam31mars.igagain.exception.BadRequestException;
import school.hei.exam31mars.igagain.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getDishes() {
        return ResponseEntity.ok(dishService.findAllDishes());
    }

    @PutMapping("/{id}/ingredients")
    public ResponseEntity<Dish> updateDishIngredients(
            @PathVariable Integer id,
            @RequestBody(required = false) List<DishIngredient> ingredients) {
        if (ingredients == null) {
            throw new BadRequestException("Request body containing list of ingredients is mandatory");
        }
        return ResponseEntity.ok(dishService.updateDishIngredients(id, ingredients));
    }
}
