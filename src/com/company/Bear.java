package com.company;

public class Bear extends Animal
{
    public Bear()
    {
        super(18, new String []{"fish", "steak"});
        this.type = "Bear";
    }

    private void hug()
    {
        this.addHealth(3);
        System.out.println("Bear has been hugged!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default") && this.checkIfHealthCanBeAdded(3)) {
            this.hug();
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
