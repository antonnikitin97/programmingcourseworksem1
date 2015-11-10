package com.company;

public class Tiger extends BigCat
{
    public Tiger()
    {
        super();
    }

    private void stroked()
    {
        this.addHealth(3);
        System.out.println("Tiger has been stroked!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(this.getHealth() > 7) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            stroked();
        }
    }


    @Override
    public Boolean aMonthPasses()
    {
        eat();
        return true;
    }
}
