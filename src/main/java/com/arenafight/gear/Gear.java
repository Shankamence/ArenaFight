package com.arenafight.gear;

import com.arenafight.Abilities;
import com.arenafight.Item;
import com.arenafight.Roll;

/**
 * An abstract class that represents the equippable items that a player can have including
 * headgear, footwear and belts. It has certain common attributes like boostAbility, name,
 * and order number. It implements both the item interface as its treated as an item and the
 * comparable interface to define the compare to method in order to sort in the desired order.
 */
public abstract class Gear implements Item, Comparable<Gear> {
  protected Abilities boostAbility;
  protected String name;
  protected int orderNumber;

  /**
   * Default constructor for Gear.
   */
  public Gear() {
    boostAbility = null;
  }

  /**
   * A constructor for gear that takes an ability object and assigns it to
   * the boostAbility attribute. Used in super() calls of subclasses.
   * @param boostAbility the ability values to assign to the gear
   */
  public Gear(Abilities boostAbility) {
    this.boostAbility = boostAbility;
  }

  /**
   * Returns the strength boost of the gear.
   * @return int value of strength boost
   */
  public int getStrength() {
    return boostAbility.getStrength();
  }

  /**
   * Returns the constitution boost of the gear.
   * @return int value of constitution boost
   */
  public int getConstitution() {
    return boostAbility.getConstitution();
  }

  /**
   * Returns the dexterity boost of the gear.
   * @return int value of dexterity boost
   */
  public int getDexterity() {
    return boostAbility.getDexterity();
  }

  /**
   * Returns the charisma boost of the gear.
   * @return int value of charisma boost
   */
  public int getCharisma() {
    return boostAbility.getCharisma();
  }

  /**
   * A protected method used to generate a random gear class for the gear to add to name.
   * @param seed used to determine the random values
   * @return returns a string of the name
   */
  protected String generateName(int seed) {
    Roll roll = new Roll();
    return roll.rollForGearClass(seed);
  }

  /**
   * Returns a string of the Simple class name in order to determine the gear type.
   * @return a string with the type of gear
   */
  public String getItemType() {
    return this.getClass().getSimpleName();
  }

  /**
   * Returns the name of the gear.
   * @return returns the gear name.
   */
  public String getName() {
    return name;
  }

  @Override
  public Abilities getAbilities() {
    return boostAbility;
  }

  @Override
  public int compareTo(Gear g2) {
    if (this == g2) {
      return 0;
    }
    if (this.getClass().getSimpleName().equals(g2.getClass().getSimpleName())) {
      return this.getName().compareTo(g2.getName());
    }
    else {
      return g2.orderNumber - this.orderNumber;
    }
  }
}
