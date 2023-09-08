package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ToyStore {
    private List<Toy> toys = new ArrayList<>();

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public Toy drawToy() {
        if (toys.isEmpty()) {
            return null; // No toys available
        }

        Random random = new Random();
        int randomIndex = random.nextInt(toys.size());
        return toys.remove(randomIndex);
    }

    public List<Toy> getAllToys() {
        return toys;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Toy toy : toys) {
            totalQuantity += toy.getQuantity();
        }
        return totalQuantity;
    }

    public int getQuantity(Toy toy) {
        for (Toy storedToy : toys) {
            if (storedToy.getId() == toy.getId()) {
                return storedToy.getQuantity();
            }
        }
        return 0;
    }

    public void setQuantity(Toy toy, int quantity) {
        for (Toy storedToy : toys) {
            if (storedToy.getId() == toy.getId()) {
                storedToy.setQuantity(quantity);
                return;
            }
        }
    }

    public void removeToy(Toy toy) {
        toys.remove(toy);
    }
}


