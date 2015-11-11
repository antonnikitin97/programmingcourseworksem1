package com.company;

public class Chimpanzee extends Ape
{
    public Chimpanzee()
    {
        super(32);
        this.type = "Chimpanzee";
    }

    private void playChase()
    {
        this.addHealth(4);
        System.out.println("Chase has been played with Chimpanzee!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(4)) {
            this.playChase();
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
