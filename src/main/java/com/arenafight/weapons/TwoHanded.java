package com.arenafight.weapons;

import com.arenafight.Character;
import com.arenafight.Roll;

import java.util.Random;

/**
 * This class represents two handed swords.
 * Two-handed swords are a heavy sword that can only be effectively
 * wielded by players with strength greater than 14,
 * but they can do 8-12 points of damage when they hit.
 * If the player does not have the strength to wield a two-handed sword,
 * the sword only does half damage.
 */
public class TwoHanded extends Sword {

  /**
   * Constructs a TwoHanded object with a lower bound damage of 8
   * and an upper bound damage of 12.
   */
  public TwoHanded() {
    damageRangeDown = 8;
    damageRangeUp = 12;
  }

  @Override
  public String getItemType() {
    return this.getClass().getSimpleName();
  }

  @Override
  public int getWeaponDamage(int seed, Character character) {
    Roll roll = new Roll();
    Random random = new Random(seed);
    int damage;

    if (character.getModifiedStats().getStrength() > 14) {
      damage = roll.rollInRange(damageRangeDown, damageRangeUp, random.nextInt());
    } else {
      damage = roll.rollInRange(damageRangeDown / 2, damageRangeUp / 2, random.nextInt());
    }
    return damage;
  }

}
