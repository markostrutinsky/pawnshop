package com.markostrutinsky.pawn_shop.evaluation;

import com.markostrutinsky.pawn_shop.model.PawnItem;

import java.math.BigDecimal;

public interface EvaluationStrategy {
    BigDecimal evaluate(PawnItem pawnItem);
}
