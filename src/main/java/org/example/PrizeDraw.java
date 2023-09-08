package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;



class PrizeDraw {
    private ToyStore toyStore;
    private List<Toy> prizeToys = new ArrayList<>();
    private Map<Integer, Integer> winnersToPrizes = new HashMap<>();
    private int numberOfParticipants;
    private int totalPrizes;

    public PrizeDraw(ToyStore toyStore, int numberOfParticipants, int totalPrizes) {
        this.toyStore = toyStore;
        this.numberOfParticipants = numberOfParticipants;
        this.totalPrizes = totalPrizes;
    }

    public void startDraw() {
        Random random = new Random();
        List<Integer> participantIndexes = new ArrayList<>();

        // Assign each participant an index
        for (int i = 0; i < numberOfParticipants; i++) {
            participantIndexes.add(i);
        }

        if (totalPrizes <= 0) {
            System.out.println("Нет доступных призов для розыгрыша.");
            return;
        }

        while (!participantIndexes.isEmpty() && totalPrizes > 0) {
            List<Toy> availableToys = toyStore.getAllToys();
            Collections.shuffle(availableToys);

            if (availableToys.isEmpty()) {
                System.out.println("Нет доступных игрушек для розыгрыша.");
                return;
            }

            int winnerIndex = random.nextInt(participantIndexes.size());
            int winner = participantIndexes.remove(winnerIndex) + 1; // Participant numbers start from 1

            Toy prizeToy = availableToys.get(0); // Draw the first available toy
            if (!winnersToPrizes.containsKey(winner)) {
                prizeToys.add(prizeToy);
                winnersToPrizes.put(winner, prizeToy.getId());
                savePrizeToy(prizeToy, winner);
                System.out.println("Победитель " + winner + " выиграл приз: " + prizeToy.getName());

                // Decrease the quantity of the prizeToy
                int quantity = toyStore.getQuantity(prizeToy);
                if (quantity > 1) {
                    toyStore.setQuantity(prizeToy, quantity - 1);
                } else {
                    toyStore.removeToy(prizeToy);
                }

                totalPrizes--;
            }
        }

        System.out.println("Победители: " + winnersToPrizes.keySet());
        System.out.println("Все призы разыграны!");
    }

    private void savePrizeToy(Toy toy, int winner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize_toys.txt", true))) {
            writer.write("Победитель " + winner + " выиграл: ID: " + toy.getId() + ", Название: " + toy.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




