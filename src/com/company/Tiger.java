/*
Class for 'Tiger' animal. Contains method for the specific treat for the tiger, and the 'aMonthPasses'
*/

package com.company;

public class Tiger extends BigCat
{
    /*
    Constructor for the tiger object, in it we are calling the constructor of the parent class
    This is because the parent class' constructor initializes values for 'eats' and
    lifeExpectancy for 'tiger'.
    */
    public Tiger(Integer age, Integer health, char gender)
    {
        super(age, health, gender);
        this.setType("Tiger");
    }
    /*
    The specific treat for Tiger, called by the overridden 'treat' method.
    */
    private void stroked()
    {
        this.addHealth(3);
        System.out.format("%s has been stroked!\nHealth is now: %s", this.getType() ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default") && this.checkIfHealthCanBeAdded(3)) {
            this.stroked();
        }else{
            if(!keeperLabel.equals("default")) {
                System.out.format("This keeper (%s) cannot treat this animal! (%s)", keeperLabel, this.getType());
            }else{
                System.out.format("Health of %s max! Cannot treat at this time!", this.getType());
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
