package com.arenafight;

import com.arenafight.gear.Belt;
import com.arenafight.gear.BeltSize;
import com.arenafight.gear.Footwear;
import com.arenafight.gear.Gear;
import com.arenafight.gear.Headgear;
import com.arenafight.weapons.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class that represents the Arena in which the fights between 2 characters take place.
 * The arena is the class that has the main methods available to the user. It also has methods to
 * create an item bag and equip 20 random items to a character object.
 */
public class Arena {
  private static final int TURN_LIMIT = 40;

  /**
   * Creates a character, used to generate the player and their opponent.
   */
  public Character createCharacter(int seed) {
    return new Character(seed);
  }

  /**
   * Creates an equipment bag with some randomly generated items. The number of items in the bag is
   * passed as an argument as well as the seed for the random number generator.
   *
   * @param seed  the seed to generate randoms
   * @param count the count of items to generate (greater than 40 required)
   * @return an ArrayList of Items
   * @throws IllegalArgumentException thrown when the number of items is less than 40
   */
  public ArrayList<Item> createEquipmentBag(int seed, int count) throws IllegalArgumentException {
    if (count < 40) {
      throw new IllegalArgumentException("The number of items should "
        + "be greater than or equal to 40");
    }
    int i;
    Random random = new Random(seed);
    ArrayList<Item> items = new ArrayList<>();
    for (i = 0; i < 5; i++) {
      items.add(new ItemFactory().makeHeadGear(random.nextInt()));
      items.add(new ItemFactory().makeFootwear(random.nextInt()));
    }
    for (i = 0; i < 15; i++) {
      items.add(new ItemFactory().makeBelt(random.nextInt()));
      items.add(new ItemFactory().makePotion(random.nextInt()));
    }
    for (i = 0; i < (count - 40); i++) {
      items.add(new ItemFactory().makeRandomItem(random.nextInt()));
    }
    //Make 25% of items detrimental
    for (i = 0; i < (items.size() * 25) / 100; i++) {
      items.get(random.nextInt(items.size())).getAbilities().makeNegative();
    }
    return items;
  }

  /**
   * Returns a random weapon to equip to the player based on a given seed.
   *
   * @param seed int the seed that decides the random value
   * @return Weapon returns a random weapon of type Weapon
   */
  public Weapon equipWeapon(int seed) {
    return new ItemFactory().makeWeapon(seed);
  }

  /**
   * Equips a given character with twenty random items from the item bag.
   *
   * @param character the Character object we want to equip the items to
   * @param items     the list of items to choose randomly from
   */
  public void equipItems(Character character, ArrayList<Item> items, int seed) {

    Roll roll = new Roll();
    int itemCount = 0;
    ArrayList<Item> chosenItems = new ArrayList<>();
    ArrayList<Item> equippedItems = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      int noRoll = roll.rollInRange(0, items.size() - 1, seed);
      chosenItems.add(items.get(noRoll));
      items.remove(noRoll);
    }

    // Equip Footwear and Headgear first
    for (Item item : chosenItems) {
      if (item.getItemType().equals("Headgear")
          && character.getHeadgear().getName().equals("No Helmet")) {
        character.setHeadgear((Headgear) item);
        equippedItems.add(item);
        itemCount++;
      }
      if (item.getItemType().equals("Footwear") && character.getFootwear().getDexterity() == 0) {
        character.setFootwear((Footwear) item);
        equippedItems.add(item);
        itemCount++;
      }
      if (character.getFootwear().getDexterity() != 0
          && character.getHeadgear().getConstitution() != 0) {
        break;
      }
    }
    // Equip Belts
    for (Item item : chosenItems) {
      if (item.getItemType().equals("Belt")) {
        Belt belt = (Belt) item;
        if (belt.getBeltSize().equals(BeltSize.SMALL)
            && (character.getMaxBeltWeight() - character.getCurrentBeltWeight()) >= 1) {
          character.wearBelt(belt);
          equippedItems.add(item);
          itemCount++;
        } else if (belt.getBeltSize().equals(BeltSize.MEDIUM)
            && (character.getMaxBeltWeight() - character.getCurrentBeltWeight()) >= 2) {
          character.wearBelt(belt);
          equippedItems.add(item);
          itemCount++;
        } else if (belt.getBeltSize().equals(BeltSize.LARGE)
            && (character.getMaxBeltWeight() - character.getCurrentBeltWeight()) >= 4) {
          character.wearBelt(belt);
          equippedItems.add(item);
          itemCount++;
        }
      }
    }

