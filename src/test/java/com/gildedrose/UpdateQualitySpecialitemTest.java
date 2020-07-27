package com.gildedrose;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the updateQUality behavior of special items
 * these are the requirements it should test
 * - "Aged Brie" actually increases in Quality the older it gets
 * - The Quality of an item is never more than 50
 * - "Sulfuras" never has to be sold or decreases in Quality
 * - Backstage passes Quality increases as it's SellIn value approaches:
 *      - backstage passes Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less
 * - Backstage passes Quality drops to 0 after the concert
 */
public class UpdateQualitySpecialitemTest implements Logger{
    static Item[] items;
    static GildedRose controller;
    static final int GENERIC_INCREMENT = 1;


    @BeforeAll
    public static void setup(){
        items = new Item[1];
        controller = new GildedRose(items);
    }

    @Test
    public void testAgedBrie_incrementQualityBy1(){
        int initSelling = 2;
        int initQuality = 10;
        Item testItem = new Item("Aged Brie", initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 2 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - (i + 1), testItem.sellIn);
            assertEquals(initQuality + (i + GENERIC_INCREMENT), testItem.quality);
        }
        log(testItem, 2);
    }


    @Test
    public void testAgedBrie_QualityCapsAt50(){
        int initSelling = 2;
        int initQuality = 50;
        Item testItem = new Item("Aged Brie", initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 2 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - (i + 1), testItem.sellIn);
            assertEquals(initQuality, testItem.quality);
        }
        log(testItem, 2);
    }


    @Test
    public void testSulfuras_ConstantQuality(){
        int initSelling = 2;
        int initQuality = 50;
        Item testItem = new Item("Sulfuras, Hand of Ragnaros", initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 2 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling, testItem.sellIn);
            assertEquals(initQuality, testItem.quality);
        }
        log(testItem, 2);
    }


    @Test
    public void testBackstagePasses_QualityBefore10DaysSellIn(){
        int initSelling = 12;
        int initQuality = 20;
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 2 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - (i + 1), testItem.sellIn);
            assertEquals(initQuality + (i + GENERIC_INCREMENT), testItem.quality);
        }
        log(testItem, 2);
    }


    @Test
    public void testBackstagePasses_QualityBetween10To5DaysSellIn(){
        int initSelling = 10;
        int initQuality = 20;
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 5 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - (i + 1), testItem.sellIn);
            assertEquals(initQuality + 2 * (i + GENERIC_INCREMENT), testItem.quality);
        }
        log(testItem, 2);
    }


    @Test
    public void testBackstagePasses_QualityBetween5To0DaysSellIn(){
        int initSelling = 5;
        int initQuality = 20;
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 5 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - (i + 1), testItem.sellIn);
            assertEquals(initQuality + 3 * (i + GENERIC_INCREMENT), testItem.quality);
        }
        log(testItem, 2);
    }


    @Test
    public void testBackstagePasses_QualityAfterSellIn(){
        int initSelling = 0;
        int initQuality = 20;
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 5 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - (i + 1), testItem.sellIn);
            assertEquals(0, testItem.quality);
        }
        log(testItem, 2);
    }
}
