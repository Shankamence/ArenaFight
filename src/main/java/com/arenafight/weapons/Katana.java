package com.arenafight.weapons;

import com.arenafight.Character;
import com.arenafight.Roll;

import java.util.Random;

/**
 * A class that represents a weapon object of the type Katana.
 * A katana can strike an opponent twice. Katanas are lightweight curved swords that come in pairs.
 * They can do a base of 4-6 points of damage when they hit.
 * They are so light that a player can carry two of them (which attack separately).
 */
public class Katana extends Sword {

  /**
   * Constructs a Katana type object that has a damage lower bound of 4 and an upper bound of 6.
   */
  public Katana() {
    damageRangeDown = 4;
    damageRangeUp = 6;
  }

  @Override
  public String getItemType() {
    return this.getClass().getSimpleName();
  }

  @Override
  public int getWeaponDamage(int seed, Character character) {
    Roll roll = new Roll();
    Random random = new Random(seed);
    int damage = roll.rollInRange(damageRangeDown, damageRangeUp,random.nextInt());
    damage += roll.rollInRange(damageRangeDown, damageRangeUp,random.nextInt());
    return damage;
  }
}
