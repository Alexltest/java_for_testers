package ru.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class ThirdTest {

    @Test
    public void testArea() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }
}