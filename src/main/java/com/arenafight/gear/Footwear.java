package com.arenafight.gear;

import com.arenafight.Abilities;

/**
 * Represents an object of the type Footwear that can be equipped by a character one at a time.
 * Footwear is worn on the player's feet and affects the player's dexterity.
 * A footwear object is treated as a pair and they have a range of 1 - 3 boost.
 */
public class Footwear extends Gear {

  /**
   * Constructs a footwear object that initializes the abilities reference, generates a name,
   * and sets the orderNumber for ordering in sort.
   *
   * @param boostAbility the ability stats to be set for the footwear
   * @param seed the seed to generate a random name
   */
  public Footwear(Abilities boostAbility, int seed) {
    super(boostAbility);
    if ( boostAbility.getDexterity() == 0 ) {
      this.name = "Bare Footed";
    }
    else {
      this.name = generateName(seed);
    }
    orderNumber = 1;
  }

  /**
   * A copy constructor for Footwear that creates a copy of the provided footwear object.
   *
   * @param footwear the footwear object to copy.
   */
  public Footwear(Footwear footwear) {
    this.boostAbility = footwear.boostAbility;
    this.name = footwear.name;
    this.orderNumber = footwear.orderNumber;
  }

  @Override
  protected String generateName(int seed) {
    return super.generateName(seed) + " Boots";
  }

}
