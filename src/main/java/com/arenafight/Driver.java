package com.arenafight;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the driver class with the main method where the model is going to be
 * demonstrated.
 *
 */
public class Driver {

  /**
   * The main method of the program.
   * @param args any arguments to the main method are given here
   */
  public static void main(String[] args) {

    int masterSeed = 2312;
    Random random = new Random(masterSeed);
    Arena arena = new Arena();
    Character player = arena.createCharacter(random.nextInt());
    Character opponent = arena.createCharacter(random.nextInt());
    boolean continueBattle = true;

    while (continueBattle) {
      ArrayList<Item> items1 = arena.createEquipmentBag(random.nextInt(), 40);
      ArrayList<Item> items2 = arena.createEquipmentBag(random.nextInt(), 40);
      arena.equipItems(player, items1, random.nextInt());
      arena.equipItems(opponent, items2, random.nextInt());
      player.setWeapon(arena.equipWeapon(random.nextInt()));
      opponent.setWeapon(arena.equipWeapon(random.nextInt()));

      arena.printCharacterEquipment(player);
      //Prints out the characters full equipment and modifications to stats

      System.out.println(arena.battle(player, opponent, random.nextInt()));
      Scanner scanner = new Scanner(System.in);
      System.out.println("Do you want to have a rematch? (Y,N)");
      if (!scanner.nextLine().equalsIgnoreCase("y")) {
        continueBattle = false;
      }
    }

    System.out.println("Thanks for playing!");
  }
}
