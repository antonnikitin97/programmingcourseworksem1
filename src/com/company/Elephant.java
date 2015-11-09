package com.company;

public class Elephant extends Animal
{
    public Elephant()
    {
        super(18, new String []{"hay", "fruit"});
    }

    private void bath()
    {
        this.addHealth(5);
        System.out.println("Elephant has been bathed!");
    }

    @Override
    public void treat()
    {
        if(this.getHealth() > 6) {
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
