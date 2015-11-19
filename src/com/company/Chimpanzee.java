/*
Class for 'Chimpanzee' animal. Contains method for the specific treat for the chimpanzee, and the 'aMonthPasses'
*/
package com.company;

public class Chimpanzee extends Ape
{
    /*
    Constructor an 'Chimpanzee' animal. Calls the Animal constructor and passes it values such as
    what it eats, the age of the animal, life expectancy, health and gender.
    */
    public Chimpanzee(Integer age, Integer health, char gender)
    {
        super(age, health, gender);
        this.setType("Chimpanzee");
    }

    private void playChase()
    {
        this.addHealth(4);
        System.out.format("Chase has been played with %s!\nHealth is now: %s", this.getType() ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(4)) {
            this.playChase();
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
