package com.company;

public class Gorilla extends Ape
{
    public Gorilla()
    {
        super();
        this.lifeExpectancy = 32;
    }

    private void painting()
    {
        this.health += 4;
        System.out.println("Gorilla has painted!");
    }

    @Override
    public void treat()
    {
        if(this.health > 6) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            painting();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}
