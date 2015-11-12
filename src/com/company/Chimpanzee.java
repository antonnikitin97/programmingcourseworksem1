package com.company;

public class Chimpanzee extends Ape
{
    public Chimpanzee()
    {
        super(32);
        this.type = "Chimpanzee";
    }

    private void playChase()
    {
        this.addHealth(4);
        System.out.println("\nChase has been played with Chimpanzee!");
    }

    @Override
    public void treat(String keeperLabel)
    {
        if(keeperLabel.equals("play") && this.checkIfHealthCanBeAdded(4)) {
            this.playChase();
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
