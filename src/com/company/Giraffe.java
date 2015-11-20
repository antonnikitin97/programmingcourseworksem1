/*
Class for 'Giraffe' animal. Contains method for the specific treat for the giraffe, and the 'aMonthPasses'
*/
package com.company;

public class Giraffe extends Animal
{
    /*
    Constructor for the Gorilla object, in it we are calling the constructor of the parent class
    This is because the parent class' constructor initializes values for 'eats' and
    lifeExpectancy for 'gorilla'.
    */
    public Giraffe(Integer age, Integer health, char gender)
    {
        super(age, 28, health, new String []{"hay", "fruit"}, gender);
        this.setType("Giraffe");
    }
    /*
    The specific treat for Giraffe, called by the overridden 'treat' method.
    */
    private void neckMassage()
    {
        this.addHealth(4);
        System.out.format("%s has been massaged!\nHealth is now: %s", this.getType() ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("physio") && this.checkIfHealthCanBeAdded(4)) {
            this.neckMassage();
        }else{
            if(!keeperLabel.equals("physio")) {
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
