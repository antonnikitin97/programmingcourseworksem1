package com.company;

import java.util.ArrayList;

public class Enclosure
{
	protected FoodStore foodStore;
	protected ArrayList<Animal> animalsInEnclosure = new ArrayList<>();
	protected Integer animalWaste = 0;
	protected Boolean isFull = false;
    protected ArrayList<ZooKeeper> listOfKeepers = new ArrayList<>();
	protected ArrayList<Animal> deadAnimalsToRemove = new ArrayList<>();
	protected Boolean isEmpty = true;

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

	public Boolean checkIfAnimalIsDead(Animal a)
	{
		if(a.getHealth() == 0 || a.getAgeOfAnimal().equals(a.getLifeExpectancy())) {
			System.out.format("###%s has died!###\n", a.type);
			return true;
		}else{
			return false;
		}
	}

    public void addKeeper(ZooKeeper zk)
    {
        this.listOfKeepers.add(zk);
    }

	public void printTypesOfAnimalInEnclosre()
	{

		System.out.println("--- STATUS ON ANIMALS IN ENCLOSURE ---");

		for(Animal s : animalsInEnclosure)
		{
			System.out.format("Animal: %s --- Health: %s --- Age: %s\n", s.type, s.getHealth(), s.getAgeOfAnimal());
		}
	}
}
