package com.tinywebgears.samples.simplejavatest;

import java.util.Queue;

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
        String[] routes = inputGraph.split(",[ ]*");
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
    public StationNode getStation(String stationName)
    {
        return network.getStation(stationName);
    }

    @Override
    public Integer checkPath(Queue<String> path) throws NoRouteException
    {
        return network.checkPath(path);
    }
}
