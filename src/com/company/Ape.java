package com.company;

public abstract class Ape extends Animal
{
    public Ape(Integer age, Integer health, char gender)
    {
        super(age, 32, health, new String []{"fruit", "ice cream"}, gender );
    }
}
