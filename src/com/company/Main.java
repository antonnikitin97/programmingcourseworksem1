/*
The purpose of the main class is to create the simulation object, and call
the initialize method to start the process for reading config file etc..
*/
package com.company;

public class Main
{
    public static void main(String[] args)
    {
        Simulation mySim = new Simulation();
        mySim.initialize(args[0], args[1]);
    }
}