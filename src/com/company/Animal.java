package com.company;
import java.util.Random;

public abstract class Animal
{
    protected Integer ageOfAnimal;
    protected char gender;
    private String[] eats;
    protected Integer health;
    private Integer lifeExpectancy;
    protected Enclosure enclosureAnimalResidesIn;
    protected Random randomGen = new Random();

    private Integer monthlyHealthDepreciation = 2;

    // Constructor for a general animal, sets the age to 0 and health to 10
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

    // Method to return the life expectancy of the animal
    protected Integer getLifeExpectancy()
    {
        return lifeExpectancy;
    }

    // Method to return the gender of the animal
    protected char getGender()
    {
        return gender;
    }

    // Method to get the age of the animal
    protected Integer getAgeOfAnimal()
    {
        return ageOfAnimal;
    }

    protected void monthlyHealthDecrease()
    {
        health -= monthlyHealthDepreciation;
    }

    protected Boolean canEat(String food)
    {                                   // Here we are iterating over the 'eats' array, checking whether or not the
        for(String s : eats)            // value at a certain index in the array equals the parameter passed in,
        {                               // and returning true if it does, if the loop finishes, it will by default
            if(s.equals(food))          // return false, indicating  the food value is not in the 'eats' array.
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
                    temp.giveHealthAndAddWasteBasedOnFood(s, this.enclosureAnimalResidesIn, this);
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

    protected void treat()
    {
    	
    }

    protected void setEnclosure(Enclosure enclosureToSet)
    {
        this.enclosureAnimalResidesIn = enclosureToSet;
    }

    protected abstract Boolean aMonthPasses();
}
