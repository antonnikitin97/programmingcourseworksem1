/*
The class responsible for 'running' the simulation, keeps track of the months that have passed and contains

 */

package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Simulation
{
    private Integer monthPassed = 0;
    private Zoo zooSimLinkedTo;
    protected Scanner inputScanner = new Scanner(System.in);
    Integer numberOfIterations;

    public Simulation()
    {
    }

    public void go()
    {
        System.out.println("Please enter the number of iterations that you want the sim to perform: ");
        try
        {
            numberOfIterations = Integer.parseInt(inputScanner.nextLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Input must be a number! Please try again!");
        }

        for (int i = 0; i < numberOfIterations; i++)
        {
            zooSimLinkedTo.aMonthPasses();
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

    public void setZooSimLinkedTo(Zoo zoo)
    {
        this.zooSimLinkedTo = zoo;
    }

    public Zoo getZooSimLinkedTo()
    {
        return zooSimLinkedTo;
    }
}
