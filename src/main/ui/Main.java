package ui;

import java.util.Collection;
import java.util.Scanner;
import model.Binder;
import model.Card;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean continueAdding = true;
        Binder mbinder = new Binder(); // Create the Binder once, so it doesn't reset every loop
    
        while (continueAdding) {
            Card newCard;
            System.out.println("Please enter your card's condition, rarity, name, type, and price:");
    
            System.out.println("Condition: ");
            String condition1 = scanner.nextLine();
    
            System.out.println("Rarity: ");
            String rarity1 = scanner.nextLine();
    
            System.out.println("Name: ");
            String name1 = scanner.nextLine();
    
            System.out.println("Type: ");
            String type1 = scanner.nextLine();
    
            System.out.println("Price: ");
            int price1 = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character
    
            newCard = new Card(condition1, rarity1, name1, type1, price1);
            mbinder.addCard(newCard);
            System.out.println(newCard.readCardInfo());
    
            System.out.println("Would you like to see your current collection? (yes/no): ");
            String answer1 = scanner.nextLine();
    
            if (answer1.contentEquals("yes")) {
                mbinder.seeTheCollection();
            }
    
            System.out.println("Do you want to add a new card? (yes/no): ");
            String answer = scanner.nextLine();
    
            if (!answer.contentEquals("yes")) {
                continueAdding = false;
            }
        }
    
        scanner.close();
        System.out.println("Finished Adding Cards");
    }
}