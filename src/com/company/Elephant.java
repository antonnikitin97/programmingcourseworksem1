package com.company;

public class Elephant extends Animal
{
    public Elephant()
    {
        super(18, new String []{"hay", "fruit"});
        this.type = "Elephant";
    }

    private void bath()
    {
        this.addHealth(5);
        System.out.println("Elephant has been bathed!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("physio") && this.checkIfHealthCanBeAdded(5)) {
            this.bath();
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
