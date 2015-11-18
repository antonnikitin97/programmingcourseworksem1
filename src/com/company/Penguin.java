package com.company;

public class Penguin extends Animal
{
    /*
    Constructor for the penguin object, in it we are calling the constructor of the parent class
    This is because the parent class' constructor initializes values for 'eats' and
    lifeExpectancy for 'Penguin'.
    */
    public Penguin(Integer age, Integer health, char gender)
    {
        super(age, 15, health, new String []{"fish", "ice cream"}, gender);
        this.type = "Penguin";
    }

    private void watchAFilm()
    {
        this.addHealth(2);
        System.out.format("%s has watched a film!\nHealth is now: %s", this.type ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(2)) {
            this.watchAFilm();
        }else{
            if(!keeperLabel.equals("play")) {
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