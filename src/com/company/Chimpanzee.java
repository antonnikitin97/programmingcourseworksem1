package com.company;

public class Chimpanzee extends Ape
{
    public Chimpanzee()
    {
        super(32);
    }

    private void playChase()
    {
        this.addHealth(4);
        System.out.println("Chase has been played with Chimpanzee!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(this.getHealth() > 6) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            playChase();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}
