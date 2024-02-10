package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.entities.CategoryEntity;
import org.example.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
@Api(description = "Контролер категорій") // Додаємо тег для Swagger
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    @ApiOperation(value = "Get all categories", notes = "Returns a list of all categories")
    public ResponseEntity<List<CategoryEntity>> index() {
        List<CategoryEntity> list = categoryRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
