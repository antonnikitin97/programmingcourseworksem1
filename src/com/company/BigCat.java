package com.company;

public abstract class BigCat extends Animal
{
    public BigCat(Integer age, Integer health, char gender)
    {
        super(age, 24, health, new String []{"steak", "celery"}, gender);
    }
}
