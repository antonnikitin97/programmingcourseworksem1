package com.company;

/**
 * Created by Anton on 30/10/2015.
 */
public abstract class Ape extends Animal
{
    public Ape(Integer lifeExpectancy)
    {
        super(lifeExpectancy, new String []{"fruit", "ice cream"});
    }
}
