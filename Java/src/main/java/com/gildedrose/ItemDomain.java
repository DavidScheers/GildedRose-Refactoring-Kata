package com.gildedrose;

import java.util.Objects;

public class ItemDomain {

    private final String name;
    private final Quality quality;
    private final SellIn sellIn;

    public ItemDomain(String name, Quality quality, SellIn sellIn) {
        this.name = name;
        this.quality = quality;
        this.sellIn = sellIn;
    }

    public String getName() {
        return name;
    }

    public int getQuality() {
        return quality.getQuality();
    }

    public int getSellIn() {
        return sellIn.getDays();
    }

    public ItemDomain update() {
        final int updatedQuality = quality.update();
        final int updatedSellIn = sellIn.update();
        return ItemFactory.build(new Item(this.name, updatedSellIn, updatedQuality));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDomain that = (ItemDomain) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(quality, that.quality) &&
                Objects.equals(sellIn, that.sellIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quality, sellIn);
    }
}
