package com.tinywebgears.samples.simplejavatest;

class Route
{
    private final String sourceStop;
    private final String destinationStop;
    private final Integer distance;
    private final Pair<Route> subRoutes;

    Route(String sourceStop, String destinationStop, Integer distance)
    {
        this(sourceStop, destinationStop, distance, null, null);
    }

    Route(String sourceStop, String destinationStop, Integer distance, Route firstPart, Route secondPart)
    {
        assert distance != null || (firstPart != null && secondPart != null);
        assert distance > 0 || distance == null;
        if (firstPart != null && secondPart != null)
        {
            this.subRoutes = new Pair<Route>(firstPart, secondPart);
            this.distance = firstPart.getDistance() + secondPart.getDistance();
        }
        else
        {
            this.subRoutes = null;
            this.distance = distance;
        }
        this.sourceStop = sourceStop;
        this.destinationStop = destinationStop;
    }

    public Integer getDistance()
    {
        return distance;
    }

    public Pair<Route> getSubRoutes()
    {
        return subRoutes;
    }

    @Override
    public String toString()
    {
        if (subRoutes == null)
            return "[" + sourceStop + "-" + destinationStop + ":" + distance + "]";
        return "[[" + subRoutes.getFist() + "][" + subRoutes.getSecond() + "]:" + distance + "]";
    }
}
