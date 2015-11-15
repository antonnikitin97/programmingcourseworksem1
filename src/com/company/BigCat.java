package com.company;

public abstract class BigCat extends Animal
{
    public BigCat(Integer lifeExpectancy, Integer age, Integer health, char gender)
    {
        super(age, lifeExpectancy, health, new String []{"steak", "celery"}, gender);
    }
}
