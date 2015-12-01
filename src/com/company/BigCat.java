package com.company;

public abstract class BigCat extends Animal
{
    /**
    Constructor a 'bigcat' animal. Calls the Animal constructor and passes it values such as
    what it eats, the age of the animal, life expectancy, health and gender.
    */
    public BigCat(Integer age, Integer health, char gender)
    {
        super(age, 24, health, new String []{"steak", "celery"}, gender);
    }
}
