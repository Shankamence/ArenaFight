package com.arenafight.gear;

import com.arenafight.Abilities;
import com.arenafight.Roll;

/**
 * A class that represents the belt object to be equipped on to the character.
 * Belts come in three sizes small, medium, and large and are worn around the player's torso
 * affecting up to two of the player's abilities.
 * Small belts count as 1 unit, medium as 2 units, and large as 4 units.
 */
public class Belt extends Gear {

  private BeltSize beltsize;

  /**
   * Constructs a belt which boosts 2 of the 4 abilities which are chosen randomly.
   *
   * @param boostAbility the ability object that is assigned to the belt
   * @param seed the seed integer that determines the random roll for which 2 abilities are chosen
   */
  public Belt(Abilities boostAbility, int seed) {
    super();
    orderNumber = 2;
    Roll roll = new Roll();
    this.name = generateName(seed);
    this.beltsize = BeltSize.SMALL;
    int[] rollVals = new int[3];
    rollVals[0] = roll.rollInRange(1, 4, seed);
    rollVals[1] = roll.rollInRange(1, 4, seed);
    if (rollVals[1] == rollVals[0]) {
      rollVals[1]++;
    }
    if (rollVals[0] == 4) {
      rollVals[1] = 1;
    }
    for (int i = 0; i < 2; i++) {

      switch (rollVals[i]) {
        case 1 : boostAbility.setStrength(0);
        break;
        case 2 : boostAbility.setConstitution(0);
        break;
        case 3 : boostAbility.setDexterity(0);
        break;
        case 4 : boostAbility.setCharisma(0);
        break;
        default:
          break;
      }
    }
    rollVals[2] = roll.rollInRange(1, 3, seed);
    switch (rollVals[2]) {
      case 1 : this.beltsize = BeltSize.SMALL;
      break;
      case 2 : this.beltsize = BeltSize.MEDIUM;
      break;
      case 3 : this.beltsize = BeltSize.LARGE;
      break;
      default: this.beltsize = null;
    }
    this.boostAbility = boostAbility;
  }

  @Override
  protected String generateName(int seed) {
    return super.generateName(seed) + " Belt";
  }

  @Override
  public Abilities getAbilities() {
    return boostAbility;
  }

  /**
   * Returns the belt size for a belt as an enum value from the Enum BeltSize.
   * @return Enum BeltSize
   */
  public BeltSize getBeltSize() {
    return beltsize;
  }

}
