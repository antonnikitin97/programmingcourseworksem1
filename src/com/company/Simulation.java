/*
The class responsible for 'running' the simulation, keeps track of the months that have passed and contains methods
that initialize the zoo, obtaining status of the whole zoo and their enclosures and the 'start' method.
*/
package com.company;

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

    /*
    Method to start the process of reading the config file for the zoo.
    */
    public void initialize(String pathOfFile)
    {
        ConfigFile file = new ConfigFile();
        file.getDirectoryOfFile(pathOfFile);
    }

    /*
    Begins the simulation process. Takes input to decide how many iterations to do. Performs these iterations.
    When all iterations have completed. The program terminates.
    */
    public void startSimulation()
    {
        System.out.println("Please enter the number of iterations that you want the sim to perform: ");
        try
        {
            numberOfIterations = Integer.parseInt(inputScanner.nextLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Input must be a number! Please try again!");
            startSimulation();
        }
        for (int i = 0; i < numberOfIterations; i++)
        {
            zooSimLinkedTo.aMonthPasses();
            System.out.println("Iteration complete! Press 'enter' to continue!");
            inputScanner.next();
        }
        System.out.println("\n###SIMULATION COMPLETE###\nEXITING NOW!!");
        System.exit(0);
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
