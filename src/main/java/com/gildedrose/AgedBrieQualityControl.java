package com.gildedrose;

public interface AgedBrieQualityControl {

    default void updateAgedBrie(Item item){
        item.sellIn--;
        if (item.quality < 50){
            item.quality++;
        }
    }
}
