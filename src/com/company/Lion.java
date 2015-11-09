/*

*/

package com.company;

public class Lion extends BigCat
{
    // Constructor for the lion object, in it we are calling the constructor of the parent class
    // This is because the parent class' constructor initializes default values for 'eats' and
    // lifeExpectancy for 'BigCats'
    public Lion()
    {
        super();
    }

    private void stroked()
    {
        this.addHealth(2);
        System.out.println("Lion has been stroked!");
    }

    @Override
    public void treat()
    {
        if(this.getHealth() > 8) {
            System.out.println("Cannot be treated at this time! (Health is max!)");
        }else{
            stroked();
        }
    }

    @Override
    public Boolean aMonthPasses()
    {
        eat();
        this.monthlyHealthDecrease();
        return true;
    }
}
