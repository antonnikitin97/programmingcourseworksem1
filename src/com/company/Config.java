/**
This class is responsible for loading and parsing the configuration file, it contains methods to read the lines in the file and extract the data,
and call the appropriate methods to operate on the data.
*/

package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Config {
    private Scanner inputScanner = new Scanner(System.in);
    private File configFile;
    private String configFilePath = "";

    /**
     * The 4 Array lists declared below hold the information from the config file about the zoo's food (zooConfig), the enclosure's
     * waste and food (enclosureConfig). The animals in the enclosure (animalConfig) and the zoo keepers (zooKeeperConfig)
     */
    private ArrayList<String> zooConfig = new ArrayList<>();
    private ArrayList<String> enclosureConfig = new ArrayList<>();
    private ArrayList<String> animalConfig = new ArrayList<>();
    private ArrayList<String> zookeeperConfig = new ArrayList<>();

    Integer numberOfEnclosuresToCreate = 0;

    /**
     * Method for getting directory of file using values passed into the command prompt as arugamnts
     * An overloaded version is provided
     * @param configFilePath
     */
    public void getDirectoryOfFile(String configFilePath) {
        configFile = new File(configFilePath);
        if(!configFile.exists() || !configFile.isDirectory()){
            //Checks to see if config file exists, if not prompt for new file
            getDirectoryOfFile();
        }else{
            readConfig();
        }

    }

    public void getDirectoryOfFile() {
        boolean continueInput = false;
        do{
            System.out.println("We could not find that file! Please enter a new path: ");
            configFilePath = inputScanner.nextLine();
            configFile = new File(configFilePath);
            continueInput = !configFile.exists() ||configFile.isDirectory();
            if(continueInput){
                System.out.println("We cannot find the required file! Please try again!");
            }
        }while (continueInput);
        readConfig();
    }

    /**
     * This method is responsible for reading the whole configuration file line by line and populating the arrays with data for each section of the config file.
     */
    public void readConfig() {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(configFile));

            String line = "";
            //Reads the file until it gets to the end of the 'zoo' section, adds the data to the zooConfig Array
            while (!(line = bufferedReader.readLine()).contains("NewEnclosure")) {
                zooConfig.add(line);
            }
            switch (line.split(" ")[1]) {
                //Splits the line that contains the instruction for a new enclosure into an array containing two elements
                case "0":
                    while (!(line = bufferedReader.readLine()).equals("Animals:")) {
                        zooConfig.add(line);
                    }
                    break;
                default:
                    numberOfEnclosuresToCreate = Integer.parseInt(line.split(" ")[1]);
                    while (!(line = bufferedReader.readLine()).equals("Animals:")) {
                        enclosureConfig.add(line);
                    }
                    break;
            }
            enclosureConfig.remove("NewEnclosure " + numberOfEnclosuresToCreate);
            zooConfig.remove("zoo:");
            //Reads the file until it gets to the end of the 'animals' section, adds the data to the animalConfig Array.
            while (!(line = bufferedReader.readLine()).equals("ZooKeeper:")) {
                animalConfig.add(line);
            }
            animalConfig.remove("Animals:");
            //Reads the file until it gets to the end of the 'zookeeper' section, adds the data to the zookeeperConfig Array.
            while ((line = bufferedReader.readLine()) != null) {
                zookeeperConfig.add(line);
            }
            zookeeperConfig.remove("ZooKeeper:");
        }
        //Exception that is thrown if the file path entered is incorrect.
        catch (FileNotFoundException e) {
            System.out.println("We cannot find the required file! Please try again!");
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufferedReader.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getZooConfig() {
        return zooConfig;
    }

    public ArrayList<String> getEnclosureConfig() {
        return enclosureConfig;
    }

    public ArrayList<String> getAnimalConfig() {
        return animalConfig;
    }

    public ArrayList<String> getZookeeperConfig() {
        return zookeeperConfig;
    }

    public Integer getNumberOfEnclosuresToCreate() {
        return numberOfEnclosuresToCreate;
    }
}
