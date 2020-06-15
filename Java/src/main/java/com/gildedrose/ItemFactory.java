package com.gildedrose;

public class ItemFactory {

    private ItemFactory() {
    }

    public static ItemDomain build(Item item) {
        final ItemCategory itemCategory = CategoryParser.parse(item.name);
        final SellIn sellIn = new SellIn(item.sellIn, determineSellInPolicy(itemCategory));
        final Quality quality = new Quality(item.quality, determineDegradePolicy(itemCategory, sellIn));

        return new ItemDomain(item.name, quality, sellIn);
    }

    private static DegradePolicy determineDegradePolicy(ItemCategory category, SellIn sellIn) {
        switch (category) {
            case NORMAL:
                return val -> min(val - calcScalar(1, sellIn));
            case STANDARD_INCREASING_QUALITY:
                return val -> max(val + calcScalar(1, sellIn));
            case SPECIAL_INCREASING_QUALITY:
                return val -> max(val + calcSpecialScalar(sellIn, val));
            case CONJURED:
                return val -> min(val - calcScalar(2, sellIn));
            default:
                return val -> val;
        }
    }

    private static int calcSpecialScalar(SellIn sellIn, int val) {
        final int days = sellIn.getDays();
        if (days < 1) {
            return -val;
        } else if (days < 6) {
            return 3;
        } else if (days < 11) {
            return 2;
        } else {
            return 1;
        }
    }

    private static SellInPolicy determineSellInPolicy(ItemCategory category) {
        return category == ItemCategory.LEGENDARY
                ? val -> val
                : val -> val - 1;
    }

    private static int calcScalar(int scalar, SellIn sellIn) {
        return sellIn.getDays() < 1
                ? scalar * 2
                : scalar;
    }

    private static int max(int val) {
        return val > 49 ? 50 : val;
    }

    private static int min(int val) {
        return val < 1 ? 0 : val;
    }

}
