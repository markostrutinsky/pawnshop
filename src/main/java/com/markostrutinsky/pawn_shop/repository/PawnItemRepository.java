package com.markostrutinsky.pawn_shop.repository;

import com.markostrutinsky.pawn_shop.model.PawnItem;
import com.markostrutinsky.pawn_shop.model.PawnItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PawnItemRepository extends JpaRepository<PawnItem, Long> {
    List<? extends PawnItem> findByCategory(PawnItemCategory category);
}
