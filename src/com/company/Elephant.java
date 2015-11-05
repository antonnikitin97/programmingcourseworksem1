package com.company;

public class Elephant extends Animal
{
    public Elephant()
    {
        super();
        this.lifeExpectancy = 18;
        eats = new String []{"hay", "fruit"};
    }

    private void bath()
    {
        this.health += 5;
        System.out.println("Elephant has been bathed!");
    }

    @Override
    public void treat()
    {
        if(this.health > 6) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            bath();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}
