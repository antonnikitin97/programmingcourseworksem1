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
            System.out.format("Health of %s is max OR this keeper can't treat this animal!", this.type);
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        removeHealth(2);
        return true;
    }
}