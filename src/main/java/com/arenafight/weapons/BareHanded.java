package com.arenafight.weapons;

import com.arenafight.Character;

/**
 * A class that represents an object of no weapon, or Barehanded.
 * Being barehanded means there is no extra weapon damage.
 * Barehanded does not have any special effects or ability requirements.
 */
public class BareHanded extends Weapon {
  /**
   * Constructs an object of type Barehanded by setting the damage upper bound and lower bound as 0
   * and also calling the super's constructor.
   */
  public BareHanded() {
    super();
    damageRangeUp = 0;
    damageRangeDown = 0;
  }

  @Override
  public String getItemType() {
    return this.getClass().getSimpleName();
  }

  @Override
  public int getWeaponDamage(int seed, Character character) {
    return 0;
  }
}