package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class responsible for taking the data from the configurationFile and initializing the zoo.
 */
public class SimulationInitialize {
    /**
     * The hashmap and integer variable declared below are merely used as intermediate storage for other methods.
     * The enclosureForAnimal hashmap is used for the enclosureConfig() method to assign animals to enclosure (the Key is the animal object
     * the value is the enclosure for that animal). The integer variable is used by the enclosureConfig() method in the event of no enclosures being specified
     * in the config file, so that it can create empty enclosures for the animals.
     */
    protected static HashMap<Animal, Integer> enclosureForAnimal = new HashMap<>();
    static Integer numberOfEnclosuresToCreateNoneSpecified = 0;

    protected static Simulation mySim;
    protected static Config file;

    public SimulationInitialize() {
        mySim = new Simulation();
    }

    /**
     * Begins the process of parsing the config file.
     * @param pathOfFile
     */
    protected static void beginConfigLoading(String pathOfFile) {
        file = new Config();
        file.getDirectoryOfFile(pathOfFile);
    }

    /**
     * This method calls the methods to set up the zoo, (initializing animals, foodstore, enclosure, zookeepers)
     * It also calls the startSimulation method after all of these have completed.
     */
    public static Simulation setUpSimulation(String continuousCondition) {
        System.out.println("### BEGIN CONFIG FILE LOAD ###");
        setUpZoo(file.getZooConfig());
        initializeAnimals(file.getAnimalConfig());
        initializeEnclosure(file.getEnclosureConfig(), enclosureForAnimal, file.getNumberOfEnclosuresToCreate());
        initializeKeepers(file.getZookeeperConfig());
        System.out.println("\n### DATA LOADED SUCCESSFULLY ###\n");
        mySim.setContinuousCondition(continuousCondition);
        return mySim;
    }


    /**
     * This method creates the enclosures and populates them with the animals that have been read in from the config file.
     * If no enclosures have been specified in the config file, then it will create as many enclosures as there are animals with default values for food.
     */
    public static void initializeEnclosure(ArrayList<String> enclosureConfig, HashMap<Animal, Integer> enclosureForAnimal, Integer numberOfEnclosuresToCreate) {
        ArrayList<Enclosure> tempEnclosureList = new ArrayList<>();
        if (enclosureConfig.size() == 0) {
            System.out.println("No Enclosures configured in file! Resulting food will go to the zoo!");
            //If no enclosures specified in config file, create that many...
            for (int i = 0; i <= numberOfEnclosuresToCreate; i++) {
                tempEnclosureList.add(new Enclosure());
            }
            mySim.getZooSimLinkedTo().enclosures = tempEnclosureList;
            for (Animal a : enclosureForAnimal.keySet()) {
                mySim.getZooSimLinkedTo().enclosures.get(enclosureForAnimal.get(a)).addAnimal(a);
            }
        } else {
            for (int i = 0; i < numberOfEnclosuresToCreate; i++) {
                tempEnclosureList.add(new Enclosure());
            }
            Integer counter = 0;
            for (Enclosure e : tempEnclosureList) {
                for (String s : enclosureConfig) {
                    String[] enclosureInfo = s.split(" ");
                    //Ascertain how much waste has been specified in the config file.
                    switch (enclosureInfo[0]) {
                        case "Waste":
                            tempEnclosureList.get(counter).addWaste(Integer.parseInt(enclosureInfo[1]));
                            continue;
                    }
                    if (enclosureInfo[0].equals("ice_cream")) {
                        enclosureInfo[0] = "ice cream";
                    }
                    tempEnclosureList.get(counter).getFoodStore().addFood(enclosureInfo[0], Integer.parseInt(enclosureInfo[1]));
                }
                counter += 1;
            }
            mySim.getZooSimLinkedTo().enclosures = tempEnclosureList;
            for (Animal a : enclosureForAnimal.keySet()) {
                mySim.getZooSimLinkedTo().enclosures.get(enclosureForAnimal.get(a)).addAnimal(a);
            }
        }
        System.out.println("Enclosure initialised!");
    }

    /**
     * This method is responsible for reading the data from the zooConfig arrayList and creating a foodStore, populating that
     * food store and finally creating the zoo object.
     */
    public static void setUpZoo(ArrayList<String> zooAndFoodConfig) {
        FoodStore tempZooStore = new FoodStore();
        for (String s : zooAndFoodConfig) {
            if (s.contains("Waste")) {
                continue;
            }
            String[] foodAndAmount = s.split(" ");
            if (foodAndAmount[0].equals("ice_cream")) {
                foodAndAmount[0] = "ice cream";
            }
            tempZooStore.addFood(foodAndAmount[0], Integer.parseInt(foodAndAmount[1]));
        }
        mySim.setZooSimLinkedTo(new Zoo(mySim, tempZooStore));
        System.out.println("Zoo initialised!");
    }

    /**
     * This method is responsible for extracting the animal's type, gender, health and enclosure number, and populating the enclosureForAnimal hashmap
     */
    public static void initializeAnimals(ArrayList<String> animalConfig) {
        for (String s : animalConfig) {
            String[] animalInfo = s.split(" ");
            if (Integer.parseInt(animalInfo[4]) > file.getNumberOfEnclosuresToCreate()) {
                numberOfEnclosuresToCreateNoneSpecified = Integer.parseInt(animalInfo[4]);
            }
            if (Integer.parseInt(animalInfo[3]) > 10 || Integer.parseInt(animalInfo[3]) <= 0) {
                animalInfo[3] = "10";
            }
            //Code to ascertain what type of animal to create.
            switch (animalInfo[0]) {
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

    /**
     * This method is responsible for extracting the types of keepers required from zooKeeperConfig and populating a tempKeeperList
     * with these keepers.
     */
    public static void initializeKeepers(ArrayList<String> zookeeperConfig) {
        ArrayList<ZooKeeper> tempKeeperList = new ArrayList<>();
        for (String s : zookeeperConfig) {
            switch (s) {
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
        while (tempKeeperList.size() < mySim.getZooSimLinkedTo().enclosures.size()) {
            tempKeeperList.add(new ZooKeeper(mySim.getZooSimLinkedTo(), "default"));
            tempKeeperList.add(new PhysioZooKeeper(mySim.getZooSimLinkedTo()));
            tempKeeperList.add(new PlayZooKeeper(mySim.getZooSimLinkedTo()));
        }
        mySim.getZooSimLinkedTo().zooKeepers = tempKeeperList;
        System.out.println("Zookeepers Initialized!");
    }

    /**
     * Verifies whether the argument array has any parameters in it, if not initialize default with path: "config.txt"
     * and continuousCondition False
     * @param arrayToCheck
     * @return
     */
    public static String[] verifyArguments(String[] arrayToCheck){
        if(arrayToCheck.length == 0 || arrayToCheck.length == 1) {
            return new String[]{"", "false"};
        }else{
            return arrayToCheck;
        }
    }
}