package com.tinywebgears.samples.railnetwork;

import java.util.LinkedList;
import java.util.Queue;

class Path
{
    private Queue<String> stations = new LinkedList<String>();

    public static Path parseString(String pathString)
    {
        Path path = new Path();
        String[] stations = pathString.split("-");
        for (String station : stations)
            path.addStation(station);
        return path;
    }

    void addStation(String station)
    {
        stations.offer(station);
    }

    public Queue<String> getStations()
    {
        Queue<String> copy = new LinkedList<String>();
        copy.addAll(stations);
        return copy;
    }

    @Override
    public String toString()
    {
        StringBuilder pathString = new StringBuilder();
        Boolean first = true;
        for (String station : stations)
        {
            if (first)
                first = false;
            else
                pathString.append("-");
            pathString.append(station);
        }
        return pathString.toString();
    }
}
