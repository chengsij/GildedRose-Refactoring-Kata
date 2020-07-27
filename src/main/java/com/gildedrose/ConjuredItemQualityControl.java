package com.gildedrose;

public interface ConjuredItemQualityControl extends GenericItemQualityControl{

    default void updateConjuredItem(Item item){
        int originalQuality = item.quality;
        updateGenericItem(item);
        int deterioration = originalQuality - item.quality;
        if (deterioration < item.quality){
            item.quality -= deterioration;
        } else {
            item.quality = 0;
        }
    }
}
