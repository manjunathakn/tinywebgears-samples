package com.tinywebgears.samples.railnetwork;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkPlannerTest
{
    private final static String testString = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    private static NetworkPlanner planner;

    private final Logger logger = LoggerFactory.getLogger(NetworkPlannerTest.class);

    @BeforeClass
    public static void oneTimeSetUp()
    {
        planner = new NetworkPlannerImpl(testString);
    }

    @Test
    public void testTrainPlannerIsInitialized()
    {
        Assert.assertEquals(true, planner.isInitialized());
    }

    @Test
    public void testFollowRoute1() throws NoRouteException
    {
        Integer distance = planner.checkPath("A", "B", "C");
        logger.info("Route A-B-C : " + distance);
        Assert.assertEquals(Integer.valueOf(9), distance);
    }

    @Test
    public void testFollowRoute2() throws NoRouteException
    {
        Integer distance = planner.checkPath("A", "D");
        logger.info("Route A-D : " + distance);
        Assert.assertEquals(Integer.valueOf(5), distance);
    }

    @Test
    public void testFollowRoute3() throws NoRouteException
    {
        Integer distance = planner.checkPath("A", "D", "C");
        logger.info("Route A-D-C : " + distance);
        Assert.assertEquals(Integer.valueOf(13), distance);
    }

    @Test
    public void testFollowRoute4() throws NoRouteException
    {
        Integer distance = planner.checkPath("A", "E", "B", "C", "D");
        logger.info("Route A-E-B-C-D : " + distance);
        Assert.assertEquals(Integer.valueOf(22), distance);
    }

    @Test(expected = NoRouteException.class)
    public void testFollowRoute5() throws NoRouteException
    {
        Integer distance = planner.checkPath("A", "E", "D");
        logger.info("Route A-E-D : " + distance);
        Assert.assertTrue(false);
    }
}
