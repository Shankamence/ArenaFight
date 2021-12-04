package com.arenafight.weapons;

import com.arenafight.Abilities;
import com.arenafight.AbilityBuilder;
import com.arenafight.Character;
import com.arenafight.Item;
import com.arenafight.Roll;

import java.util.Random;

/**
 * An abstract class that represents the weapon held by the character. The three
 * types of weapons are swords, flails and axes. There may be some stat requirement for certain
 * weapons and some weapons have a different damage calculation.
 */
public abstract class Weapon implements Item {

  protected Abilities boostAbilities;
  protected int damageRangeUp;
  protected int damageRangeDown;

  /**
   * Constructs a weapon object that sets all the ability boosts to 0.
   * This is kept for future use where weapons have special effects and can also boost
   * abilities. For now the value has been set to 0.
   */
  public Weapon() {
    boostAbilities = new Abilities(0,0,0,0);
  }

  /**
   * Returns the damage roll for a weapon based on upper and lowerbound values.
   * @param seed the seed integer that decides the random generated value
   * @return an integer with the damage between upper and lower bound.
   */
  public int getWeaponDamage(int seed, Character character) {
    Roll roll = new Roll();
    Random random = new Random(seed);
    return roll.rollInRange(damageRangeDown, damageRangeUp,random.nextInt());
  }

  @Override
  public Abilities getAbilities() {
    return new AbilityBuilder().build();
  }

  @Override
  public String getItemType() {
    return this.getClass().getSimpleName();
  }
}
