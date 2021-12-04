package com.arenafight.weapons;

import com.arenafight.Character;
import com.arenafight.Roll;

import java.util.Random;

/**
 * A class used to represent a Flail weapon. Flails are also great general weapons,
 * but they can only be effectively wielded by players with a dexterity greater than 14.
 * They do 8-12 points of damage when they hit.
 * If the player does not have the dexterity to wield a flail, the flail only does half damage.
 */
public class Flail extends Weapon {

  /**
   * Used to constuct a Flail object with a lower bound damage range of 8
   * and an upper bound damage range of 12.
   */
  public Flail() {
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

    if (character.getModifiedStats().getDexterity() > 14) {
      damage = roll.rollInRange(damageRangeDown, damageRangeUp, random.nextInt());
    } else {
      damage = roll.rollInRange(damageRangeDown / 2, damageRangeUp / 2, random.nextInt());
    }
    return damage;
  }

}
