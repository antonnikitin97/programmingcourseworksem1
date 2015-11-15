package com.company;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigFile
{
    protected Scanner inputScanner = new Scanner(System.in);
    protected File configFile;
    protected Simulation mySim;
    protected ArrayList<String> zooConfig = new ArrayList<>();
    protected ArrayList<String> enclsoureConfig = new ArrayList<>();
    protected ArrayList<String> animalConfig = new ArrayList<>();
    protected ArrayList<String> zookeeperConfig = new ArrayList<>();

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
        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try
        {
            reader = new FileReader(configFile);
            bufferedReader = new BufferedReader(reader);

            String line = "";
            while(!(line = bufferedReader.readLine()).equals("NewEnclosure:"))
            {
                zooConfig.add(line);
            }
            zooConfig.remove("zoo:");
            while(!(line = bufferedReader.readLine()).equals("Animals:"))
            {
                enclsoureConfig.add(line);
            }
            enclsoureConfig.remove("Animals:");
            while(!(line = bufferedReader.readLine()).equals("ZooKeeper:"))
            {
                animalConfig.add(line);
            }
            animalConfig.remove("Animals:");
            while((line = bufferedReader.readLine()) != null)
            {
                zookeeperConfig.add(line);
            }
            zookeeperConfig.remove("ZooKeeper:");
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

    public void extractEnclosure(ArrayList<String> )
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
