package com.company;

import java.util.ArrayList;

public class Enclosure
{
	protected FoodStore foodStore;
	protected ArrayList<Animal> animalsInEnclosure = new ArrayList<>();
	protected Integer animalWaste;
	protected Boolean isFull = false;

	public Enclosure()
	{
		foodStore = new FoodStore();

	}
	// Adds the animal passed into the method to the ArrayList of animals.
    public Boolean addAnimal(Animal animalToAdd)
	{
		if(isFull) {
			System.out.println("Enclosure full cannot add animal!");
			return false;
		}else{
			this.animalsInEnclosure.add(animalToAdd);
			return true;
		}
	}

    // Removes the passed in animal from the enclosure.
    public void removeAnimal(Animal animalToRemove)
	{
		this.animalsInEnclosure.remove(animalToRemove); //Removes the animal passed into the method from the ArrayList of animals.
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

	public Integer size()
	{
		return animalsInEnclosure.size();
	}

	/*
	Executes the 'aMonthPasses' on each animal in the enclosure. Checks to see whether the animal is dead.
    If so the animal is removed from the enclosure list. Also checks to see whether an animal object is
	Referencing null, in this case, the animal is removed from the enclosure list.
	*/
	public void aMonthPasses()
	{
		for(Animal a: animalsInEnclosure)
        {
            try
            {
                if(checkIfAnimalIsDead(a))
                {
                    this.animalsInEnclosure.remove(a);
                }
                a.aMonthPasses();
            }
            catch(NullPointerException e)
            {
                System.out.println("Animal referencing 'null' detected! Removing this from the list!!");
                this.removeAnimal(a);
            }
        }
	}

	public Boolean checkIfAnimalIsDead(Animal a)
	{
		if(a.getHealth() == 0 || a.getAgeOfAnimal().equals(a.getLifeExpectancy())) {
			System.out.print("An animal has died!");
			return true;
		}else{
			return false;
		}
	}
}
