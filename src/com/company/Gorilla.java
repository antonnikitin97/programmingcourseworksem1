package com.company;

public class Gorilla extends Ape
{
    public Gorilla()
    {
        super(32);
        this.type = "Gorilla";
    }

    private void painting()
    {
        this.addHealth(4);
        System.out.println("Gorilla has painted!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(4)) {
            this.painting();
        }else{
            System.out.println("Health is max OR this keeper can't treat this animal!");
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}
