package com.arenafight.weapons;

/**
 * A class that represents the weapon of type broadsword. Broadswords are a good medium weapon
 * that can do 6-10 points of damage when they hit. They have no special conditions or abilities.
 */
public class Broadsword extends Sword {

  /**
   * Used to construct a broadsword with an upper damage range of 6 and lower
   * damage range of 10.
   */
  public Broadsword() {
    damageRangeDown = 6;
    damageRangeUp = 10;
  }

  @Override
  public String getItemType() {
    return this.getClass().getSimpleName();
  }
}
