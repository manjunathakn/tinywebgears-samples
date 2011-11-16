package com.tinywebgears.samples.simplejavatest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainPlanner
{
    private final Logger logger = LoggerFactory.getLogger(TrainPlanner.class);

    private boolean initialized = false;
    private Map<Integer, SameDepthRoutes> routesPartitions;

    public void initialize(String inputGraph)
    {
        routesPartitions = new HashMap<Integer, SameDepthRoutes>();
        createSingleStopRoutes(inputGraph);
        logger.info("TrainPlanner initialized.");
        initialized = true;
    }

    private void createSingleStopRoutes(String inputGraph)
    {
        SameDepthRoutes initialRoutes = new SameDepthRoutes();
        routesPartitions.put(1, initialRoutes);
        String[] routes = inputGraph.split(",[ ]*");
        for (String route : routes)
        {
            try
            {
                String sourceStop = route.substring(0, 1);
                String destinationStop = route.substring(1, 2);
                Integer distance = Integer.valueOf(route.substring(2));
                logger.debug("Route " + sourceStop + "-" + destinationStop + " : " + distance);
                AllPossibleDestinations destinations = initialRoutes.getDestinations(sourceStop);
                destinations.addRoute(destinationStop, new Route(sourceStop, destinationStop, distance));
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                logger.info("Invalid route: " + route);
            }
            catch (IllegalArgumentException e)
            {
                logger.info("Invalid route: " + route);
            }
        }
    }

    public SameDepthRoutes getInitialRoutes()
    {
        return routesPartitions.get(1);
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
