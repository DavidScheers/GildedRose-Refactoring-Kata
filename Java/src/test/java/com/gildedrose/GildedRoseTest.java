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
}
