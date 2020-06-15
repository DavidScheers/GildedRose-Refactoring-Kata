package com.gildedrose;

import java.util.Objects;

public class Quality {

    private final int quality;
    private final DegradePolicy degradePolicy;

    public Quality(int quality, DegradePolicy degradePolicy) {
        this.quality = quality;
        this.degradePolicy = degradePolicy;
    }

    public int getQuality() {
        return quality;
    }

    public int update() {
        return degradePolicy.update(quality);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quality quality1 = (Quality) o;
        return quality == quality1.quality &&
                Objects.equals(degradePolicy, quality1.degradePolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quality, degradePolicy);
    }
}
