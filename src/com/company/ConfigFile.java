package com.company;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ConfigFile
{
    protected Scanner inputScanner = new Scanner(System.in);
    protected Scanner fileScanner;
    protected File configFile;
    private BufferedInputStream inputStream;
    protected DataInputStream dataInputStream;

    private String configFilePath = "";

    public void getDirectoryOfFile()
    {
        System.out.print("Please enter the path of your file: ");
        configFilePath = inputScanner.nextLine();
        configFile = new File(configFilePath);
    }

    public void readConfig()
    {
        try
        {
            fileScanner = new Scanner(configFile);

            switch(fileScanner.nextLine())
            {

            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void extractEnclosure()
    {

    }

}
