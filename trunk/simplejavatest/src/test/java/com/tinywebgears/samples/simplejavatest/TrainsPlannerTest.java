package com.tinywebgears.samples.simplejavatest;

import junit.framework.Assert;

import org.junit.Test;

public class TrainsPlannerTest
{
    private final static String testString = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    @Test
    public void testTrainPlannerIsInitialized()
    {
        TrainPlanner planner = new TrainPlanner();
        planner.initialize(testString);
        Assert.assertEquals(true, planner.isInitialized());
        SameDepthRoutes initialRoutes = planner.getInitialRoutes();
        AllPossibleDestinations destinations = initialRoutes.getDestinations("A");
        Assert.assertEquals(1, destinations.getRoutes("B").size());
        Assert.assertEquals(Integer.valueOf(5), destinations.getRoutes("B").get(0).getDistance());
        Assert.assertEquals(0, destinations.getRoutes("C").size());
        Assert.assertEquals(1, destinations.getRoutes("E").size());
        Assert.assertEquals(Integer.valueOf(7), destinations.getRoutes("E").get(0).getDistance());
    }
}
