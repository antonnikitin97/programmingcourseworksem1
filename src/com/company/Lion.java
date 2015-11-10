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
        this.type = "Lion";
    }

    private void stroked()
    {
        this.addHealth(2);
        System.out.println("Lion has been stroked!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default") && this.checkIfHealthCanBeAdded(2)) {
            this.stroked();
        }else{
            System.out.println("Health is max OR this keeper can't treat this animal!");
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
