package com.arenafight.gear;

import com.arenafight.Abilities;

/**
 * Represents an object of the type Headgear that can be equipped by a character one at a time.
 * Headgear is worn on the player's head and affects the player's constitution.
 * Since a player has one head, they can only wear one piece of headgear.
 * The range of constitution boost from a headgear is 1-3.
 */
public class Headgear extends Gear {

  /**
   * Used to construct a headgear object with the provided ability object.
   * @param boostAbility the boost ability object to assign to headgear
   * @param seed the seed integer that determines the random values.
   */
  public Headgear(Abilities boostAbility, int seed) {
    super(boostAbility);
    if ( boostAbility.getConstitution() == 0 ) {
      this.name = "No Helmet";
    }
    else {
      this.name = generateName(seed);
    }
    orderNumber = 3;
  }

  /**
   * A copy constructor for headgear that generates a copy of the headgear we provide
   * in the argument.
   * @param headgear the headgear we want to copy
   */
  public Headgear(Headgear headgear) {
    this.boostAbility = headgear.boostAbility;
    this.name = headgear.name;
    this.orderNumber = headgear.orderNumber;
  }

  @Override
  protected String generateName(int seed) {
    return super.generateName(seed) + " Helmet";
  }

}
