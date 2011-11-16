package com.tinywebgears.samples.simplejavatest;

import junit.framework.Assert;

import org.junit.Test;

public class TrainsPlannerTest
{
    @Test
    public void testDummy()
    {
        TrainPlanner planner = new TrainPlanner();
        Assert.assertEquals(true, planner.isInitialized());
    }
}
