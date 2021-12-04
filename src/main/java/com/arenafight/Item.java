package com.arenafight;

/**
 * An interface of the type Item that is implemented by all things that the player can hold,
 * including weapons, headgear, footwear, belts and potions. The common methods that all these types
 * should have, have been defined here in the item Interface.
 *
 */
public interface Item {

  /**
   * Used to return the boostAbilities from all Item children.
   * We can view this object to see how each and every item affects the player ability wise.
   * @return an object of type ability
   */
  Abilities getAbilities();

  /**
   * Returns the type of the item, used to distinguish between different item children.
   * @return a string with the kind of item
   */
  String getItemType();
}
