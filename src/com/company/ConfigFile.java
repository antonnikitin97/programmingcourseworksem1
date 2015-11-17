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
    protected ArrayList<ZooKeeper> tempKeeperList = new ArrayList<>();
    Integer numberOfEnclosuresToCreate = 0;

    private String configFilePath = "";

    public ConfigFile()
    {
        mySim = new Simulation();
    }

    public void getDirectoryOfFile(String configFilePath)
    {
        configFile = new File(configFilePath);
        readConfig();
    }

    public void getDirectoryOfFile()
    {
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
            while(!(line = bufferedReader.readLine()).contains("NewEnclosure"))
            {
                zooConfig.add(line);
            }
            switch(line.split(" ")[1])
            {
                case "0":
                    while(!(line = bufferedReader.readLine()).equals("Animals:"))
                    {
                        zooConfig.add(line);
                    }
                    break;
                default:
                    numberOfEnclosuresToCreate = Integer.parseInt(line.split(" ")[1]);
                    while(!(line = bufferedReader.readLine()).equals("Animals:"))
                    {
                        enclosureConfig.add(line);
                    }
                    break;
            }
            enclosureConfig.remove("NewEnclosure " + numberOfEnclosuresToCreate);
            zooConfig.remove("zoo:");
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
        {extractData();
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
        initializeAnimals(animalConfig);
        initializeEnclosure(enclosureConfig, enclosureForAnimal, numberOfEnclosuresToCreate);
        initializeKeepers(zookeeperConfig);
        mySim.startSimulation();
    }

    /*
    This method creates the enclosures and populates them with the animals that have been read in from the config file.
    If no enclosures have been specified in the config file, then it will create as many enclosures as there are animals with default values for food.
    */
    public void initializeEnclosure(ArrayList<String> enclosureConfig, HashMap<Animal, Integer> enclosureForAnimal, Integer numberOfEnclosuresToCreate)
    {
        ArrayList<Enclosure> tempEnclosureList = new ArrayList<>();
        if(enclosureConfig.size() == 0) {
            System.out.println("No Enclosures configured in file! Resulting food will go to the zoo!");
            for(int i = 0; i <= numberOfEnclosuresToCreate; i ++)
            {
                tempEnclosureList.add(new Enclosure());
            }
            mySim.getZooSimLinkedTo().enclosures = tempEnclosureList;
            for(Animal a : enclosureForAnimal.keySet())
            {
                mySim.getZooSimLinkedTo().enclosures.get(enclosureForAnimal.get(a)).addAnimal(a);
            }
        }else{
            for(int i = 0; i < numberOfEnclosuresToCreate; i++)
            {
                tempEnclosureList.add(new Enclosure());
            }
            Integer counter = 0;
            for(Enclosure e : tempEnclosureList)
            {
                for (String s : enclosureConfig)
                {
                    String[] enclosureInfo = s.split(" ");
                    switch (enclosureInfo[0])
                    {
                        case "Waste":
                            tempEnclosureList.get(counter).addWaste(Integer.parseInt(enclosureInfo[1]));
                            continue;
                    }
                    if(enclosureInfo[0].equals("ice_cream"))
                    {
                        enclosureInfo[0] = "ice cream";
                    }
                    tempEnclosureList.get(counter).getFoodStore().addFood(enclosureInfo[0], Integer.parseInt(enclosureInfo[1]));
                }
                counter += 1;
            }
            mySim.getZooSimLinkedTo().enclosures = tempEnclosureList;
            for(Animal a : enclosureForAnimal.keySet())
            {
                mySim.getZooSimLinkedTo().enclosures.get(enclosureForAnimal.get(a)).addAnimal(a);
            }
        }
        System.out.println("Enclosure initialised!");
    }

    public void setUpZoo(ArrayList<String> zooAndFoodConfig)
    {
        FoodStore tempZooStore = new FoodStore();
        for(String s : zooAndFoodConfig)
        {
            String[] foodAndAmount = s.split(" ");
            if(foodAndAmount[0].equals("ice_cream"))
            {
                foodAndAmount[0] = "ice cream";
            }
            tempZooStore.addFood(foodAndAmount[0], Integer.parseInt(foodAndAmount[1]));
        }
        mySim.setZooSimLinkedTo(new Zoo(this.mySim, tempZooStore));
        System.out.println("Zoo initialised!");
    }

    public void initializeAnimals(ArrayList<String> animalConfig)
    {
        for(String s : animalConfig)
        {
            String [] animalInfo = s.split(" ");
            if(Integer.parseInt(animalInfo[4]) > numberOfEnclosuresToCreate)
            {
                numberOfEnclosuresToCreate = Integer.parseInt(animalInfo[4]);
            }
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

    public void initializeKeepers(ArrayList<String> zookeeperConfig)
    {
        for(String s : zookeeperConfig)
        {
            switch (s)
            {
                case "Default":
                    tempKeeperList.add(new ZooKeeper(mySim.getZooSimLinkedTo(), "default"));
                    break;
                case "Physio":
                    tempKeeperList.add(new PhysioZooKeeper(mySim.getZooSimLinkedTo()));
                    break;
                case "Play":
                    tempKeeperList.add(new PlayZooKeeper(mySim.getZooSimLinkedTo()));
                    break;
            }
        }
        while(tempKeeperList.size() < mySim.getZooSimLinkedTo().enclosures.size())
        {
            tempKeeperList.add(new ZooKeeper(mySim.getZooSimLinkedTo(), "default"));
            tempKeeperList.add(new PhysioZooKeeper(mySim.getZooSimLinkedTo()));
            tempKeeperList.add(new PlayZooKeeper(mySim.getZooSimLinkedTo()));
        }
        mySim.getZooSimLinkedTo().zooKeepers = tempKeeperList;
        System.out.println("Zookeepers Initialized!");
    }
}
