package com.markostrutinsky.pawn_shop.service.impl;

import com.markostrutinsky.pawn_shop.model.Agreement;
import com.markostrutinsky.pawn_shop.model.ItemFactory;
import com.markostrutinsky.pawn_shop.model.PawnItem;
import com.markostrutinsky.pawn_shop.model.PawnItemCategory;
import com.markostrutinsky.pawn_shop.repository.PawnItemRepository;
import com.markostrutinsky.pawn_shop.service.PawnItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PawnItemServiceImpl implements PawnItemService {
    @Qualifier("itemFactoryImpl")
    private final ItemFactory factory;
    private PawnItemRepository itemRepository;

    @Override
    public PawnItem createItem(Map<String, String> formData) {
        return factory.createPawnItem(formData);
    }

    @Override
    public PawnItem addNewItem(Map<String, String> formData) throws IOException, SQLException {
        PawnItem item = createItem(formData);
        item.setCategory(PawnItemCategory.convertStringToPawnItemCategory(formData.get("category")));
        if (!formData.get("photo").isEmpty()) {
            byte[] photoBytes = Base64.getDecoder().decode(formData.get("photo"));
            Blob photoBlob = new SerialBlob(photoBytes);
            item.setPhoto(photoBlob);
        }
        Agreement agreement = createAgreement(formData);
        item.setAgreement(agreement);

        return itemRepository.save(item);
    }

    @Override
    public List<? extends PawnItem> getAllItemsByCategory(PawnItemCategory category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<? extends PawnItem> findAllItems() {
        return itemRepository.findAll();
    }

    private Agreement createAgreement(Map<String, String> formData) {
        return Agreement.AgreementBuilder
                .builder()
                .personWithName(formData.get("firstName"))
                .bySurname(formData.get("lastName"))
                .withEmail(formData.get("email"))
                .takesLoanForAmount(new BigDecimal(formData.get("loanAmount")))
                .atAnInterestRate(Integer.parseInt(formData.get("interestRate")))
                .startsWith(new Date())
                .expires(Date.from(Instant.now().plus(Duration.ofDays(30))))
                .isPaid(false)
                .build();
    }
}
