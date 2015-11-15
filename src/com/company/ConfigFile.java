package com.company;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Scanner;

public class ConfigFile
{
    protected Scanner inputScanner = new Scanner(System.in);
    protected File configFile;
    protected Simulation mySim;

    private String configFilePath = "";

    public ConfigFile()
    {
        //this.mySim = simToPassIn;
    }

    public void getDirectoryOfFile()
    {
        System.out.print("Please enter the path of your file: ");
        configFilePath = inputScanner.nextLine();
        configFile = new File(configFilePath);
        readConfig();
    }

    public void readConfig()
    {
        BufferedReader reader = null;
        BufferedInputStream inputStream = null;
        DataInputStream dataInputStream = null;
        FileInputStream fileInputStream = null;

        try
        {
            fileInputStream = new FileInputStream(configFile);
            inputStream = new BufferedInputStream(fileInputStream);
            dataInputStream = new DataInputStream(inputStream);

            while(dataInputStream.available() != 0)
            {

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("We cannot find the required file! Please try again!");
            getDirectoryOfFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void extractEnclosure()
    {

    }

    public void extractZooAndFood()
    {

    }

    public void extractAnimals()
    {

    }

    public void extractKeepers()
    {

    }

}
