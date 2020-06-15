package com.gildedrose;

import java.util.Objects;

public class SellIn {

    private final int days;
    private final SellInPolicy sellInPolicy;

    public SellIn(int days, SellInPolicy sellInPolicy) {
        this.days = days;
        this.sellInPolicy = sellInPolicy;
    }

    public int getDays() {
        return days;
    }

    public int update() {
        return sellInPolicy.update(days);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellIn sellIn = (SellIn) o;
        return days == sellIn.days &&
                Objects.equals(sellInPolicy, sellIn.sellInPolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(days, sellInPolicy);
    }
}
