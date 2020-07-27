package com.gildedrose;

class GildedRose implements AgedBrieQualityControl,
        SulfurasQualityControl,
        BackstagePassQualityControl,
        GenericItemQualityControl,
        ConjuredItemQualityControl
{
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean isItem(String itemName, String catagoryName){
        return itemName.toLowerCase().contains(catagoryName.toLowerCase());
    }

    public void updateQuality(Item item){
        //check for speical items first
        if (isItem(item.name, Constants.AGED_BRIE)){
            updateAgedBrie(item);
        } else if (isItem(item.name, Constants.SULFURAS)){
            updateSulfuras(item);
        } else if (isItem(item.name, Constants.BACKSTAGE_PASS)) {
            updateBackStagePass(item);
        } else if (isItem(item.name, Constants.CONJURED)) {
            updateConjuredItem(item);
        } else {
            updateGenericItem(item);
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQuality(items[i]);
        }
    }
}