/**
Class for 'Gorilla' animal. Contains method for the specific treat for the gorilla, and the 'aMonthPasses'
*/
package com.company;

public class Gorilla extends Ape
{
    /**
    Constructor for the Gorilla object, in it we are calling the constructor of the parent class
    This is because the parent class' constructor initializes values for 'eats' and
    lifeExpectancy for 'gorilla'.
    */
    public Gorilla(Integer age, Integer health, char gender)
    {
        super(age, health, gender, 32);
        this.setType("Gorilla");
    }
    /**
    The specific treat for Gorilla, called by the overridden 'treat' method.
    */
    private void painting()
    {
        this.addHealth(4);
        System.out.format("%s has painted!\nHealth is now: %s", this.getType() ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(4)) {
            this.painting();
        }else{
            if(!keeperLabel.equals("play")) {
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
