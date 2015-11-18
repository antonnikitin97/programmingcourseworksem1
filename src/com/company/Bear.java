package com.company;

public class Bear extends Animal
{
    /*
    Constructor a 'bear' animal. Calls the Animal constructor and passes it values such as
    what it eats, the age of the animal, life expectancy, health and gender.
    */
    public Bear(Integer age, Integer health, char gender)
    {
        super(age, 18, health, new String []{"fish", "steak"}, gender);
        this.type = "Bear";
    }

    private void hug()
    {
        this.addHealth(3);
        System.out.format("%s has been hugged!\nHealth is now: %s", this.type ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default") && this.checkIfHealthCanBeAdded(3)) {
            this.hug();
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
