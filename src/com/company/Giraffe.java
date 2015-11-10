package com.company;

public class Giraffe extends Animal
{
    public Giraffe()
    {
        super(28, new String []{"hay", "fruit"});
    }

    private void neckMassage() // This method will 'treat' the giraffe and give it health
    {
        this.addHealth(4);
        System.out.println("Giraffe has had neck massaged! Health increased by 4");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(this.getHealth() > 6) {
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
