package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigFile
{
    protected Scanner inputScanner = new Scanner(System.in);
    protected File configFile;
    protected Simulation mySim;
    protected ArrayList<String> zooConfig = new ArrayList<>();
    protected ArrayList<String> enclosureConfig = new ArrayList<>();
    protected ArrayList<String> animalConfig = new ArrayList<>();
    protected ArrayList<String> zookeeperConfig = new ArrayList<>();

    private String configFilePath = "";

    public ConfigFile()
    {
        mySim = new Simulation();
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
                enclosureConfig.add(line);
            }
            enclosureConfig.remove("Animals:");
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
        finally
        {
            try
            {
                bufferedReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            extractData();
        }
    }

    public void extractData()
    {
        setUpZoo(zooConfig);
        //extractEnclosure(enclosureConfig);
        //extractAnimals(animalConfig);
        //extractKeepers(zookeeperConfig);
    }

    public void extractEnclosure(ArrayList<String> enclsoureConfig)
    {

    }

    public void setUpZoo(ArrayList<String> zooAndFoodConfig)
    {
        FoodStore tempZooStore = new FoodStore();

        for(String s : zooAndFoodConfig)
        {
            String[] foodAndAmount = s.split(" ");
            if(foodAndAmount[0].equals("ice"))
            {
                foodAndAmount[0] = "ice cream";
                foodAndAmount[1] = foodAndAmount[2];
            }
            tempZooStore.addFood(foodAndAmount[0], Integer.parseInt(foodAndAmount[1]));
        }
        mySim.setZooSimLinkedTo(new Zoo(this.mySim, tempZooStore));
        System.out.println("Zoo initialised!");
    }

    public void extractAnimals(ArrayList<String> animalConfig)
    {


        for(String s : animalConfig)
        {
            String [] animalInfo = s.split(" ");

        }
    }

    public void extractKeepers(ArrayList<String> zookeeperConfig)
    {

    }
}
