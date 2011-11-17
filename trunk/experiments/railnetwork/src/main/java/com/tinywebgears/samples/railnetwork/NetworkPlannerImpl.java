package com.tinywebgears.samples.railnetwork;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkPlannerImpl implements NetworkPlanner
{
    private final Logger logger = LoggerFactory.getLogger(NetworkPlannerImpl.class);

    private boolean initialized = false;
    private final TrainNetworkImpl network;

    public NetworkPlannerImpl(String inputGraph)
    {
        network = new TrainNetworkImpl();
        initializeGraph(inputGraph);
        logger.info("TrainPlanner initialized.");
        initialized = true;
    }

    private void initializeGraph(String inputGraph)
    {
        String[] routes = inputGraph.split(", *");
        for (String route : routes)
        {
            try
            {
                String source = route.substring(0, 1);
                String destination = route.substring(1, 2);
                Integer distance = Integer.valueOf(route.substring(2));
                logger.debug("Route " + source + "-" + destination + " : " + distance);
                network.addRout(source, destination, distance);
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

    public boolean isInitialized()
    {
        return initialized;
    }

    @Override
    public Integer checkPath(Path path) throws NoRouteException
    {
        return network.checkPath(path);
    }

    @Override
    public Integer checkPath(String pathString) throws NoRouteException
    {
        return checkPath(Path.parseString(pathString));
    }

    @Override
    public List<Route> getAllRoutes(String source, String destination, Integer minStops, Integer maxStops)
    {
        return new ArrayList<Route>();
    }
}
