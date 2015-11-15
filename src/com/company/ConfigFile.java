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
        extractAnimals(animalConfig);
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
        ArrayList<Animal> temp = new ArrayList<>();

        for(String s : animalConfig)
        {
            String [] animalInfo = s.split(" ");
            switch (animalInfo[0])
            {
                case "Lion":
                    Animal tempA = new Lion(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempA);
                    enclosureForAnimal.put(tempA, Integer.parseInt(animalInfo[4]));
                    break;
                case "Tiger":
                    Animal tempB = new Tiger(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempB);
                    enclosureForAnimal.put(tempB, Integer.parseInt(animalInfo[4]));
                    break;
                case "Elephant":
                    Animal tempC = new Elephant(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempC);
                    enclosureForAnimal.put(tempC, Integer.parseInt(animalInfo[4]));
                    break;
                case "Giraffe":
                    Animal tempD = new Giraffe(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempD);
                    enclosureForAnimal.put(tempD, Integer.parseInt(animalInfo[4]));
                    break;
                case "Penguin":
                    Animal tempE = new Penguin(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempE);
                    enclosureForAnimal.put(tempE, Integer.parseInt(animalInfo[4]));
                    break;
                case "Chimpanzee":
                    Animal tempF = new Chimpanzee(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempF);
                    enclosureForAnimal.put(tempF, Integer.parseInt(animalInfo[4]));
                    break;
                case "Bear":
                    Animal tempG = new Lion(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempG);
                    enclosureForAnimal.put(tempG, Integer.parseInt(animalInfo[4]));
                    break;
                case "Gorilla":
                    Animal tempH = new Gorilla(Integer.parseInt(animalInfo[2]), Integer.parseInt(animalInfo[3]), animalInfo[1].charAt(0));
                    temp.add(tempH);
                    enclosureForAnimal.put(tempH, Integer.parseInt(animalInfo[4]));
                    break;
            }
        }
    }

    public void extractKeepers(ArrayList<String> zookeeperConfig)
    {

    }
}
