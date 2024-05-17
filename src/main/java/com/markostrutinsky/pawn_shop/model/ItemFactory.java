package com.markostrutinsky.pawn_shop.model;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ItemFactory {
    PawnItem createPawnItem(Map<String, String> formData);
}
