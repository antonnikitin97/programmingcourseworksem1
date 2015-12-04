/*
Base Class that represents a general animal, contains fields for age, health, type, the enclosure it lives in, what it can eat and its gender.
Also contains methods that allow other classes to access information (e.g. getAge, getHealth etc...), and methods to add and remove health, and add health based on
food eaten.
*/

package com.company;

public abstract class Animal
{
    private Integer ageOfAnimal;
    private char gender;
    private String[] eats;
    private Integer health;
    private Integer lifeExpectancy;
    protected Enclosure enclosureAnimalResidesIn;
    private String type;

    /**
    Constructor for a general animal, sets the age, what it eats and health to whatever is passed in from the subclasses.
    */
    public Animal(Integer age, Integer lifeExpectancy, Integer health, String[] eats, char gender)
    {
        this.ageOfAnimal = age;
        this.health = health;
        this.lifeExpectancy = lifeExpectancy;
        this.eats = eats;
        this.gender = gender;
    }

    /**
    Method to return the life expectancy of the animal
    */
    protected Integer getLifeExpectancy()
    {
        return lifeExpectancy;
    }

    /**
    Method to return the gender of the animal
    */
    protected char getGender()
    {
        return gender;
    }

    /**
    Method to get the age of the animal
    */
    protected Integer getAgeOfAnimal()
    {
        return ageOfAnimal;
    }

    protected void addHealth(Integer healthToAdd)
    {
        if(checkIfHealthCanBeAdded(healthToAdd)) {
            System.out.format("Health of %s (Gender: %s) cannot be increased by %s as it would exceed 10 health!\n", this.getType(), this.getGender(), healthToAdd);
        }else{
            this.health += healthToAdd;
        }
    }

    /**
    Checks to see if the given health can be removed, if it can't it tries to remove 1 health.
    */
    protected void removeHealth(Integer healthToRemove)
    {
        if(checkIfHealthCanBeRemoved(healthToRemove)) {
            this.health -= healthToRemove;
        }else{
            if(checkIfHealthCanBeRemoved(1))
            {
                this.health -= 1;
            }
        }
    }
    protected Integer getHealth()
    {
        return this.health;
    }

    /**
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
        for(String s : temp.getFoodStorage().keySet())
        {
            if(this.canEat(s))
            {
                if(temp.takeFood(s)) {
                    //Checks if the animal can eat food, if yes then we take an item and break out of the loop (to ensure only one item is eaten per month)
                    giveHealthAndAddWasteBasedOnFood(s, this.enclosureAnimalResidesIn, this);
                    System.out.format("%s has eaten %s! Health is now %s\n", this.type , s, this.getHealth());
                    break;
                }else{
                    System.out.format("%s tried to eat %s. Not enough of %s in store, continuing search...\n" ,this.type, s, s);
                    failedAttempts += 1;
                    if(failedAttempts == this.eats.length) {
                        System.out.format("No food available for %s to eat! :(\n", this.getType());
                        break;
                    }
                }
            }
        }
    }

    /**
    Method that will treat the animal if the correct keeper is treating. This method is overridden in each sub-class
    so that the treat method for each individual animal can be called.
    */
    public void treat(String keeperLabel) {
    	
    }

    public void incrementAge()
    {
        this.ageOfAnimal ++;
    }

    protected abstract Boolean aMonthPasses();

    /**
    This method checks to see what food is being eaten, and adds food/waste accordingly.
    */
    private void giveHealthAndAddWasteBasedOnFood(String food, Enclosure enclosure, Animal animal) {
        switch(food)
        {
            case "hay":
                animal.addHealth(1);
                enclosure.addWaste(4);
                break;
            case "steak":
                animal.addHealth(3);
                enclosure.addWaste(4);
                break;
            case "fruit":
                animal.addHealth(2);
                enclosure.addWaste(3);
                break;
            case "celery":
                animal.addHealth(0);
                enclosure.addWaste(1);
                break;
            case "fish":
                animal.addHealth(3);
                enclosure.addWaste(2);
                break;
            case "ice cream":
                animal.addHealth(1);
                enclosure.addWaste(3);
                break;
        }
    }

    /**
    Any given animal's health MUST be 10 or below. This method will check whether or not the planned health addition will take it over 10 health.
    If it does, it'll print out an 'error' message and return false. If it doesn't, it'll add the planned health to the animal's health and return
    true.
    */
    protected Boolean checkIfHealthCanBeAdded(Integer plannedHealthAddition) {
        return (this.getHealth() + plannedHealthAddition > 10);
    }

    /**
    This method will verify that the planned health removal will not take the animal's health below 0. If this is the case it will return false
    In all other cases it will return true.
    */
    protected Boolean checkIfHealthCanBeRemoved(Integer plannedHealthDecrement)
    {
        if(this.getHealth() - plannedHealthDecrement < 0) {
            System.out.println("Planned health removal will make health negative!");
            return false;
        }else{
            return true;
        }
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String typeToSet)
    {
        this.type = typeToSet;
    }
}