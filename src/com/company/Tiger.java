package com.company;

public class Tiger extends BigCat
{
    public Tiger(Integer age, Integer health, char gender)
    {
        super(age, health, gender);
        this.type = "Tiger";
    }

    private void stroked()
    {
        this.addHealth(3);
        System.out.format("%s has been stroked!\nHealth is now: %s", this.type ,this.getHealth());
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("default") && this.checkIfHealthCanBeAdded(3)) {
            this.stroked();
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
