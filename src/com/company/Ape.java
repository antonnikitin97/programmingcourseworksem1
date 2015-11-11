package com.company;

public abstract class Ape extends Animal
{
    public Ape(Integer lifeExpectancy)
    {
        super(lifeExpectancy, new String []{"fruit", "ice cream"});
    }
}
