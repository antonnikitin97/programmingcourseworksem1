package com.company;

public class Gorilla extends Ape
{
    public Gorilla()
    {
        super(32);
    }

    private void painting()
    {
        this.addHealth(4);
        System.out.println("Gorilla has painted!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(this.getHealth() > 6) {
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
