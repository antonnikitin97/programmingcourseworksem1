package com.company;

public class Giraffe extends Animal
{
    public Giraffe(Integer lifeExpectancy, Integer age, Integer health, char gender)
    {
        super(age, lifeExpectancy, health, new String []{"hay", "fruit"}, gender);
        this.type = "Giraffe";
    }

    // This method will 'treat' the giraffe and give it health
    private void neckMassage()
    {
        this.addHealth(4);
        System.out.format("%s has been massaged!\nHealth is now: %s", this.type ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("physio") && this.checkIfHealthCanBeAdded(4)) {
            this.neckMassage();
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
