package com.arenafight.weapons;

/**
 * A class that represents an object of an axe weapon.
 * Axes are great general weapons doing 6-10 points of damage when they hit.
 * They do not have any special effects or ability requirements.
 */
public class Axe extends Weapon {
  /**
   * Constructs an object of type Axe by setting the damage upper bound and lower bound, and
   * also calling the super's constructor.
   */
  public Axe() {
    super();
    damageRangeUp = 10;
    damageRangeDown = 6;
  }

  @Override
  public String getItemType() {
    return this.getClass().getSimpleName();
  }
}
