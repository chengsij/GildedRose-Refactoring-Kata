package com.gildedrose;

public interface Logger {

    default void log(Item item, int day){
        System.out.println(String.format("Day %d | %s", day, item.toString()));
    }
}
