package com.gildedrose;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * This class tests the updateQUality behavior of generic item
 * these are the requirements it should test
 * - At the end of each day our system lowers both values for every item
 * - Once the sell by date has passed, Quality degrades twice as fast
 * - The Quality of an item is never negative
 *
 * Assumptions:
 * This assumes initial quality q is 0 <= q <= 50
 *
 * Update 07-27-2020 new requirement
 * - "Conjured" items degrade in Quality twice as fast as normal items
 */
public class UpdateQualityGenericTest implements Logger{

    static Item[] items;
    static GildedRose controller;
    static final int GENERIC_DECREMENT = 1;
    static final String ITEM_NAME = "Generic Item";


    @BeforeAll
    public static void setup(){
        items = new Item[1];
        controller = new GildedRose(items);
    }

    @Test
    public void testGenericItemBeforeSellinDate_decrementQualityBy1(){
        int initSelling = 2;
        int initQuality = 10;
        Item testItem = new Item(ITEM_NAME, initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 2 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - i - 1, testItem.sellIn);
            assertEquals(initQuality - i - GENERIC_DECREMENT, testItem.quality);
        }
        log(testItem, 2);
    }

    @Test
    public void testGenericItemAfterSellinDate_decrementQualityBy2(){
        int initSelling = 0;
        int initQuality = 10;
        Item testItem = new Item(ITEM_NAME, initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 2 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - i - 1, testItem.sellIn);
            assertEquals(initQuality - 2 * (i +  GENERIC_DECREMENT), testItem.quality);
        }
        log(testItem, 2);
    }

    @Test
    public void testGenericItemAfterSellinDate_QualityIsPositive(){
        int initSelling = 0;
        int initQuality = 0;
        Item testItem = new Item(ITEM_NAME, initSelling, initQuality);
        items[0] = testItem;
        for (int i = 0 ; i < 2 ; i++){
            log(testItem, i);
            controller.updateQuality();
            assertEquals(initSelling - i - 1, testItem.sellIn);
            assertEquals(initQuality, testItem.quality);
        }
        log(testItem, 2);
    }


}
