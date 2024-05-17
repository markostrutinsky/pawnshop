package com.markostrutinsky.pawn_shop.controller;

import com.markostrutinsky.pawn_shop.model.PawnItem;
import com.markostrutinsky.pawn_shop.model.PawnItemCategory;
import com.markostrutinsky.pawn_shop.response.PawnItemResponse;
import com.markostrutinsky.pawn_shop.service.PawnItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pawnshop")
public class PawnItemController {
    PawnItemService pawnItemService;

    @GetMapping("/items/by-category/{category}")
    public ResponseEntity<List<? extends PawnItem>> getAllByCategory(@PathVariable("category") PawnItemCategory category) {
        return ResponseEntity.ok(pawnItemService.getAllItemsByCategory(category));
    }

    @GetMapping("/all-item")
    public ResponseEntity<List<? extends PawnItem>> getAll() {
        return ResponseEntity.ok(pawnItemService.findAllItems());
    }

    @PostMapping("/add-item")
    public ResponseEntity<PawnItemResponse> addItem(@RequestBody Map<String, String> formData) throws SQLException, IOException {
        PawnItem pawnItem = pawnItemService.addNewItem(formData);

        PawnItemResponse pawnItemResponse = new PawnItemResponse(pawnItem);
        return ResponseEntity.ok(pawnItemResponse);
    }

}
