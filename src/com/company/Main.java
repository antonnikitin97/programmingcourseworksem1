/**
The purpose of the main class is to create the simulation object, and call
the initialize method to start the process for reading config file etc..
*/
package com.company;

public class Main
{
    public static void main(String[] args)
    {
        SimulationInitialize simulationInitialize = new SimulationInitialize();
        simulationInitialize.beginConfigLoading("C:\\Users\\anton\\Desktop\\Zoo\\src\\config.txt");
        simulationInitialize.setUpSimulation("false").startSimulation();
    }
}