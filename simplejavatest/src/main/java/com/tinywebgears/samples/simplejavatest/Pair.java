package com.tinywebgears.samples.simplejavatest;

class Pair<A>
{
    private final A fist;
    private final A second;

    public Pair(A first, A second)
    {
        this.fist = first;
        this.second = second;
    }

    public A getFist()
    {
        return fist;
    }

    public A getSecond()
    {
        return second;
    }
}
