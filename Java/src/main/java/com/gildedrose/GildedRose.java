package com.gildedrose;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class GildedRose {

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        final List<ItemDomain> itemDomains = Arrays.stream(this.items)
                .map(ItemFactory::build)
                .map(ItemDomain::update)
                .collect(toList());

        this.items = itemDomains.stream().map(this::map).toArray(Item[]::new);
    }

    private Item map(ItemDomain itemDomain) {
        return new Item(itemDomain.getName(), itemDomain.getSellIn(), itemDomain.getQuality());
    }
}