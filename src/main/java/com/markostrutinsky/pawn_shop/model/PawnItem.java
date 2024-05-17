package com.markostrutinsky.pawn_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "items")
@Data
@NoArgsConstructor
public class PawnItem {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String pawnItemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PawnItemCategory category;

    @Lob
    @Column(name = "photo", nullable = false)
    private Blob photo;

    @OneToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    public PawnItem(Map<String, String> fromData) {
        this.pawnItemName = fromData.get("name");
        this.category = PawnItemCategory.valueOf(fromData.get("category"));
    }
}
