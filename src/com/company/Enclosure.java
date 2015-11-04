package com.company;

import java.util.ArrayList;

public class Enclosure
{
	protected FoodStore foodStore;
	protected ArrayList<Animal> animalsInEnclosure = new ArrayList<>();
	protected ArrayList<ZooKeeper> zooKeepersAssignedToEnclosure = new ArrayList<>();
	protected Integer animalWaste;
	protected Boolean isFull = false;

	public Enclosure()
	{
		foodStore = new FoodStore();
        this.foodStore.foodStorage.put("hay" , 200);
        this.foodStore.foodStorage.put("steak" , 200);
        this.foodStore.foodStorage.put("fruit" , 200);
        this.foodStore.foodStorage.put("celery" , 200);
        this.foodStore.foodStorage.put("fish" , 200);
        this.foodStore.foodStorage.put("ice cream" , 200);
	}

	public Boolean addAnimal(Animal animalToAdd) //Adds the animal passed into the method to the ArrayList of animals.
	{
		if(!isFull) {
			System.out.println("Enclosure full cannot add animal!");
			return false;
		}else{
			this.animalsInEnclosure.add(animalToAdd);
			return true;
		}
	}

	public void removeAnimal(Animal animalToRemove)
	{
		this.animalsInEnclosure.remove(animalToRemove); //Removes the animal passed into the method from the ArrayList of animals.
	}

	public void removeWaste(Integer wasteToRemove)
	{
		animalWaste -= wasteToRemove;
	}

	public void addWaste(Integer wasteToAdd)
	{
		animalWaste += wasteToAdd;
	}

	public Integer getWasteSize()
	{
		return animalWaste;
	}

	public FoodStore getFoodStore()
	{
		return foodStore;
	}

	public Integer size()
	{
		return animalsInEnclosure.size();
	}

	public void aMonthPasses()
	{
		for(Animal a: animalsInEnclosure)
        {
            try
            {
                a.aMonthPasses();
                if(checkIfAnimalIsDead(a))
                {
                    this.animalsInEnclosure.remove(a);
                }
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
		if(a.health == 0 || a.getAgeOfAnimal().equals(a.getLifeExpectancy())) {
			System.out.print("An animal has died!");
			return true;
		}else{
			return false;
		}
	}
}
