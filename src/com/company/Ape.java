package com.company;

public abstract class Ape extends Animal
{
    /*
    Constructor an 'ape' animal. Calls the Animal constructor and passes it values such as
    what it eats, the age of the animal, health and gender.
    */
    public Ape(Integer age, Integer health, char gender)
    {
        super(age, 32, health, new String []{"fruit", "ice cream"}, gender );
    }
}
