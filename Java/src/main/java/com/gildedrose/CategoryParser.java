package com.gildedrose;

import java.util.List;

import static java.util.Collections.singletonList;

public class CategoryParser {

    private static final List<String> legendary = singletonList("Sulfuras, Hand of Ragnaros");
    private static final List<String> standardIncreasing = singletonList("Aged Brie");
    private static final List<String> specialIncreasing = singletonList("Backstage passes");


    private CategoryParser() {
    }

    public static ItemCategory parse(String itemName) {
        if (matches(legendary, itemName)) {
            return ItemCategory.LEGENDARY;
        } else if (matches(standardIncreasing, itemName)) {
            return ItemCategory.STANDARD_INCREASING_QUALITY;
        } else if (matches(specialIncreasing, itemName)) {
            return ItemCategory.SPECIAL_INCREASING_QUALITY;
        } else {
            return ItemCategory.NORMAL;
        }
    }

    private static boolean matches(List<String> list, String itemName) {
        return list.stream()
                .anyMatch(itemName::contains);
    }


}
