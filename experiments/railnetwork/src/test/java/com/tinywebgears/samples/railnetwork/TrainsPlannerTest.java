package com.tinywebgears.samples.railnetwork;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class TrainsPlannerTest
{
    private final static String testString = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    private static NetworkPlanner planner;

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
    public void testFollowRoute() throws NoRouteException
    {
        Queue<String> path = new LinkedList<String>(Arrays.asList("A", "B", "C", "E"));
        Integer distance = planner.checkPath(path);
        Assert.assertEquals(Integer.valueOf(11), distance);
    }
}
