package com.gildedrose;

public interface GenericItemQualityControl {

    default void updateGenericItem(Item item){
        item.sellIn--;
        if (item.quality > 0 && item.sellIn >= 0 ) {
                item.quality--;
        } else if (item.quality > 1 && item.sellIn < 0){
            item.quality -= 2;
        }
    }
}
