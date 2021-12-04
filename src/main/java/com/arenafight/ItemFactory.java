package com.arenafight;

import com.arenafight.gear.Belt;
import com.arenafight.gear.Footwear;
import com.arenafight.gear.Headgear;
import com.arenafight.weapons.Axe;
import com.arenafight.weapons.Broadsword;
import com.arenafight.weapons.Flail;
import com.arenafight.weapons.Katana;
import com.arenafight.weapons.TwoHanded;
import com.arenafight.weapons.Weapon;

import java.util.Random;

/**
 * This is a factory class that creates items.
 */
public class ItemFactory {

  /**
   * Constructs different types of objects based on the random number obtained in the roll.
   * @param seed int the seed to generate the random numbers
   * @return item returns the object created
   */
  public Item makeRandomItem(int seed) {
    Roll roll = new Roll();
    int randomNo = roll.rollInRange(1, 4, seed);

    switch (randomNo) {
      case 1 : return new Belt(new AbilityBuilder()
        .strength(roll.rollInRange(1, 10, seed))
        .constitution(roll.rollInRange(1, 3, seed))
        .dexterity(roll.rollInRange(1, 3, seed))
        .charisma(roll.rollInRange(1, 3, seed))
        .build(), seed);

      case 2 : return new Footwear(new AbilityBuilder()
        .dexterity(roll.rollInRange(1, 3, seed)).build(),seed);

      case 3 : return new Headgear(new AbilityBuilder()
        .constitution(roll.rollInRange(1, 3, seed)).build(),seed);

      case 4 : return new Potion((new AbilityBuilder()
        .strength(roll.rollInRange(0, 10, seed))
        .constitution(roll.rollInRange(0, 3, seed))
        .dexterity(roll.rollInRange(0, 3, seed))
        .charisma(roll.rollInRange(0, 3, seed))
        .build()),seed);
      default: break;
    }
    return null;
  }

  /**
   * Creates a headgear with a random constitution boost from 1-3.
   *
   * @param seed the seed that determines the random value
   * @return Item, a headgear object in an Item reference
   */
  public Item makeHeadGear(int seed) {
    Roll roll = new Roll();
    return new Headgear(new AbilityBuilder()
      .constitution(roll.rollInRange(1, 3, seed)).build(),seed);
  }

  /**
   * Creates a footwear with a random dexterity boost from 1-3.
   *
   * @param seed the seed that determines the random value
   * @return Item, a footwear object in an Item reference
   */
  public Item makeFootwear(int seed) {
    Roll roll = new Roll();
    return new Footwear(new AbilityBuilder()
      .dexterity(roll.rollInRange(1, 3, seed)).build(),seed);
  }

  /**
   * Creates a belt with 2 random boost from 1-3, (strength: 1-10).
   *
   * @param seed the seed that determines the random values
   * @return Item, a belt object in an Item reference
   */
  public Item makeBelt(int seed) {
    Roll roll = new Roll();
    Random random = new Random(seed);
    return new Belt(new AbilityBuilder()
      .strength(roll.rollInRange(1, 10, random.nextInt()))
      .constitution(roll.rollInRange(1, 3, random.nextInt()))
      .dexterity(roll.rollInRange(1, 3, random.nextInt()))
      .charisma(roll.rollInRange(1, 3, random.nextInt()))
      .build(), seed);
  }

  /**
   * Creates a potion with random boosts from 0-3, (strength: 1-10).
   *
   * @param seed the seed that determines the random values
   * @return Item, a potion object in an Item reference
   */
  public Item makePotion(int seed) {
    Roll roll = new Roll();
    Random random = new Random(seed);
    return new Potion((new AbilityBuilder()
      .strength(roll.rollInRange(0, 10, random.nextInt()))
      .constitution(roll.rollInRange(0, 3, random.nextInt()))
      .dexterity(roll.rollInRange(0, 3, random.nextInt()))
      .charisma(roll.rollInRange(0, 3, random.nextInt()))
      .build()),seed);
  }

  /**
   * Returns a weapon from a choice of weapons based on a seeded randomly generated value.
   *
   * @param seed the integer that decides the random value
   * @return returns a weapon to equip to the character
   */
  public Weapon makeWeapon(int seed) {
    Roll roll = new Roll();
    int randomNo = roll.rollInRange(1, 5, seed);

    switch (randomNo) {
      case 1:
        return new Axe();
      case 2:
        return new Flail();
      case 3:
        return new Katana();
      case 4:
        return new Broadsword();
      case 5:
        return new TwoHanded();
      default:
        return null;
    }
  }

  /**
   * Creates a bare head Headgear object that represents having no Headgear on.
   *
   * @return a Headgear with no boosts
   */
  public Headgear makeBareHeadGear() {
    return new Headgear(new AbilityBuilder()
      .constitution(0).build(),0);
  }

  /**
   * Creates a bare foot Footwear object that represents having no Footwear on.
   *
   * @return a Footwear with no boosts
   */
  public Footwear makeBareFootGear() {
    return new Footwear(new AbilityBuilder()
      .dexterity(0).build(),0);
  }



}
