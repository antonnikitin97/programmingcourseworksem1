/*
This class is responsible for loading and parsing the configuration file, it contains methods to read the lines in the file and extract the data,
and call the appropriate methods to operate on the data.
*/

package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigFile
{
    private Scanner inputScanner = new Scanner(System.in);
    private File configFile;
    private Simulation mySim;
    private String configFilePath = "";

    /*
    The 4 Array lists declared below hold the information from the config file about the zoo's food (zooConfig), the enclosure's
    waste and food (enclosureConfig). The animals in the enclosure (animalConfig) and the zoo keepers (zooKeeperConfig)
    */
    protected ArrayList<String> zooConfig = new ArrayList<>();
    protected ArrayList<String> enclosureConfig = new ArrayList<>();
    protected ArrayList<String> animalConfig = new ArrayList<>();
    protected ArrayList<String> zookeeperConfig = new ArrayList<>();

    /*
    The hashmap and integer variable declared below are merely used as intermediate storage for other methods.
    The enclosureForAnimal hashmap is used for the enclosureConfig() method to assign animals to enclosure (the Key is the animal object
    the value is the enclosure for that animal). The integer variable is used by the enclosureConfig() method in the event of no enclosures being specified
    in the config file, so that it can create empty enclosures for the animals.
    */
    protected HashMap<Animal, Integer> enclosureForAnimal = new HashMap<>();
    Integer numberOfEnclosuresToCreate = 0;

    public ConfigFile(Simulation mySimToSet)
    {
        mySim = mySimToSet;
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

    /*
    This method is responsible for reading the whole configuration file line by line and populating the arrays with data for each section of the config file.
    */
    public void readConfig()
    {
        BufferedReader bufferedReader = null;

        try
        {
            bufferedReader = new BufferedReader(new FileReader(configFile));

            String line = "";
            //Reads the file until it gets to the end of the 'zoo' section, adds the data to the zooConfig Array
            while(!(line = bufferedReader.readLine()).contains("NewEnclosure"))
            {
                zooConfig.add(line);
            }
            switch(line.split(" ")[1])
            {
                //Splits the line that contains the instruction for a new enclosure into an array containing two elements
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
            //Reads the file until it gets to the end of the 'animals' section, adds the data to the animalConfig Array.
            while(!(line = bufferedReader.readLine()).equals("ZooKeeper:"))
            {
                animalConfig.add(line);
            }
            animalConfig.remove("Animals:");
            //Reads the file until it gets to the end of the 'zookeeper' section, adds the data to the zookeeperConfig Array.
            while((line = bufferedReader.readLine()) != null)
            {
                zookeeperConfig.add(line);
            }
            zookeeperConfig.remove("ZooKeeper:");
        }
        //Exception that is thrown if the file path entered is incorrect.
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
            setUpSimulation();
        }
    }

    /*
    This method calls the methods to set up the zoo, (initializing animals, foodstore, enclosure, zookeepers)
    It also calls the startSimulation method after all of these have completed.
    */
    public void setUpSimulation()
    {
        System.out.println("### BEGIN CONFIG FILE LOAD ###");
        setUpZoo(zooConfig);
        initializeAnimals(animalConfig);
        initializeEnclosure(enclosureConfig, enclosureForAnimal, numberOfEnclosuresToCreate);
        initializeKeepers(zookeeperConfig);
        System.out.println("\n### DATA LOADED SUCCESSFULLY ###\n");
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

    /*
    This method is responsible for reading the data from the zooConfig arrayList and creating a foodStore, populating that
    food store and finally creating the zoo object.
    */
    public void setUpZoo(ArrayList<String> zooAndFoodConfig)
    {
        FoodStore tempZooStore = new FoodStore();
        for(String s : zooAndFoodConfig)
        {
            if(s.contains("Waste"))
            {
                continue;
            }
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

    /*
    This method is responsible for extracting the animal's type, gender, health and enclosure number, and populating the enclosureForAnimal hashmap
    */
    public void initializeAnimals(ArrayList<String> animalConfig)
    {
        for(String s : animalConfig)
        {
            String [] animalInfo = s.split(" ");
            if(Integer.parseInt(animalInfo[4]) > numberOfEnclosuresToCreate)
            {
                numberOfEnclosuresToCreate = Integer.parseInt(animalInfo[4]);
            }
            if(Integer.parseInt(animalInfo[3]) > 10 || Integer.parseInt(animalInfo[3]) <= 0)
            {
                animalInfo[3] = "10";
            }
            //Code to ascertain what type of animal to create.
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
                    Animal tempG = new Bear(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
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

    /*
    This method is responsible for extracting the types of keepers required from zooKeeperConfig and populating a tempKeeperList
    with these keepers.
    */
    public void initializeKeepers(ArrayList<String> zookeeperConfig)
    {
        ArrayList<ZooKeeper> tempKeeperList = new ArrayList<>();
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