package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normalItem_SellInAndQualityDecreaseByOne() {
        Item[] items = new Item[]{new Item("foo", 10, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("foo", 9, 9));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void normalItem_SystemCanProcessMultipleItems() {
        Item[] items = new Item[] {new Item("foo", 10, 10),
                new Item("Elixir of the Mongoose", 5, 7)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();


        Item[] expectedArray = new Item[] {new Item("foo", 9, 9),
                new Item("Elixir of the Mongoose", 4, 6)
        };
        List<Item> expected = Arrays.asList(expectedArray);

        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void normalItem_PastSellDateDecreasesTwiceAsFast() {
        Item[] items = new Item[]{new Item("foo", 0, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("foo", -1, 8));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void normalItem_QualityNeverNegative() {
        Item[] items = new Item[]{new Item("foo", 1, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("foo", 0, 0));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void normalItem_ProcessConsecutive() {
        Item[] items = new Item[]{new Item("foo", 10, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("foo", 8, 8));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void agedBrie_IncreasesInQuality() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Aged Brie", 9, 11));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void agedBrie_MaxQualityIs50() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();


        List<Item> expected = Collections.singletonList(new Item("Aged Brie", 9, 50));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void agedBrie_CanHandleNegativeSellIn_QualityDegradesTwiceAsFast() {
        Item[] items = new Item[]{new Item("Aged Brie", -10, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Aged Brie", -11, 42));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void sulfuras_NoSellInAndNoQualityDecrease() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 80)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void backstage_ZeroAfterSellIn() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void backstage_5DaysOrLessQualityIncreasesBy3() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 23));
        assertEquals(expected, Arrays.asList(app.items));

        app.updateQuality();

        List<Item> expected2 = Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 26));
        assertEquals(expected2, Arrays.asList(app.items));
    }

    @Test
    void backstage_10DaysOrLessQualityIncreasesBy2() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", 9, 22));
        assertEquals(expected, Arrays.asList(app.items));

        app.updateQuality();

        List<Item> expected2 = Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", 8, 24));
        assertEquals(expected2, Arrays.asList(app.items));
    }

    @Test
    void backstage_NormalIfSellInIsBiggerThan10() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 21));
        assertEquals(expected, Arrays.asList(app.items));
    }

    @Test
    void backstage_QualityCantGetBiggerThan50() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 2, 49)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        List<Item> expected = Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50));
        assertEquals(expected, Arrays.asList(app.items));
    }


}
