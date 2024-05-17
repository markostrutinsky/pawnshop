package PawnShop.PawnShop.model;

import PawnShop.PawnShop.repository.PawnItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public interface ItemFactory {
    PawnItem createPawnItem(Map<String, String> formData);
}
