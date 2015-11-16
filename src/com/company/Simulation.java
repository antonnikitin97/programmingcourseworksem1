/*
The class responsible for 'running' the simulation, keeps track of the months that have passed and contains

 */

package com.company;

public class Simulation
{
    private Integer monthPassed = 0;
    private Zoo zooSimLinkedTo;

    public Simulation()
    {
    }

    public void go()
    {
    }

    public void incrementMonth()
    {
        monthPassed += 1;
    }

    public Integer getMonthsPassed()
    {
        return monthPassed;
    }

    public void setZooSimLinkedTo(Zoo zoo)
    {
        this.zooSimLinkedTo = zoo;
    }

    public Zoo getZooSimLinkedTo()
    {
        return zooSimLinkedTo;
    }
}
