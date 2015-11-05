package com.company;

public class Penguin extends Animal
{
    public Penguin()
    {
        super();
        this.lifeExpectancy = 15;
        eats = new String []{"fish", "ice cream"};
    }

    private void watchAFilm()
    {
        this.health += 2;
        System.out.println("A film has been watched with the penguin!");
    }

    @Override
    public void treat()
    {
        if(this.health > 8) {
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