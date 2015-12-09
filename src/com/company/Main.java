/**
The purpose of the main class is to create the simulation object, and call
the initialize method to start the process for reading config file etc..
*/
package com.company;

public class Main
{
    public static void main(String[] args) {
        SimulationInitialize simulationInitialize = new SimulationInitialize();
        String[] argumentsToPass = simulationInitialize.verifyArguments(args);
        simulationInitialize.beginConfigLoading(argumentsToPass[0]);
        simulationInitialize.setUpSimulation(argumentsToPass[1]).startSimulation();
    }
}