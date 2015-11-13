package com.company;

import java.util.Scanner;

/**
 * Created by Anton on 09/11/2015.
 */
public class ConfigFile
{
    protected Scanner inputScanner = new Scanner(System.in);
    private String cofigFilePath = "";

    public void getDirectoryOfFile()
    {
        System.out.print("Please enter the path of your file: ");
        cofigFilePath = inputScanner.nextLine();
    }
}
