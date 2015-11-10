package com.company;
import java.util.Random;

public abstract class Animal
{
    protected Integer ageOfAnimal;
    protected char gender;
    private String[] eats;
    private Integer health;
    private Integer lifeExpectancy;
    protected Enclosure enclosureAnimalResidesIn;
    protected Random randomGen = new Random();

    private Integer monthlyHealthDepreciation = 2;

    /*
    Constructor for a general animal, sets the age to 0 and health to 10
    */
    public Animal(Integer lifeExpectancy, String[] eats)
    {
        Integer randomNumber = randomGen.nextInt(100);
        if(randomNumber % 2 == 0) { // Here we are generating a random number between 0 and 10, we are then saying
            gender = 'm';           // if the number is a multiple of 2, the animal is a male, else it's a female -- FOR TESTING ONLY!!!
        }else{
            gender = 'f';
        }

        this.ageOfAnimal = 0;
        this.health = 10;
        this.lifeExpectancy = lifeExpectancy;
        this.eats = eats;

    }

    /*
    Method to return the life expectancy of the animal
    */
    protected Integer getLifeExpectancy()
    {
        return lifeExpectancy;
    }

    /*
    Method to return the gender of the animal
    */
    protected char getGender()
    {
        return gender;
    }

    /*
    Method to get the age of the animal
    */
    protected Integer getAgeOfAnimal()
    {
        return ageOfAnimal;
    }

    protected void addHealth(Integer healthToAdd)
    {
        if(checkIfHealthCanBeAdded(healthToAdd)) {
            this.health += healthToAdd;
        }else{
            System.out.println("Planned health addition takes health over 10! Not adding health!");
        }
    }

    protected void removeHealth(Integer healthToRemove)
    {
        checkIfHealthCanBeRemoved(healthToRemove);
    }

    protected Integer getHealth()
    {
        return this.health;
    }

    protected void monthlyHealthDecrease()
    {
        removeHealth(2);
    }

    /*
    Here we are iterating over the 'eats' array, checking whether or not the
    value at a certain index in the array equals the parameter passed in,
    and returning true if it does, if the loop finishes, it will by default
    return false, indicating  the food value is not in the 'eats' array.
    */
    protected Boolean canEat(String food)
    {
        for(String s : eats)
        {
            if(s.equals(food))
            {
                return true;
            }
        }
        return false;
    }

    protected void eat()
    {
        FoodStore temp = this.enclosureAnimalResidesIn.getFoodStore();
        Integer failedAttempts = 0;
        for(String s : temp.foodStorage.keySet())
        {
            if(this.canEat(s))
            {
                if(temp.takeFood(s)) {
                    giveHealthAndAddWasteBasedOnFood(s, this.enclosureAnimalResidesIn, this);
                    break;
                }else{
                    if(failedAttempts == this.eats.length)
                    {
                        System.out.println("\nNot enough food to feed animal! We should order more food!\n");
                        break;
                    }
                    System.out.format("Not enough of %s in store, continuing search... " , s);
                    failedAttempts += 1;
                }
            }
        }
    }

    public void treat(String keeperLabel)
    {
    	
    }

    protected void setEnclosure(Enclosure enclosureToSet)
    {
        this.enclosureAnimalResidesIn = enclosureToSet;
    }

    protected abstract Boolean aMonthPasses();

    /*
    This method checks to see what food is being eaten, and adds food/waste accordingly.
    */
    private void giveHealthAndAddWasteBasedOnFood(String food, Enclosure enclosure, Animal animal)
    {
        switch(food)
        {
            case "hay":
                addHealth(1);
                enclosure.addWaste(4);
                break;
            case "steak":
                addHealth(3);
                enclosure.addWaste(4);
                break;
            case "fruit":
                addHealth(2);
                enclosure.addWaste(3);
                break;
            case "celery":
                addHealth(0);
                enclosure.addWaste(1);
                break;
            case "fish":
                addHealth(3);
                enclosure.addWaste(2);
                break;
            case "ice cream":
                addHealth(1);
                enclosure.addWaste(3);
                break;
        }
    }

    /*
    Any given animal's health MUST be 10 or below. This method will check whether or not the planned health addition will take it over 10 health.
    If it does, it'll print out an 'error' message and return false. If it doesn't, it'll add the planned health to the animal's health and return
    true.
    */
    protected Boolean checkIfHealthCanBeAdded(Integer plannedHealthAddition)
    {
        if(plannedHealthAddition + this.getHealth() > 10) {
            System.out.println("\nHealth cannot be added as it would exceed 10 health!\n");
            return false;
        }else{
            return true;
        }
    }

    /*
    This method will verify that the planned health removal will not take the animal's health below 0. If this is the case it will return false
    In all other cases it will return true.
    */
    protected Boolean checkIfHealthCanBeRemoved(Integer plannedHealthDecrement)
    {
        if(this.getHealth() - plannedHealthDecrement < 0) {
            System.out.println("\nPlanned health removal will make health negative!\n");
            return false;
        }else{
            return true;
        }
    }
}