    // Drink potions
    for (Item item : chosenItems) {
      if (item.getItemType().equals("Potion") && itemCount < 20) {
        Potion potion = (Potion) item;
        character.drinkPotion(potion);
        equippedItems.add(item);
        itemCount++;
      }
      if (itemCount == 20) {
        break;
      }
    }
    int strengthBoost = 0;
    int constitutionBoost = 0;
    int dexterityBoost = 0;
    int charismaBoost = 0;
    for (Item item : equippedItems) {
      strengthBoost += item.getAbilities().getStrength();
      constitutionBoost += item.getAbilities().getConstitution();
      dexterityBoost += item.getAbilities().getDexterity();
      charismaBoost += item.getAbilities().getDexterity();
    }
    Abilities modifiedAbilities = new Abilities(
        character.getBaseStats().getStrength() + strengthBoost,
        character.getBaseStats().getConstitution() + constitutionBoost,
        character.getBaseStats().getDexterity() + dexterityBoost,
        character.getBaseStats().getCharisma() + charismaBoost);
    character.setModifiedStats(modifiedAbilities);
    character.setCurrentHealth(
        character.getBaseStats().getStrength() + strengthBoost
        + character.getBaseStats().getConstitution() + constitutionBoost
        + character.getBaseStats().getDexterity() + dexterityBoost
        + character.getBaseStats().getCharisma() + charismaBoost);
  }

  /**
   * Prints the items used by the character including gear and potions.
   * The individual stat changes for each item and the item names are also displayed.
   *
   * @param character the character object whose equipment we want to display
   */
  public String printCharacterEquipment(Character character) {

    ArrayList<Gear> equippedGear = new ArrayList<>();
    equippedGear.add(character.getHeadgear());
    equippedGear.add(character.getFootwear());
    equippedGear.addAll(character.getBelts());
    StringBuilder str = new StringBuilder();
    Collections.sort(equippedGear);
    str.append(String.format("\n %30s %14s %14s %14s %14s %14s\n\n",
        "Gear Name", "Gear Type", "Strength+", "Constitution+", "Dexterity+", "Charisma+"));
    for (Gear gear : equippedGear) {
      if (gear != null) {
        str.append(String.format("%30s %14s %14d %14d %14d %14d \n",
            gear.getName(), gear.getItemType(), gear.getStrength(),
            gear.getConstitution(), gear.getDexterity(),
            gear.getCharisma()));
      }
    }
    for (Potion potion : character.getPotions()) {
      str.append(String.format("%30s %14s %14d %14d %14d %14d \n",
          potion.getName(), "Potion", potion.getAbilities().getStrength(),
          potion.getAbilities().getConstitution(), potion.getAbilities().getDexterity(),
          potion.getAbilities().getCharisma()));
    }

    return str.toString();
  }

  /**
   * Method that initiates the battle between two given Characters, the first argument is
   * treated as the player and the second argument is treated as the opponent.
   *
   * @param player player of type Character
   * @param opponent opponent of type Character
   * @param seed int the seed value that decides the random values.
   * @return a string with the text of the battle
   */
  public String battle(Character player, Character opponent, int seed) {
    StringBuilder stringBuilder = new StringBuilder();
    Random random = new Random(seed);
    player.setCurrentHealth(player.getModifiedStats().getStrength()
        + player.getModifiedStats().getConstitution()
        + player.getModifiedStats().getDexterity()
        + player.getModifiedStats().getCharisma()
    );

    opponent.setCurrentHealth(opponent.getModifiedStats().getStrength()
        + opponent.getModifiedStats().getConstitution()
        + opponent.getModifiedStats().getDexterity()
        + opponent.getModifiedStats().getCharisma()
    );
    stringBuilder.append(player.printBaseStats());
    stringBuilder.append(this.printCharacterEquipment(player));
    stringBuilder.append(player.printModifiedStats());
    stringBuilder.append(opponent.printBaseStats());
    stringBuilder.append(this.printCharacterEquipment(opponent));
    stringBuilder.append(opponent.printModifiedStats());

    player.setCurrentHealth(player.getModifiedStats().getStrength()
        + player.getModifiedStats().getConstitution()
        + player.getModifiedStats().getDexterity()
        + player.getModifiedStats().getCharisma()
    );

    opponent.setCurrentHealth(opponent.getModifiedStats().getStrength()
        + opponent.getModifiedStats().getConstitution()
        + opponent.getModifiedStats().getDexterity()
        + opponent.getModifiedStats().getCharisma()
    );
    boolean isPlayersTurn = player.getModifiedStats().getCharisma()
        > opponent.getModifiedStats().getCharisma();
    int turnCounter = 1;
    while (player.getCurrentHealth() > 0 && opponent.getCurrentHealth() > 0
        && turnCounter < TURN_LIMIT) {
      stringBuilder.append(String.format("Turn %d: \n %s Health: %d \n %s Health: %d \n",
          turnCounter,player.getFullName(),player.getCurrentHealth(),
          opponent.getFullName(),opponent.getCurrentHealth()));
      if (isPlayersTurn) {
        stringBuilder.append(player.attack(opponent, random.nextInt()));
      }
      else {
        stringBuilder.append(opponent.attack(player, random.nextInt()));
      }
      isPlayersTurn = !isPlayersTurn;
      turnCounter++;
    }
    if (player.getCurrentHealth() <= 0) {
      stringBuilder.append("\n You Lost.");
    }
    else if (opponent.getCurrentHealth() <= 0) {
      stringBuilder.append("\n You Won!");
    }
    else {
      stringBuilder.append("\n Match Draw.\n");
    }
    player.resetCharacter();
    opponent.resetCharacter();
    return stringBuilder.toString();
  }
}
