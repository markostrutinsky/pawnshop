package com.markostrutinsky.pawn_shop.response;

import com.markostrutinsky.pawn_shop.model.Agreement;

public class AgreementResponse {
    private Long id;
    private String email;

    public AgreementResponse(Agreement agreement) {
        this.id = agreement.getId();
        this.email = agreement.getEmail();
    }
}
