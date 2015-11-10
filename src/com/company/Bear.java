package com.company;

public class Bear extends Animal
{
    public Bear()
    {
        super(18, new String []{"fish", "steak"});
    }

    private void hug()
    {
        this.addHealth(3);
        System.out.println("Bear has been hugged!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default"));
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}
