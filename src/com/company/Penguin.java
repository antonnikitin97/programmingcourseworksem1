package com.company;

public class Penguin extends Animal
{
    public Penguin()
    {
        super(15, new String []{"fish", "ice cream"});
        this.type = "Penguin";
    }

    private void watchAFilm()
    {
        this.addHealth(2);
        System.out.println("A film has been watched with the penguin!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(2)) {
            this.watchAFilm();
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