package com.arenafight;

import com.arenafight.weapons.Weapon;

/**
 * An interface of type Playable that is implemented by the Character class and used to provide
 * basic functionality to it. The methods that must be implemented in the Character class in order
 * for the game to work have been given here. A few examples include; printBaseStats,
 * getModifiedStats, getCurrentHealth and setCurrentHealth, all of which are essential to the game.
 */
public interface Playable {

  /**
   * Used to print the base stats of a character unaffected by any gear or potions.
   *
   * @return a string to print out with the stat values and character name.
   */
  String printBaseStats();

  /**
   * Used to print the modified stats of a character as affected by any gear or potions.
   *
   * @return a string with the character name and modified stats
   */
  String printModifiedStats();

  /**
   * Returns the current health of a character.
   *
   * @return int current health
   */
  int getCurrentHealth();

  /**
   * Sets the current health of the character. Used in the battle scenarios.
   *
   * @param currentHealth int the new health to set
   */
  void setCurrentHealth(int currentHealth);

  /**
   * Gets a copy of the weapon of the character as a Weapon object.
   *
   * @return Weapon the object returned
   */
  Weapon getWeapon();

  /**
   * Returns a copy of the base stats of the character.
   * @return an Abilities copy of base stats reference
   */
  Abilities getBaseStats();

  /**
   * Returns a copy of the modified stats of the character.
   * @return an Abilities copy of modifiedstats reference
   */
  Abilities getModifiedStats();
}
