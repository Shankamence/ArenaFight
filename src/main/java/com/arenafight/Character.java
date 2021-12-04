package com.arenafight;

import com.arenafight.gear.Belt;
import com.arenafight.gear.BeltSize;
import com.arenafight.gear.Footwear;
import com.arenafight.gear.Headgear;
import com.arenafight.weapons.BareHanded;
import com.arenafight.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents a Character object that fights in the arena. They have a reference
 * to an abilities object that holds their ability values. They also have a Headgear, a footwear
 * and a list of belts reference that is used to track what objects are held by the character.
 * When the current health of the character drops to 0 in a battle they lose.
 */
public class Character implements Playable {

  private final Abilities baseStats;
  private Abilities modifiedStats;
  private final int totalHealth;
  private int currentHealth;
  private final String name;
  private final String title;
  private Headgear headgear;
  private Footwear footwear;
  private List<Belt> belts;
  private List<Potion> potions;
  private Weapon weapon;
  private int currentBeltWeight;
  private final int maxBeltWeight;

  /**
   * Constructs a character object with random ability values as decided by the seed passed as
   * the argument.The total health is calculated based on the sum
   * of these values and the current health
   * is temporarily set to the same value. We assign a null value to both the headgear and footwear.
   *
   * @param seed the seed value for randomness
   */
  public Character(int seed) {
    Roll roll = new Roll();
    Random random = new Random(seed);
    int baseStrength = roll.rollForStats(random.nextInt());
    int baseConstitution = roll.rollForStats(random.nextInt());
    int baseDexterity = roll.rollForStats(random.nextInt());
    int baseCharisma = roll.rollForStats(random.nextInt());
    baseStats = new Abilities(baseStrength, baseConstitution, baseDexterity, baseCharisma);
    this.name = roll.rollForCharacterName(random.nextInt());
    this.title = roll.rollForTitle(random.nextInt());
    this.totalHealth = baseStats.getCharisma()
      + baseStats.getDexterity() + baseStats.getConstitution() + baseStats.getStrength();
    headgear = new ItemFactory().makeBareHeadGear();
    footwear = new ItemFactory().makeBareFootGear();
    belts = new ArrayList<>();
    potions = new ArrayList<>();
    weapon = new BareHanded();
    currentBeltWeight = 0;
    maxBeltWeight = 10;
    modifiedStats = new Abilities(baseStrength, baseConstitution, baseDexterity, baseCharisma);
    currentHealth = totalHealth;
  }

  @Override
  public String printBaseStats() {

    return  String.format("\n %11s the %s\n", name, title)
        + String.format("%15s : %d\n", "Strength", baseStats.getStrength())
        + String.format("%15s : %d\n", "Constitution", baseStats.getConstitution())
        + String.format("%15s : %d\n", "Dexterity", baseStats.getDexterity())
        + String.format("%15s : %d\n", "Charisma", baseStats.getCharisma())
        + String.format("%15s : %d\n", "Health", totalHealth);

  }

  @Override
  public String printModifiedStats() {

    return String.format("\n %11s the %s\n", name, title)
      + String.format("%15s : %d\n", "Strength", modifiedStats.getStrength())
      + String.format("%15s : %d\n", "Constitution", modifiedStats.getConstitution())
      + String.format("%15s : %d\n", "Dexterity", modifiedStats.getDexterity())
      + String.format("%15s : %d\n", "Charisma", modifiedStats.getCharisma())
      + String.format("%15s : %d\n", "Health", currentHealth);



  }


  public int getCurrentHealth() {
    return currentHealth;
  }

  /**
   * Returns the name and the title of the character as the full name.
   *
   * @return string full name with title
   */
  public String getFullName() {
    return String.format("%s the %s", this.name, this.title);
  }


  public void setCurrentHealth(int currentHealth) {
    this.currentHealth = currentHealth;
  }

  /**
   * Gets a copy of the weapon of the character as a Weapon object.
   *
   * @return Weapon the object returned
   */
  public Weapon getWeapon() {
    return weapon;
  }

  /**
   * Sets the weapon for a character.
   *
   * @param weapon Weapon object to be set
   */
  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  /**
   * Returns a copy of the belts held by the character.
   *
   * @return an ArrayList copy of belts
   */
  public ArrayList<Belt> getBelts() {
    return new ArrayList<>(belts);
  }


  /**
   * Returns a copy of the footwear object held by the character object.
   *
   * @return a Footwear copy of footwear.
   */
  public Footwear getFootwear() {
    if (footwear != null) {
      return new Footwear(footwear);
    }
    return null;
  }

  /**
   * Used to set the footwear object for the character.
   *
   * @param footwear sets the footwear.
   */
  public void setFootwear(Footwear footwear) {
    this.footwear = footwear;
  }

  /**
   * Used to get a copy of the Headgear object held by the character.
   *
   * @return a Headgear copy of the headgear.
   */
  public Headgear getHeadgear() {
    if (headgear != null) {
      return new Headgear(headgear);
    }
    return null;
  }

  /**
   * Used to set the headgear object for character.
   *
   * @param headgear the headgear object to be set
   */
  public void setHeadgear(Headgear headgear) {
    this.headgear = headgear;
  }

  /**
   * Used to return the current weight occupied by belts.
   * @return an int with the current belt weight
   */
  public int getCurrentBeltWeight() {
    return currentBeltWeight;
  }

