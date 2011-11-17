package com.tinywebgears.samples.railnetwork;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Immutable object representing a path amongst stations.
 */
class Path
{
    private final Queue<String> stations;

    Path()
    {
        stations = new LinkedList<String>();
    }

    private Path(Queue<String> stations)
    {
        this.stations = stations;
    }

    public static Path parseString(String pathString)
    {
        Path path = new Path();
        String[] stations = pathString.split("-");
        for (String station : stations)
            path = path.addStation(station);
        return path;
    }

    Path addStation(String station)
    {
        Queue<String> newStations = new LinkedList<String>();
        newStations.addAll(stations);
        newStations.offer(station);
        return new Path(newStations);
    }

    public Queue<String> getStations()
    {
        Queue<String> copy = new LinkedList<String>();
        copy.addAll(stations);
        return copy;
    }

    // TODO: equals() and hashCode()

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
