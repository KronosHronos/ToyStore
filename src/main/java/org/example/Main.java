package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в программу розыгрыша игрушек!");

        System.out.print("Введите количество участников розыгрыша: ");
        int numberOfParticipants = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after reading the number

        System.out.print("Введите общее количество игрушек для разыгрывания: ");
        int totalQuantity = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after reading the number

        int totalPrizes = totalQuantity;

        System.out.print("Добавьте игрушки в магазин (ID, Название, Количество, Вес):\n");
        while (totalQuantity > 0) {
            System.out.print("Введите ID (0 для завершения): ");
            int toyId = scanner.nextInt();
            if (toyId == 0) {
                break;
            }
            scanner.nextLine(); // Clear the buffer after reading the number

            System.out.print("Введите название: ");
            String name = scanner.nextLine();

            System.out.print("Введите количество: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer after reading the number

            System.out.print("Введите вес: ");
            double weight = scanner.nextDouble();
            scanner.nextLine(); // Clear the buffer after reading the number

            Toy toy = new Toy(toyId, name, quantity, weight);
            toyStore.addToy(toy);
            totalQuantity -= quantity;
        }

        PrizeDraw prizeDraw = new PrizeDraw(toyStore, numberOfParticipants, totalPrizes);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Разыграть призы");
            System.out.println("2. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    prizeDraw.startDraw();
                    break;
                case 2:
                    System.out.println("Программа завершена.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите действие из меню.");
                    break;
            }
        }
    }
}