  /**
   * Used to set the current belt weight.
   *
   * @param currentBeltWeight the current belt weight to be set
   */
  public void setCurrentBeltWeight(int currentBeltWeight) {
    this.currentBeltWeight = currentBeltWeight;
  }

  /**
   * The max belt weight units that can be carried by a character as defined in the constructor.
   *
   * @return an int with the max belt weight of the character.
   */
  public int getMaxBeltWeight() {
    return maxBeltWeight;
  }

  /**
   * A method that adds a belt to a characters list of belts and adds to the current belt weight.
   *
   * @param belt a belt object to be added to the list of belts
   */
  public void wearBelt(Belt belt) {
    belts.add(belt);
    if (belt.getBeltSize().equals(BeltSize.SMALL)) {
      setCurrentBeltWeight(getCurrentBeltWeight() + 1);
    } else if (belt.getBeltSize().equals(BeltSize.MEDIUM)) {
      setCurrentBeltWeight(getCurrentBeltWeight() + 2);
    } else if (belt.getBeltSize().equals((BeltSize.LARGE))) {
      setCurrentBeltWeight(getCurrentBeltWeight() + 4);
    }
  }

  /**
   * A method that adds a potion to a characters list of potions.
   *
   * @param potion a potion object to be added to the list of potions
   */
  public void drinkPotion(Potion potion) {
    potions.add(potion);
  }

  /**
   * A method that returns a copy of the array list of potions
   * held by the character.
   *
   * @return a copy of the Arraylist of potions
   */
  public ArrayList<Potion> getPotions() {
    return new ArrayList<>(potions);
  }

  @Override
  public Abilities getModifiedStats() {
    if (modifiedStats != null) {
      return new Abilities(modifiedStats.getStrength(), modifiedStats.getConstitution(),
        modifiedStats.getDexterity(), modifiedStats.getCharisma());
    }
    return null;
  }

  @Override
  public Abilities getBaseStats() {
    if (baseStats != null) {
      return new Abilities(baseStats.getStrength(), baseStats.getConstitution(),
        baseStats.getDexterity(), baseStats.getCharisma());
    }
    return null;
  }

  /**
   * Sets the modified stats object reference after calculating the modifications.
   *
   * @param modifiedStats the modified stats object to set
   */
  public void setModifiedStats(Abilities modifiedStats) {
    this.modifiedStats = modifiedStats;
  }

  /**
   * A method that does the damage calculation for a character attacking an
   * opponent and reduces an opponent's health.
   *
   * @param opponent Character object, the opponent to attack
   * @param seed a seed that determines the random values
   * @return a string with the text of the battle
   */
  public String attack(Character opponent, int seed) {
    int strikingPower = calculateStrikingPower(this,seed);
    int avoidanceAbility = calculateAvoidanceAbility(opponent,seed);
    if (strikingPower > avoidanceAbility) {
      int potentialStrikingDamage = calculatePotentialStrikingDamage(this,seed);
      int trueDamage = potentialStrikingDamage - opponent.getModifiedStats().getConstitution();
      if (trueDamage < 0) {
        trueDamage = 0;
      }
      opponent.setCurrentHealth(opponent.getCurrentHealth() - trueDamage);
      return String.format("\n%s inflicted %d damage on %s!\n\n", this.getWeapon().getItemType(),
        trueDamage,opponent.getFullName());
    } else {
      return String.format( "The attack was avoided as "
        + "Striking Power (%d) was less than avoidance (%d)!\n", strikingPower, avoidanceAbility);
    }
  }

  /**
   * Resets the character to the default state by unequipping all the gear and removing
   * potion effects. The weapon is also unequipped and replaced with BareHanded.
   */
  public void resetCharacter() {
    headgear = new ItemFactory().makeBareHeadGear();
    footwear = new ItemFactory().makeBareFootGear();
    belts = new ArrayList<>();
    potions = new ArrayList<>();
    weapon = new BareHanded();
    currentBeltWeight = 0;
    modifiedStats = new Abilities(baseStats.getStrength(), baseStats.getConstitution(),
      baseStats.getDexterity(), baseStats.getCharisma());
    currentHealth = totalHealth;
  }

  /**
   * Used in damage calculation for calculating the Striking Power of attacker.
   * @param character the attacking character
   * @param seed the random seed int
   * @return an integer with striking power
   */
  public int calculateStrikingPower(Character character, int seed) {
    Random random = new Random(seed);
    Roll roll = new Roll();
    return character.getModifiedStats().getStrength()
      + roll.rollInRange(1, 10, random.nextInt());
  }

  /**
   * Used in damage calculation for calculating the Striking Power of defender.
   * @param opponent the defending character
   * @param seed the random seed int
   * @return an integer with avoidance ability
   */
  public int calculateAvoidanceAbility(Character opponent, int seed) {
    Random random = new Random(seed);
    Roll roll = new Roll();
    return opponent.getModifiedStats().getDexterity()
      + roll.rollInRange(1, 6, random.nextInt());
  }

  /**
   * Used in damage calculation for calculating the Potential Striking damage of attacker.
   * @param player the attacking character
   * @param seed the random seed int
   * @return an integer with potential striking damage
   */
  public int calculatePotentialStrikingDamage(Character player, int seed) {
    Random random = new Random(seed);
    return player.getModifiedStats().getStrength()
      + this.getWeapon().getWeaponDamage(random.nextInt(),this);
  }
}
