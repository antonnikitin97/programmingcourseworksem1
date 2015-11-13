/*
The class responsible for 'running' the simulation, keeps track of the months that have passed
 */

package com.company;

public class Simulation
{
    private Integer monthPassed = 0;
    private Zoo zooSimLinkedTo;

    public Simulation()
    {
        zooSimLinkedTo = new Zoo(this);
        zooSimLinkedTo.createEnclosuresAndZooKeepersTEST();
        zooSimLinkedTo.populateZooTEST();
    }

    public void go()
    {
        for(int i = 0; i <= 1; i ++)
        {
            zooSimLinkedTo.aMonthPasses();
            System.out.println();
        }
    }

    public void incrementMonth()
    {
        monthPassed += 1;
    }

    public Integer getMonthsPassed()
    {
        return monthPassed;
    }

}
