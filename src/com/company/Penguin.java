package com.company;

public class Penguin extends Animal
{
    public Penguin()
    {
        super(15, new String []{"fish", "ice cream"});
    }

    private void watchAFilm()
    {
        this.addHealth(2);
        System.out.println("A film has been watched with the penguin!");
    }

    @Override
    public void treat()
    {
        if(this.getHealth() > 8) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            watchAFilm();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}