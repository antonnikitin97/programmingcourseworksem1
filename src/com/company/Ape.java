package com.company;

public abstract class Ape extends Animal
{
    /**
    Constructor an 'ape' animal. Calls the Animal constructor and passes it values such as
    what it eats, the age of the animal, health and gender.
    */
    public Ape(Integer age, Integer health, char gender, Integer lifeExpectancy)
    {
        super(age, lifeExpectancy, health, new String []{"fruit", "ice cream"}, gender );
    }
}
