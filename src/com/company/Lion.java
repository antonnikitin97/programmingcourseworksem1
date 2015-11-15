/*

*/

package com.company;

public class Lion extends BigCat
{
    // Constructor for the lion object, in it we are calling the constructor of the parent class
    // This is because the parent class' constructor initializes default values for 'eats' and
    // lifeExpectancy for 'BigCats'
    public Lion(Integer lifeExpectancy, Integer age, Integer health, char gender)
    {
        super(lifeExpectancy, age, health, gender);
        this.type = "Lion";
    }

    private void stroked()
    {
        this.addHealth(2);
        System.out.format("%s has been stroked!\nHealth is now: %s", this.type ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default") && this.checkIfHealthCanBeAdded(2)) {
            this.stroked();
        }else{
            if(!keeperLabel.equals("default")) {
                System.out.format("This keeper (%s) cannot treat this animal! (%s)", keeperLabel, this.type);
            }else{
                System.out.format("Health of %s max! Cannot treat at this time!", this.type);
            }
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
