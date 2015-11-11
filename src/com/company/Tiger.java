package com.company;

public class Tiger extends BigCat
{
    public Tiger()
    {
        super();
        this.type = "Tiger";
    }

    private void stroked()
    {
        this.addHealth(3);
        System.out.println("Tiger has been stroked!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default") && this.checkIfHealthCanBeAdded(3)) {
            this.stroked();
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
