package com.company;

public class Chimpanzee extends Ape
{
    public Chimpanzee()
    {
        super();
        this.lifeExpectancy = 32;
    }

    private void playChase()
    {
        this.health += 4;
        System.out.println("Chase has been played with Chimpanzee!");
    }

    @Override
    public void treat()
    {
        if(this.health > 6) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            playChase();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        return true;
    }
}
