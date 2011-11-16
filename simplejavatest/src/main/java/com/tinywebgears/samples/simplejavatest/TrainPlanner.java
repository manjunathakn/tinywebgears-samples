package com.tinywebgears.samples.simplejavatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainPlanner
{
    private final Logger logger = LoggerFactory.getLogger(TrainPlanner.class);

    private boolean initialized = false;

    public TrainPlanner()
    {
        initialize();
    }

    private void initialize()
    {
        initialized = true;
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
