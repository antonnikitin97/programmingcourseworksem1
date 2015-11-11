package com.company;

public class Giraffe extends Animal
{
    public Giraffe()
    {
        super(28, new String []{"hay", "fruit"});
        this.type = "Giraffe";
    }

    // This method will 'treat' the giraffe and give it health
    private void neckMassage()
    {
        this.addHealth(4);
        System.out.println("Giraffe has had neck massaged! Health increased by 4");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("physio") && this.checkIfHealthCanBeAdded(4)) {
            this.neckMassage();
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
