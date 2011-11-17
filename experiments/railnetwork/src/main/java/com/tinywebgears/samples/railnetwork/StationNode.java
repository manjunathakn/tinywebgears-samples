package com.tinywebgears.samples.railnetwork;

import java.util.HashMap;
import java.util.Map;

public class StationNode
{
    private String name;
    private Map<String, Pair<StationNode, Integer>> nextStations;

    StationNode(String name)
    {
        assert name != null;
        this.name = name;
        this.nextStations = new HashMap<String, Pair<StationNode, Integer>>();
    }

    void addRoute(StationNode destinationNode, Integer distance)
    {
        nextStations.put(destinationNode.getName(), new Pair<StationNode, Integer>(destinationNode, distance));
    }

    String getName()
    {
        return name;
    }

    Pair<StationNode, Integer> checkNextStation(String station)
    {
        return nextStations.get(station);
    }

    Map<String, Pair<StationNode, Integer>> getAllNextStations()
    {
        return nextStations;
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
