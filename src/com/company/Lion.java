/*
Class for 'Lion' animal. Contains method for the specific treat for the lion, and the 'aMonthPasses'
*/

package com.company;

public class Lion extends BigCat
{
    /*
    Constructor for the lion object, in it we are calling the constructor of the parent class
    This is because the parent class' constructor initializes default values for 'eats' and
    lifeExpectancy for 'BigCats'
    */
    public Lion(Integer age, Integer health, char gender)
    {
        super(age, health, gender);
        this.setType("Lion");
    }
    /*
    The specific treat for Lion, called by the overridden 'treat' method.
    */
    private void stroked()
    {
        this.addHealth(2);
        System.out.format("%s has been stroked!\nHealth is now: %s", this.getType() ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(this.checkIfHealthCanBeAdded(2)) {
            this.stroked();
        }else{
                System.out.format("Health of %s max! Cannot treat at this time!", this.getType());
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
