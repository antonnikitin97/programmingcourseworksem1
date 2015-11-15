package com.company;

public abstract class Ape extends Animal
{
    public Ape(Integer lifeExpectancy, Integer age, Integer health, char gender)
    {
        super(age, lifeExpectancy, health, new String []{"fruit", "ice cream"}, gender );
    }
}
