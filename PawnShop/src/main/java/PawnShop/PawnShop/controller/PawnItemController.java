package PawnShop.PawnShop.controller;

import PawnShop.PawnShop.model.PawnItem;
import PawnShop.PawnShop.model.PawnItemCategory;
import PawnShop.PawnShop.response.PawnItemResponse;
import PawnShop.PawnShop.service.PawnItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
