package com.arenafight;

/**
 * This class represents a potion which are items used by the player before a battle.
 * Potions are consumed by the player before entering the field of battle.
 * They can temporarily affect any of the player's abilities.
 * There is no limit to the number of these that the player can drink.
 * They can boost all 3 abilities within the range 0-3 (Strength 0-10)
 */
public class Potion implements Item {
  private final Abilities boostAbilities;
  private final String name;

  /**
   * Constructs a potion object that boosts the abilities with the values
   * as per given in the argument. The name of the potion is also generated here
   * with a roll.
   *
   * @param boostAbilities the Ability object to be assigned to the potion
   * @param seed the integer that determines the random value
   */
  public Potion(Abilities boostAbilities, int seed) {
    this.boostAbilities = boostAbilities;
    this.name = new Roll().rollForGearClass(seed) + " Rank Potion";
  }

  /**
   * Returns the name of the potion.
   * @return string of the name
   */
  public String getName() {
    return name;
  }

  @Override
  public Abilities getAbilities() {
    return new Abilities(boostAbilities.getStrength(), boostAbilities.getConstitution(),
        boostAbilities.getDexterity(), boostAbilities.getCharisma());
  }

  @Override
  public String getItemType() {
    return this.getClass().getSimpleName();
  }


}
