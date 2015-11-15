package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    protected HashMap<Animal, Integer> enclosureForAnimal = new HashMap<>();
    protected ArrayList<Enclosure> tempEnclosureList = new ArrayList<>();
    protected Integer enclosureID = 0;

    private String configFilePath = "";

    public ConfigFile()
    {
        mySim = new Simulation();
    }

    public void getDirectoryOfFile()
    {
        //System.out.print("Please enter the path of your file: ");
        configFilePath = "C:\\Users\\anton\\Desktop\\config.txt";
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
        extractAnimals(animalConfig);
        extractEnclosure(enclosureConfig, enclosureForAnimal);
        //extractKeepers(zookeeperConfig);
    }

    public void extractEnclosure(ArrayList<String> enclosureConfig, HashMap<Animal, Integer> enclosureForAnimal)
    {
        tempEnclosureList.add(new Enclosure());

        for (String s : enclosureConfig)
        {
            String[] enclosureInfo = s.split(" ");
            switch (enclosureInfo[0])
            {
                case "Waste":
                    tempEnclosureList.get(enclosureID).addWaste(Integer.parseInt(enclosureInfo[1]));
                    continue;
            }
            if(enclosureInfo[0].equals("ice"))
            {
                enclosureInfo[0] = "ice cream";
                enclosureInfo[1] = enclosureInfo[2];
            }
            tempEnclosureList.get(0).getFoodStore().addFood(enclosureInfo[0], Integer.parseInt(enclosureInfo[1]));
        }
        System.out.println("Enclosure initialised!");
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
            switch (animalInfo[0])
            {
                case "Lion":
                    Animal tempA = new Lion(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempA, Integer.parseInt(animalInfo[4]));
                    break;
                case "Tiger":
                    Animal tempB = new Tiger(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempB, Integer.parseInt(animalInfo[4]));
                    break;
                case "Elephant":
                    Animal tempC = new Elephant(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempC, Integer.parseInt(animalInfo[4]));
                    break;
                case "Giraffe":
                    Animal tempD = new Giraffe(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempD, Integer.parseInt(animalInfo[4]));
                    break;
                case "Penguin":
                    Animal tempE = new Penguin(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempE, Integer.parseInt(animalInfo[4]));
                    break;
                case "Chimpanzee":
                    Animal tempF = new Chimpanzee(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempF, Integer.parseInt(animalInfo[4]));
                    break;
                case "Bear":
                    Animal tempG = new Lion(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempG, Integer.parseInt(animalInfo[4]));
                    break;
                case "Gorilla":
                    Animal tempH = new Gorilla(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    enclosureForAnimal.put(tempH, Integer.parseInt(animalInfo[4]));
                    break;
            }
        }

        System.out.println("Animals initialised!");
    }

    public void extractKeepers(ArrayList<String> zookeeperConfig)
    {

    }
}
