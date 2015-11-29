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
    Boolean continuous = false;

    /*
    Method to start the process of reading the config file for the zoo, and the setting of the continuous variable.
    */
    public void initialize(String pathOfFile, String continuousCondition)
    {
        switch (continuousCondition)
        {
            case "true":
                continuous = true;
                break;
            case "false":
                continuous = false;
                break;
            default:
                //If the user enters anything other than true/false. It will default to false.
                continuous = false;
                break;
        }
        //Creates a new config file and passes itself (the simulation) as a parameter so the configfile class can access simulation methods etc...
        ConfigFile file = new ConfigFile(this);
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
            //Attempts to take input from the user, throws an exception if NaN.
            numberOfIterations = Integer.parseInt(inputScanner.nextLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Input must be a number! Please try again!");
            startSimulation();
        }
        if(continuous) {
            for (int i = 0; i <= numberOfIterations; i++) {
                //If continuous is set to true the simulation will just run uninterrupted until the sim ends.
                zooSimLinkedTo.aMonthPasses();
            }
        }else{
            for (int i = 0; i <= numberOfIterations; i++) {
                //If continuous is false, then the sim will pause after each iteration.
                zooSimLinkedTo.aMonthPasses();
                System.out.println("Iteration complete! Press 'enter' to continue!");
                inputScanner.nextLine();
            }
        }
        System.out.println("\n###SIMULATION COMPLETE###\nEXITING NOW!!");
        exit();
    }

    public void incrementMonth()
    {
        monthPassed += 1;
    }

    public Integer getMonthsPassed()
    {
        return monthPassed;
    }

    /*
    Sets the zoo object reference to the simulation's variable so that the simulation class can access methods/properties
    of the zoo necessary
    */
    public void setZooSimLinkedTo(Zoo zoo)
    {
        this.zooSimLinkedTo = zoo;
    }

    /*
    Returns the zoo that the sim has linked to it.
    */
    public Zoo getZooSimLinkedTo()
    {
        return zooSimLinkedTo;
    }

    public void exit()
    {
        System.exit(0);
    }
}
