package com.company;

public class Giraffe extends Animal
{
    public Giraffe()
    {
        super();
        this.lifeExpectancy = 28;
        eats = new String []{"hay", "fruit"};
    }

    private void neckMassage()
    {
        this.health += 4;
        System.out.println("Giraffe has had neck massaged! Health increased by 4");
    }

    @Override
    public void treat()
    {
        if(this.health > 6) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            neckMassage();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}
