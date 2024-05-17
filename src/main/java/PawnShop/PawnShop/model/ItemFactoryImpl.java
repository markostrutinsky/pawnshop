package PawnShop.PawnShop.model;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class ItemFactoryImpl implements ItemFactory {
    private final Map<PawnItemCategory, Function<Map<String, String>, PawnItem>> itemCreators;

    public ItemFactoryImpl() {
        itemCreators = new EnumMap<>(PawnItemCategory.class);
        itemCreators.put(PawnItemCategory.JEWELRY, formData -> new Jewelry(formData));
        itemCreators.put(PawnItemCategory.ANTIQUES, formData -> new Antiques(formData));
        itemCreators.put(PawnItemCategory.ELECTRONICS, formData -> new Electronics(formData));
        itemCreators.put(PawnItemCategory.CLOTHES, formData -> new Clothes(formData));
        itemCreators.put(PawnItemCategory.FURNITURE, formData -> new Furniture(formData));
        itemCreators.put(PawnItemCategory.SPORTSEQUIPMENT, formData -> new SportEquipment(formData));
        itemCreators.put(PawnItemCategory.HOUSEHOLDGOODS, formData -> new HouseholdGoods(formData));
        itemCreators.put(PawnItemCategory.MUSICALINSTRUMENTS, formData -> new MusicalInstrument(formData));
    }

    @Override
    public PawnItem createPawnItem(Map<String, String> formData) {
        return itemCreators.get(PawnItemCategory.convertStringToPawnItemCategory(formData.get("category"))).apply(formData);
    }
}
