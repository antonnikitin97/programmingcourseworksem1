package com.company;

public class Bear extends Animal
{
    public Bear()
    {
        super();
        this.lifeExpectancy = 18;
        eats = new String []{"fish", "steak"};
    }

    private void hug()
    {
        this.health += 3;
        System.out.println("Bear has been hugged!");
    }

    @Override
    public void treat()
    {
        if(this.health > 7) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            hug();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        return true;
    }
}
