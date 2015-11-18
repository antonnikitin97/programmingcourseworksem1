/*
Class for 'Elephant' animal. Contains method for the specific treat for the elephant, and the 'aMonthPasses'
*/
package com.company;

public class Elephant extends Animal
{
    /*
    Constructor an 'Elephant' animal. Calls the Animal constructor and passes it values such as
    what it eats, the age of the animal, life expectancy, health and gender.
    */
    public Elephant(Integer age, Integer health, char gender)
    {
        super(age, 36, health, new String []{"hay", "fruit"}, gender);
        this.type = "Elephant";
    }

    private void bath()
    {
        this.addHealth(5);
        System.out.format("%s has been bathed!\nHealth is now: %s", this.type ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("physio") && this.checkIfHealthCanBeAdded(5)) {
            this.bath();
        }else{
            if(!keeperLabel.equals("physio")) {
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
