package com.gildedrose;

import java.util.List;

import static java.util.Collections.singletonList;

public class CategoryParser {

    private static final List<String> LEGENDARY = singletonList("Sulfuras, Hand of Ragnaros");
    private static final List<String> STANDARD_INCREASING = singletonList("Aged Brie");
    private static final List<String> SPECIAL_INCREASING = singletonList("Backstage passes");
    private static final List<String> CONJURED = singletonList("Conjured");


    private CategoryParser() {
    }

    public static ItemCategory parse(String itemName) {
        if (matches(LEGENDARY, itemName)) {
            return ItemCategory.LEGENDARY;
        } else if (matches(STANDARD_INCREASING, itemName)) {
            return ItemCategory.STANDARD_INCREASING_QUALITY;
        } else if (matches(SPECIAL_INCREASING, itemName)) {
            return ItemCategory.SPECIAL_INCREASING_QUALITY;
        } else if (matches(CONJURED, itemName)) {
            return ItemCategory.CONJURED;
        } else {
            return ItemCategory.NORMAL;
        }
    }

    private static boolean matches(List<String> list, String itemName) {
        return list.stream()
                .anyMatch(itemName::contains);
    }


}
