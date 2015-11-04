package com.company;

public class Tiger extends BigCat
{
    public Tiger()
    {
        super();
    }

    private void stroked()
    {
        this.health += 3;
    }

    @Override
    public void treat()
    {
        if(this.health == 8) {
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
