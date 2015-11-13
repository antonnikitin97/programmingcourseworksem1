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

    public void displayStats()
    {
        System.out.format("#### ZOO STATUS ###\nMonths Passed: %s\nNumber of Enclosures: %s\n", monthPassed, zooSimLinkedTo.enclosures.length);
        getEnclosureStatus();
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
        monthPassed ++;
    }

    /*
    This method iterates over all the enclosures in the zoo and prints out information about them including
    the types of animal in the enclosure, their age and their health.
    */
    private void getEnclosureStatus()
    {
        Integer currentEnclosure = 0;

        for(Enclosure e : zooSimLinkedTo.enclosures)
        {
            System.out.format("### Enclosure %s ###\nAnimals in Enclosure: %s\nWaste size: %s\n", currentEnclosure, e.size(), e.getWasteSize());
            e.printTypesOfAnimalInEnclosre();
            System.out.println("#######################################");
            currentEnclosure += 1;
        }
    }
}
