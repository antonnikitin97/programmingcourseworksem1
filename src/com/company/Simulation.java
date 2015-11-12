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
        zooSimLinkedTo = new Zoo();
    }

    public void displayStats()
    {
        System.out.format("#### ZOO STATUS ###\nMonths Passed: %s\nNumber of Enclosures: %s", monthPassed, zooSimLinkedTo.enclosures.length);
        getEnclosureStatus();
    }

    public void go()
    {
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
            System.out.format("### Enclosure %s ###\nAnimals in Enclosure: %s", currentEnclosure, e.size());
            e.printTypesOfAnimalInEnclosre();
            System.out.println("####");
            currentEnclosure += 1;
        }
    }
}
