package com.company;

public class Gorilla extends Ape
{
    public Gorilla(Integer lifeExpectancy, Integer age, Integer health, char gender)
    {
        super(lifeExpectancy, age, health, gender);
        this.type = "Gorilla";
    }

    private void painting()
    {
        this.addHealth(4);
        System.out.format("%s has painted!\nHealth is now: %s", this.type ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(4)) {
            this.painting();
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
