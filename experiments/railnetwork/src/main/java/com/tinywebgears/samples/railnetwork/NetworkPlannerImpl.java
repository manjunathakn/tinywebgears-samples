package com.tinywebgears.samples.railnetwork;

import java.util.Set;

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
        initializeNetwork(inputGraph);
        logger.debug("TrainPlanner initialized.");
        initialized = true;
    }

    private void initializeNetwork(String inputGraph)
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
    public Route checkPath(Path path) throws NoRouteException
    {
        logger.debug("checkPath : " + path);
        return network.checkPath(path);
    }

    @Override
    public Route checkPath(String pathString) throws NoRouteException
    {
        return checkPath(Path.parseString(pathString));
    }

    @Override
    public Set<Route> getAllRoutes(String source, String destination, Integer minStops, Integer maxStops)
    {
        return network.getAllRoutes(source, destination, minStops, maxStops);
    }

    @Override
    public Set<Route> getAllRoutes(String source, String destination, Integer maxDistance)
    {
        return network.getAllRoutes(source, destination, maxDistance);
    }

    @Override
    public Route getShortestRoute(String source, String destination)
    {
        return network.getShortestRoute(source, destination);
    }
}
