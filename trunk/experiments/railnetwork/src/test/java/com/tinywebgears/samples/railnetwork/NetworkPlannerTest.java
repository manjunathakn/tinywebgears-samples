package com.tinywebgears.samples.railnetwork;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        Integer distance = planner.checkPath("A-B-C");
        logger.info("A-B-C : " + distance);
        Assert.assertEquals(Integer.valueOf(9), distance);
    }

    @Test
    public void testFollowRoute2() throws NoRouteException
    {
        Integer distance = planner.checkPath("A-D");
        logger.info("A-D : " + distance);
        Assert.assertEquals(Integer.valueOf(5), distance);
    }

    @Test
    public void testFollowRoute3() throws NoRouteException
    {
        Integer distance = planner.checkPath("A-D-C");
        logger.info("A-D-C : " + distance);
        Assert.assertEquals(Integer.valueOf(13), distance);
    }

    @Test
    public void testFollowRoute4() throws NoRouteException
    {
        Integer distance = planner.checkPath("A-E-B-C-D");
        logger.info("A-E-B-C-D : " + distance);
        Assert.assertEquals(Integer.valueOf(22), distance);
    }

    @Test(expected = NoRouteException.class)
    public void testFollowRoute5() throws NoRouteException
    {
        Integer distance = planner.checkPath("A-E-D");
        logger.info("A-E-D : " + distance);
        Assert.assertTrue(false);
    }

    @Test
    public void testGetRoutes1() throws NoRouteException
    {
        Set<Route> allRoutes = planner.getAllRoutes("C", "C", 1, 3);
        Assert.assertEquals(Integer.valueOf(2), Integer.valueOf(allRoutes.size()));
        Set<String> expectedRoutes = new HashSet<String>(Arrays.asList("C-D-C", "C-E-B-C"));
        for (Route route : allRoutes)
            Assert.assertTrue(expectedRoutes.contains(route.getPath().toString()));
    }
}
