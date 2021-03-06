/*
Class that represents an enclosure, contains fields that represent animal waste, the list of keepers assigned to it
the animals in the enclosure, whether it's full and finally the deadAnimalsToRemove (if there are any)
*/

package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Enclosure
{
	private Random random = new Random();

    private FoodStore foodStore;
	private ArrayList<Animal> animalsInEnclosure = new ArrayList<>();
	private Integer animalWaste = 0;
	private Boolean isFull = false;
	private ArrayList<Animal> deadAnimalsToRemove = new ArrayList<>();

	public Enclosure()
	{
		foodStore = new FoodStore();
	}

	/**
	Adds the animal passed into the method to the ArrayList of animals.
	*/
    public Boolean addAnimal(Animal animalToAdd)
	{
		if(isFull) {
			System.out.println("Enclosure full cannot add animal!");
			return false;
		}else{
			this.animalsInEnclosure.add(animalToAdd);
            animalToAdd.enclosureAnimalResidesIn = this;
            setFullStatus();
			return true;
		}
	}

    public void removeAnimal(Animal animalToRemove)
	{
		this.animalsInEnclosure.remove(animalToRemove);
	}

	public void addWaste(Integer wasteToAdd)
	{
		animalWaste += wasteToAdd;
	}

	public Integer getWasteSize()
	{
		return animalWaste;
	}

    public void removeWaste(Integer wasteToRemove)
    {
        this.animalWaste -= wasteToRemove;
    }

	public FoodStore getFoodStore()
	{
		return foodStore;
	}

	public Integer getSize()
	{
		return animalsInEnclosure.size();
	}

	/**
	Executes the 'aMonthPasses' on each animal in the enclosure. Checks to see whether the animal is dead.
    If so the animal is removed from the enclosure list. Also checks to see whether an animal object is
	Referencing null, in this case, the animal is removed from the enclosure list.
	*/
	public void aMonthPasses()
	{
        if(this.getSize() > 1)
        {
            breedTwoAnimals();
        }
        System.out.println("#### ANIMALS EATING ####");
        for(Animal a: animalsInEnclosure)
        {
            try
            {
                /**
                 * Checking if an animal is dead, if it is we add it to the list of dead animals, as we can't remove during the for loop as this will yield a
                 * 'ConcurrentModification' exception.
                 */
                if(checkIfAnimalIsDead(a))
                {
                    deadAnimalsToRemove.add(a);
					continue;
                }
                a.aMonthPasses();
				a.incrementAge();
            }
            catch(NullPointerException e)
            {
                System.out.println("Animal referencing 'null' detected! Removing this from the list!!");
                this.removeAnimal(a);
            }
        }

		for(Animal a : deadAnimalsToRemove)
		{
			this.animalsInEnclosure.remove(a);
		}
	}

    /**
    Checks to see whether the animal is dead, returns true if it is, else false.
    */
	public Boolean checkIfAnimalIsDead(Animal a)
	{
		if(a.getHealth() == 0 || a.getAgeOfAnimal().equals(a.getLifeExpectancy())) {
			System.out.format("###%s has died!###\n", a.getType());
			return true;
		}else{
			return false;
		}
	}
    /**
    Method to check whether all the animals in this enclosure are dead
    */
    public Boolean checkIfAllDead()
    {
        Integer numberDead = 0;
        for(Animal a : animalsInEnclosure)
        {
            if(a.getHealth() == 0)
            {
                numberDead += 1;
            }
        }
        return (numberDead.equals(this.getSize()));
    }

	public void printTypesOfAnimalInEnclosure()
	{
        System.out.println("--- STATUS ON ANIMALS IN ENCLOSURE ---");
		for(Animal s : animalsInEnclosure)
		{
			System.out.format("Animal: %s --- Health: %s --- Age: %s --- Gender: %s\n", s.getType(), s.getHealth(), s.getAgeOfAnimal(), s.getGender());
		}
	}

    public void printFoodStatus()
    {
        System.out.println("--- STATUS ON FOOD IN ENCLOSURE AT START OF MONTH ---");
        for(String s : this.getFoodStore().getAvailableFoodInZoo())
        {
            System.out.format("Food: %s --- Quantity: %s\n", s, this.getFoodStore().getFoodQuantity(s));
        }
    }

    /**
    This method breeds two animals, first it gets two random animals and checks to see if they can breed
    if they can it generates a random number and sees if it's divisible by 2. If it is then breeding is
    successful.
    */
    public void breedTwoAnimals()
    {
        System.out.println("#### BREEDING ####");
        ArrayList<Animal> breed = pickTwoRandom();
        Animal tempA = breed.get(0);
        Animal tempB = breed.get(1);

        if(checkCanBreed(tempA, tempB))
        {
            if (random.nextInt(100) % 2 == 0)
            {
                System.out.format("\n%s and %s had a baby!! :)\n\n", tempA.getType(), tempB.getType());
                switch (tempA.getType()) {
                    case "Lion":
                        this.addAnimal(new Lion(0, 10, generateGender()));
                        break;
                    case "Bear":
                        this.addAnimal(new Bear(0, 10, generateGender()));
                        break;
                    case "Tiger":
                        this.addAnimal(new Tiger(0, 10, generateGender()));
                        break;
                    case "Elephant":
                        this.addAnimal(new Elephant(0, 10, generateGender()));
                        break;
                    case "Penguin":
                        this.addAnimal(new Penguin(0, 10, generateGender()));
                        break;
                    case "Chimpanzee":
                        this.addAnimal(new Chimpanzee(0, 10, generateGender()));
                        break;
                    case "Gorilla":
                        this.addAnimal(new Gorilla(0, 10, generateGender()));
                        break;
                    case "Giraffe":
                        this.addAnimal(new Giraffe(0, 10, generateGender()));
                        break;
                }
            }else{
                System.out.format("\n%s (Age: %s) and %s (Age: %s) tried to breed! But it didn't work out :'(\n\n", tempA.getType(), tempA.getAgeOfAnimal(), tempB.getType(), tempB.getAgeOfAnimal());
            }
                }else{
                    if(tempA.getType().equals(tempB.getType()) && tempA.getGender() == tempB.getGender()) {
                     System.out.format("\n%s (Age: %s) (Gender: %s) and %s (Age %s) (Gender: %s) tried to breed! But they're the same gender!\n", tempA.getType(), tempA.getAgeOfAnimal(), tempA.getGender(), tempB.getType(), tempB.getAgeOfAnimal(), tempB.getGender());
                }else{
                    if((tempA.getType().equals(tempB.getType())) && (tempA.getAgeOfAnimal() <= 4 || tempB.getAgeOfAnimal() <= 4)) {
                        System.out.format("\n%s (Age: %s) and %s (Age: %s) tried to breed! But they're too young!\n\n", tempA.getType(), tempA.getAgeOfAnimal(), tempB.getType(), tempB.getAgeOfAnimal());
                    }else{
                        System.out.format("\n%s (Age: %s) and %s (Age: %s) tried to breed! But they're not the right type!\n\n", tempA.getType(), tempA.getAgeOfAnimal(), tempB.getType(), tempB.getAgeOfAnimal());
                    }
                }
            }
    }

    /**
    Picks two random animals from the enclosure.
    */
    public ArrayList<Animal> pickTwoRandom()
    {
        ArrayList<Animal> animalsToReturn = new ArrayList<>();

        Integer x = random.nextInt(this.getSize());
        Integer y = random.nextInt(this.getSize());

        while(x.equals(y))
        {
            x = random.nextInt(this.getSize());
            y = random.nextInt(this.getSize());
        }

        animalsToReturn.add(this.animalsInEnclosure.get(x));
        animalsToReturn.add(this.animalsInEnclosure.get(y));

        return animalsToReturn;
    }

    /**
    This method generates either a 'M' or 'F' at random.
    */
    public char generateGender()
    {
        if(random.nextInt(10) % 2 == 0) {
            return 'M';
        }else{
            return 'F';
        }
    }

    /**
    Method that checks to see if two animals can breed.
    Checks if the genders are different, the ages are greater than 4 and their types are the same.
    */
    public Boolean checkCanBreed(Animal a, Animal b)
    {
        if(this.isFull) {
            return false;
        }else{
            return ((a.getGender() != b.getGender()) && (a.getAgeOfAnimal() > 4 && b.getAgeOfAnimal() > 4) && (a.getType().equals(b.getType())));
        }
    }

    /**
     * Checks to see whether the enclosure is full and sets the 'isFull' variable
     * accordingly.
     */
    public void setFullStatus()
    {
        if(this.getSize() >= 20) {
            this.isFull = true;
        }else{
            this.isFull = false;
        }
    }

    public Animal getAnimal(Integer indexOfAnimal)
    {
        return this.animalsInEnclosure.get(indexOfAnimal);
    }
}
