package com.tinywebgears.samples.railnetwork;

import java.util.HashMap;
import java.util.Map;

public class StationNode
{
    private String name;
    private Map<String, Pair<StationNode, Integer>> routes;

    StationNode(String name)
    {
        assert name != null;
        this.name = name;
        this.routes = new HashMap<String, Pair<StationNode, Integer>>();
    }

    void addRoute(StationNode destinationStation, Integer distance)
    {
        routes.put(destinationStation.getName(), new Pair<StationNode, Integer>(destinationStation, distance));
    }

    Pair<StationNode, Integer> checkRoute(String stationName)
    {
        return routes.get(stationName);
    }

    String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if (!(o instanceof StationNode))
            return false;
        StationNode s = (StationNode) o;
        return name.equals(s.name);
    }

    @Override
    public int hashCode()
    {
        // TODO: Improve hash
        return 0;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
